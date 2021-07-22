package com.MySQL_First_trying;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = Person.getConnection()) {
            System.out.println("Connection successful");
        }
        catch (SQLException sQLE) {
            System.out.println("Connection unsuccessful");
            System.out.println(sQLE.getMessage());

        }

    }
}
