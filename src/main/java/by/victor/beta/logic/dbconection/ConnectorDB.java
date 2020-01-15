package by.victor.beta.logic.dbconection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {

    public static final String DB_BUNDLE_NAME = "database";

    public static Connection getConnection() throws SQLException {
        ResourceBundle connectionInfo = ResourceBundle.getBundle(DB_BUNDLE_NAME);
         String url = connectionInfo.getString("db.url");
        String user = connectionInfo.getString("db.user");
        String pass = connectionInfo.getString("db.password");
        return DriverManager.getConnection(url, user, pass);
    }
}
