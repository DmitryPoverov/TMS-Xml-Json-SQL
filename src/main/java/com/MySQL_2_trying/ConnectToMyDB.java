package com.MySQL_2_trying;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectToMyDB {

    public static Connection getConnection () throws Exception {

        Properties properties = new Properties();

        try(InputStream iS = ConnectToMyDB.class.getClassLoader().getResourceAsStream("DB.properties")) {
            properties.load(iS);
        }

        String url = properties.getProperty("db.url");
        String login = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        String driver = properties.getProperty("db.driver");
        Class.forName(driver);

        return DriverManager.getConnection(url,login,password);
    }
}
