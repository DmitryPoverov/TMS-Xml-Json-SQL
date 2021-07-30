package com.MySQL_2_trying;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo6_Showing_Table_Elements_by_Indexes {

    private static final String SQL_ZOO_SHOP = "SELECT * FROM zoo_products.shop";

    public static void main(String[] args) {

        //https://metanit.com/java/database/2.5.php

        try (Connection conn = ConnectToMyDB.getConnection();

             Statement statement = conn.createStatement()){

            System.out.println("ConnectionMyDB to Store DB successful!");

            ResultSet resultSet = statement.executeQuery(SQL_ZOO_SHOP);

            while(resultSet.next()){

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int price = resultSet.getInt(3);

                System.out.printf("%d. %s - %d \n", id, name, price);
            }

        } catch (Exception throwable) {
            System.out.println("ConnectionMyDB failed...");
            System.out.println(throwable.getMessage());
        }
    }
}