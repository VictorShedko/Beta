package by.victor.beta.repository;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
    private Queue<ProxiConfection> freeConnection = new ArrayDeque();
    private List<ProxiConfection> lockedConnection = new ArrayList();

    ConnectionProvider(){
        ResourceBundle connectionInfo = ResourceBundle.getBundle(DB_BUNDLE_NAME);
        pool_size=Integer.parseInt(connectionInfo.getString(DB_POOL_SIZE_PARAMETER_NAME));
        semaphore= new Semaphore(pool_size,true);

        try {
             for(int i=0;i<pool_size;i++) {
                 ProxiConfection newConnection = ConnectionCreator.getConnection();
                freeConnection.add(newConnection);
            }
        } catch (SQLException e) {
            logger.log(Level.FATAL,"connection error",e);
        }

    }

    public Optional<ProxiConfection> occupyConnection() {
        ProxiConfection connection=null;
        try {
            if(semaphore.tryAcquire(timeWait, TimeUnit.MILLISECONDS)) {
                gateOperationLock.lock();
               try {


                   if (!freeConnection.isEmpty()) {
                       connection = freeConnection.poll();
                       lockedConnection.add(connection);
                   }
               }finally {
                   gateOperationLock.unlock();
               }


            }else {
                logger.log(Level.DEBUG,"no free connection ");
            }
        } catch (InterruptedException e) {
            logger.log(Level.DEBUG,"can't get connection",e);
        }
        return Optional.ofNullable(connection);
    }

    public void freeConnection(ProxiConfection connection) {
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
        gateOperationLock.lock();
        freeConnection.forEach(t-> {
            try {
                t.delete();
            } catch (SQLException e) {
                logger.log(Level.ERROR,"destroy error",e);
            }
        });

    }

}
