package by.tms.sql.MySQL_2_trying;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Demo9_Exposing_Products_less_Than {
    private static final String SQL_SELECT_BY_PRICE = "SELECT * FROM zoo_products.shop WHERE dPrice < ?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input max product price: ");
        int maxPrice = scanner.nextInt();

        try (Connection conn = ConnectToMyDB.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_BY_PRICE)){
            System.out.println("ConnectionMyDB to Store DB successful!");

            preparedStatement.setInt(1, maxPrice);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                int id = resultSet.getInt("Id");
                String name = resultSet.getString("zProdName");
                int price = resultSet.getInt("dPrice");

                System.out.printf("%d. %s - %d \n", id, name, price);
            }

        } catch (Exception throwable) {
            System.out.println("ConnectionMyDB failed...");
            System.out.println(throwable);
        }

    }
}
