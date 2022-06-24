package by.tms.sql.MySQL_2_trying;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo7_Showing_Table_Elements_by_Label {
    private static final String SQL_PRODUCTS =
            "SELECT * FROM zoo_products.shop";

    public static void main(String[] args) {

        //https://metanit.com/java/database/2.5.php

        try (Connection conn = ConnectToMyDB.getConnection();
             Statement statement = conn.createStatement()){
            System.out.println("ConnectionMyDB to Store DB successful!");
            ResultSet resultSet = statement.executeQuery(SQL_PRODUCTS);
            while(resultSet.next()){

                int id = resultSet.getInt("Id");
                String name = resultSet.getString("zProdName");
                int price = resultSet.getInt("dPrice");

                System.out.printf("%d. %s - %d \n", id, name, price);
            }

        } catch (Exception throwable) {
            System.out.println("ConnectionMyDB failed...");
            System.out.println(throwable.getMessage());
        }
    }
}