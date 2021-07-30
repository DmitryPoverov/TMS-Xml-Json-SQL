package com.MySQL_2_trying;

import java.sql.Connection;
import java.sql.Statement;

public class Demo2_Creating_DB_and_Table {
    private static final String SQL_CREATE_DATABASE =
            "CREATE DATABASE ZOO_products ";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE ZOO_products.shop (id INT PRIMARY KEY AUTO_INCREMENT, zProdName VARCHAR(20), dPrice INT)";
//          "CREATE TABLE ZOO_products.shop (id INT PRIMARY KEY AUTO_INCREMENT, zProdName VARCHAR(20) UNIQUE, dPrice INT)";

    public static void main(String[] args) {

        //https://metanit.com/java/database/2.3.php

        try (Connection conn = ConnectToMyDB.getConnection();

             Statement statement = conn.createStatement()){

            System.out.println("ConnectionMyDB to Store DB successful!");
            statement.executeUpdate(SQL_CREATE_DATABASE);
            System.out.println("Database has been created!");
            statement.executeUpdate(SQL_CREATE_TABLE);
            System.out.println("table has been created!");

        } catch (Exception throwable) {
            System.out.println("ConnectionMyDB failed...");
            System.out.println(throwable.getMessage());
        }
    }
}