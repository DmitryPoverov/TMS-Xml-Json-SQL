package com.MySQL_First_trying;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Person {

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try (InputStream iStream = Person.class.getClassLoader().getResourceAsStream("DB.properties")) {
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
