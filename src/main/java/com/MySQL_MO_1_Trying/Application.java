package com.MySQL_MO_1_Trying;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Application {

    private static final String SQL_CREATE_DATABASE =
            "CREATE DATABASE People_DB";

    private static final String SQL_USE_DB =
            "USE People_DB";

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE Person (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), surname  VARCHAR(20), age INT)";

    private static final String SQL_INSERT_MAN =
            "INSERT Person (name, surname, age) VALUES (?, ?, ?)";

    private static final String SQL_INSERT_MEN =
            "INSERT INTO Person(name, surname, age) VALUES('Bill', 'Lack', 54), ('Jim', 'Gambol', 43), ('Sam', 'Smith', 31)";

    public static void main(String[] args) {

        try (Connection connect = MyConnect.getConnection();
             Statement state = connect.createStatement()) {
            System.out.println("Successful connection.");
            state.executeUpdate(SQL_CREATE_DATABASE);
            System.out.println("Successful creation of DB.");
            state.executeUpdate(SQL_USE_DB);
            System.out.println("Successful start of using.");
            state.executeUpdate(SQL_CREATE_TABLE);
            System.out.println("Successful creation of TABLE.");
            state.executeUpdate(SQL_INSERT_MEN);
            System.out.println("successful appending of men");

        } catch (SQLException | IOException exc) {
            exc.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Set the name:");
        String name = sc.nextLine();
        System.out.println("Set the surname:");
        String surname = sc.nextLine();
        System.out.println("Set the age:");
        int age = sc.nextInt();

        try (Connection connect = MyConnect.getConnection();
             Statement state = connect.createStatement();
             PreparedStatement pState = connect.prepareStatement(SQL_INSERT_MAN)) {
            System.out.println("Successful connection.");
            state.executeUpdate(SQL_USE_DB);
            pState.setString(1, name);
            pState.setString(2, surname);
            pState.setInt(3, age);
            pState.executeUpdate();
            System.out.println("Successful appending to the table.");

        } catch (SQLException | IOException exc) {
            exc.printStackTrace();
        }


    }
}