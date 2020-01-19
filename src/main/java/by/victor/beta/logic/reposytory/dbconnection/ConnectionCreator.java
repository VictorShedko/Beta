package by.victor.beta.logic.reposytory.dbconnection;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionCreator {
    private static final Logger logger= LogManager.getLogger(ConnectionCreator.class);
    private static final String DB_BUNDLE_NAME = "database";
    private static final String DB_URL="db.url";
    private static final String DB_USER="db.user";
    private static final String DB_PASSWORD="db.password";
     static {
         try {
             DriverManager.registerDriver(new com.mysql.jdbc.Driver());
         } catch (SQLException e) {
             logger.log(Level.ERROR,"registration driver error",e);
         }
     }
    public static Connection getConnection() throws SQLException {
        ResourceBundle connectionInfo = ResourceBundle.getBundle(DB_BUNDLE_NAME);
        String url = connectionInfo.getString(DB_URL);
        String user = connectionInfo.getString(DB_USER);
        String pass = connectionInfo.getString(DB_PASSWORD);
        logger.log(Level.TRACE,"get connection param:"+url+user+pass);
        return DriverManager.getConnection(url, user, pass);
    }
}
