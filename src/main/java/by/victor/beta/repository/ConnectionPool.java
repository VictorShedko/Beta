package by.victor.beta.repository;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Contains fixed number of connection with db
 */
public enum ConnectionPool {
    INSTANCE;

    private final Logger logger= LogManager.getLogger(ConnectionPool.class);
    private static final String DB_PROPERTIES_FILE = "C:\\Users\\ACER\\Documents\\Beta\\src\\main\\resources\\" +
            "database.properties";
    private static final String DB_POOL_SIZE_PARAMETER_NAME ="db.poolsize";
    private static final int timeWait=50;
    private int pool_size;
    private ReentrantLock connectionOperationLock = new ReentrantLock();
    private Semaphore semaphore;
    private Queue<ProxyConnection> freeConnection = new ArrayDeque();
    private List<ProxyConnection> lockedConnection = new ArrayList();

    ConnectionPool(){
        File file = new File(DB_PROPERTIES_FILE);
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
            pool_size=Integer.parseInt(properties.getProperty(DB_POOL_SIZE_PARAMETER_NAME));
        } catch (IOException e) {
            logger.log(Level.ERROR,"read properties error",e);
            pool_size=10;
        }

        semaphore= new Semaphore(pool_size,true);

        try {
             for(int i=0;i<pool_size;i++) {
                 ProxyConnection newConnection = ConnectionCreator.getConnection();
                freeConnection.add(newConnection);
            }
        } catch (SQLException e) {
            logger.log(Level.FATAL,"connection error",e);
        }

    }

    /**
     * Occupy connection.
     * If exist free confection instantly return it, else return optional of null
     * @return the optional
     */
    public Optional<ProxyConnection> occupyConnection() {
        ProxyConnection connection=null;
        try {
            if(semaphore.tryAcquire(timeWait, TimeUnit.MILLISECONDS)) {
                connectionOperationLock.lock();
               try {


                   if (!freeConnection.isEmpty()) {
                       connection = freeConnection.poll();
                       lockedConnection.add(connection);
                   }
               }finally {
                   connectionOperationLock.unlock();
               }


            }else {
                logger.log(Level.DEBUG,"no free connection ");
            }
        } catch (InterruptedException e) {
            logger.log(Level.DEBUG,"can't get connection",e);
        }
        return Optional.ofNullable(connection);
    }

    /**
     * Free connection.
     *
     * @param connection the connection
     */
    public void freeConnection(ProxyConnection connection) {
        connectionOperationLock.lock();
        try {
            freeConnection.add(connection);
            lockedConnection.remove(connection);

        } finally {
            semaphore.release();
            connectionOperationLock.unlock();
        }
    }

    /**
     * Destroy.
     */
    public void destroy(){
        connectionOperationLock.lock();
        try {
            freeConnection.forEach(t -> {
                try {
                    t.delete();
                } catch (SQLException e) {
                    logger.log(Level.ERROR, "destroy error", e);
                }
            });
        }finally {
            connectionOperationLock.unlock();
        }
    }

}
