package by.victor.beta.repository;

import by.victor.beta.service.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

 class ConnectionCreator {
    private static final Logger logger= LogManager.getLogger(ConnectionCreator.class);
    private static final String DB_PROPERTIES_NAME = "C:\\Users\\ACER\\Documents\\Beta\\src\\main\\resources\\database.properties";
    private static final String DB_URL="db.url";
    private static final String DB_USER="db.user";
    private static final String DB_PASSWORD="db.password";
     static {
         try {
             DriverManager.registerDriver(new com.mysql.jdbc.Driver());
         } catch (SQLException e) {
             logger.log(Level.FATAL,"registration driver error",e);
             throw new ExceptionInInitializerError();

         }
     }
    public static ProxyConnection getConnection() throws SQLException {
        File file = new File(DB_PROPERTIES_NAME);
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
        String url = (String) properties.get(DB_URL);
        String user = (String) properties.get(DB_USER);
        String pass = (String) properties.get(DB_PASSWORD);
        logger.log(Level.TRACE,"get connection param:"+url+user+pass);
        return new ProxyConnection(DriverManager.getConnection(url, user, pass));
    }
}
