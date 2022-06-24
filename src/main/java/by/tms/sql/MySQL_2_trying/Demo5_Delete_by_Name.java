package by.tms.sql.MySQL_2_trying;

import java.sql.Connection;
import java.sql.Statement;

public class Demo5_Delete_by_Name {
    private static final String SQL_PRODUCT_BY_NAME = "DELETE FROM zoo_products.shop WHERE zProdName = 'strap'";

    public static void main(String[] args) {
        try (Connection conn = ConnectToMyDB.getConnection();
             Statement statement = conn.createStatement()){
            System.out.println("ConnectionMyDB to Store DB successful!");
            int rows = statement.executeUpdate(SQL_PRODUCT_BY_NAME);
            System.out.printf("%d row(s) deleted", rows);

        } catch (Exception throwable) {
            System.out.println("ConnectionMyDB failed...");
            System.out.println(throwable);
        }

    }
}
