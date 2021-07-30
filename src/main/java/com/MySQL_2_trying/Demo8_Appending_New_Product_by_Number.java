package com.MySQL_2_trying;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Demo8_Appending_New_Product_by_Number {
    private static final String SQL_INSERT_PRODUCT =
            "INSERT INTO zoo_products.shop (zProdName, dPrice) Values (?, ?)";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input product name: ");
        String name = scanner.nextLine();

        System.out.print("Input product price: ");
        int price = scanner.nextInt();

        try (Connection conn = ConnectToMyDB.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_PRODUCT)){
            System.out.println("ConnectionMyDB to Store DB successful!");

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, price);

            int rows = preparedStatement.executeUpdate();

            System.out.printf("%d rows added", rows);

        } catch (Exception throwable) {
            System.out.println("ConnectionMyDB failed...");
            System.out.println(throwable);
        }
    }
}