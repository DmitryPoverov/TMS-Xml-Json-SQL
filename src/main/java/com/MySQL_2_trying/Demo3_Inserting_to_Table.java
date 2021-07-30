package com.MySQL_2_trying;

import java.sql.Connection;
import java.sql.Statement;

public class Demo3_Inserting_to_Table {

    private static final String SQL_INSERT_PRODUCT =
            "INSERT ZOO_products.shop (zProdName, dPrice) VALUES ('Strap', 92000),('Bowl', 23000),('Toy', 27000)";

    public static void main(String[] args) {
        try (Connection conn = ConnectToMyDB.getConnection();
             Statement statement = conn.createStatement()){
            System.out.println("ConnectionMyDB to Store DB successful!");
            int rows = statement.executeUpdate(SQL_INSERT_PRODUCT);
            System.out.printf("Added %d rows", rows);

        } catch (Exception throwable) {
            System.out.println("ConnectionMyDB failed...");
            System.out.println(throwable);
        }

    }
}
