package by.tms.sql.MySQL_1_trying;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = ConnectionMyDB.getConnection()) {
            System.out.println("ConnectionMyDB successful.");
        }
        catch (SQLException sQLE) {
            System.out.println("ConnectionMyDB unsuccessful...");
            System.out.println(sQLE.getMessage());
        }
    }
}