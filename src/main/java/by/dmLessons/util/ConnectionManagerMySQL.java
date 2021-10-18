package by.dmLessons.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerMySQL {

    private static final String URL_KEY = "db1.url";
    private static final String USER_KEY = "db1.username";
    private static final String PASSWORD_KEY = "db1.password";

    static {
        loadDriver();
    }

    private ConnectionManagerMySQL() {
    }

    public static Connection open() {
        try {
            return DriverManager.getConnection
                    (PropertiesUtil.get(URL_KEY), PropertiesUtil.get(USER_KEY), PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadDriver(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
