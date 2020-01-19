package by.victor.beta.logic.reposytory.dbconnection;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public enum ConnectionProvider {
    instance;

    private final Logger logger= LogManager.getLogger(ConnectionProvider.class);
    private static final String DB_BUNDLE_NAME = "database";
    private static final String DB_POOL_SIZE_PARAMETER_NAME ="db.poolsize";
    private static final int timeWait=50;
    private int pool_size;
    private ReentrantLock gateOperationLock = new ReentrantLock();
    private Semaphore semaphore;
    private Queue<Connection> freeConnection = new ArrayDeque();
    private List<Connection> lockedConnection = new ArrayList();

    ConnectionProvider(){
        ResourceBundle connectionInfo = ResourceBundle.getBundle(DB_BUNDLE_NAME);
        pool_size=Integer.parseInt(connectionInfo.getString(DB_POOL_SIZE_PARAMETER_NAME));
        semaphore= new Semaphore(pool_size,true);
        gateOperationLock.lock();

        try {
             for(int i=0;i<pool_size;i++) {
                Connection newConnection = ConnectionCreator.getConnection();
                freeConnection.add(newConnection);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR,"connection error",e);
        }finally {
            gateOperationLock.unlock();
        }

    }

    public Optional<Connection> occupyConnection() {
        Connection connection=null;
        gateOperationLock.lock();
        try {

            if(semaphore.tryAcquire(timeWait, TimeUnit.MILLISECONDS)) {//todo tak?
                if (!freeConnection.isEmpty()) {
                    connection = freeConnection.poll();
                    lockedConnection.add(connection);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            gateOperationLock.unlock();
        }
        return Optional.ofNullable(connection);
    }

    public void freeConnection(Connection connection) {
        gateOperationLock.lock();
        try {
            freeConnection.add(connection);
            lockedConnection.remove(connection);

        } finally {
            semaphore.release();
            gateOperationLock.unlock();
        }
    }

    public void destroy(){
        freeConnection.forEach(t-> {
            try {
                t.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR," destroy error",e);
            }
        });
        lockedConnection.forEach(t-> {
            try {
                t.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR," destroy error",e);
            }
        });
    }

}
