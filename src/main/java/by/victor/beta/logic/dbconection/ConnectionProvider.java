package by.victor.beta.logic.dbconection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public enum ConnectionProvider {
    instance;

    private static final String DATA_BASE_URL="jdbc:mysql://localhost:3306/testphones";
    public static final String USER = "root";
    public static final String PASSWORD = "victor";

    private ReentrantLock gateOperationLock = new ReentrantLock();

    private Queue<Connection> freeConnection = new ArrayDeque();
    private List<Connection> lockedConnection = new ArrayList();
    ConnectionProvider(){


        gateOperationLock.lock();
        int a=3;
        a++;
        try {
             for(int i=0;i<10;i++) {
                Connection newConnection = ConnectorDB.getConnection();
                freeConnection.add(newConnection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            gateOperationLock.unlock();
        }

    }

    public Optional<Connection> occupyConnection() {
        Connection connection=null;
        gateOperationLock.lock();
        try {
            if (!freeConnection.isEmpty()) {
                connection = freeConnection.poll();
                lockedConnection.add(connection);
            }
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
            gateOperationLock.unlock();
        }
    }

    public void destroy(){
        freeConnection.forEach(t-> {
            try {
                t.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        lockedConnection.forEach(t-> {
            try {
                t.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

}
