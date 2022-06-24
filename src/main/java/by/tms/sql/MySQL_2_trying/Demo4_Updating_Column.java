package by.tms.sql.MySQL_2_trying;

import java.sql.Connection;
import java.sql.Statement;

public class Demo4_Updating_Column {

    private static final String SQL_UPDATE_PRICE = "UPDATE zoo_products.shop SET dPrice = dPrice - 5000";

    public static void main(String[] args) {
        try (Connection conn = ConnectToMyDB.getConnection();
             Statement statement = conn.createStatement()){
            System.out.println("ConnectionMyDB to Store DB successful!");
            int rows = statement.executeUpdate(SQL_UPDATE_PRICE);
            System.out.printf("Updated %d rows", rows);

        } catch (Exception throwable) {
            System.out.println("ConnectionMyDB failed...");
            System.out.println(throwable);
        }

    }
}
