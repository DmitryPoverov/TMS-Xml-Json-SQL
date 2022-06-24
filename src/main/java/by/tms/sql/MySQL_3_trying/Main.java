package by.tms.sql.MySQL_3_trying;

import by.tms.sql.MySQL_2_trying.ConnectToMyDB;
import java.sql.Connection;
import java.sql.Statement;

public class Main {
    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE persons (id INT PRIMARY KEY AUTO_INCREMENT, FirstName VARCHAR(20), LastName VARCHAR(20), Age INT)";

    public static void main(String[] args) {
        //https://metanit.com/java/database/2.3.php
        try (Connection conn = ConnectToMyDB.getConnection();
             Statement statement = conn.createStatement()){
            System.out.println("ConnectionMyDB to Store DB successful!");
            int rows = statement.executeUpdate(SQL_CREATE_TABLE);
            System.out.println("Database has been created!");

        } catch (Exception throwable) {
            System.out.println("ConnectionMyDB failed...");
            System.out.println(throwable);
        }
    }
}