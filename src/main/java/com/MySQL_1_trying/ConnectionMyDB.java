package com.MySQL_1_trying;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionMyDB {

    public static java.sql.Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try (InputStream iStream = ConnectionMyDB.class.getClassLoader().getResourceAsStream("DB.properties")) {
            properties.load(iStream);
        }
        catch (IOException iOE) {
            System.out.println(iOE.getMessage());
        }
        String url = properties.getProperty("db.url");
        String userName = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String driver = properties.getProperty("db.driver");

        return DriverManager.getConnection(url, userName, password);
    }
}
