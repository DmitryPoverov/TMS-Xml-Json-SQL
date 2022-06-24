package by.tms.sql.MySQL_MO_1_Trying;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnect {

    public static Connection getConnection() throws IOException, SQLException {
        Properties myProp = new Properties();
        try (InputStream iStream = MyConnect.class.getClassLoader().getResourceAsStream("DB.properties")) {
            myProp.load(iStream);
        }
        String url = myProp.getProperty("db.url");
        String user = myProp.getProperty("db.username");
        String password = myProp.getProperty("db.password");
        String driver = myProp.getProperty("db.driver");

        return DriverManager.getConnection(url, user, password);
    }

}
