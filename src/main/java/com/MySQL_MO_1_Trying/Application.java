package com.MySQL_MO_1_Trying;

import java.io.IOException;
import java.sql.*;
import java.util.InputMismatchException;
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

    private static final String SQL_SHOW_ALL =
            "SELECT * FROM Person ";

    private static final String SQL_DELETE_BY_ID =
            "DELETE FROM people_db.person WHERE Id =";

    public static void createDataBase(){
        try (Connection connect = MyConnect.getConnection();
             Statement state = connect.createStatement()) {
            System.out.println("Successful connection.");
            state.executeUpdate(SQL_CREATE_DATABASE);
            System.out.println("Successful creation of DB.");

        } catch (SQLException | IOException exc) {
            System.out.println("The database has already exist...");
            System.out.println(exc.getMessage());
        }
    }

    public static void createTable(){
        try (Connection connect = MyConnect.getConnection();
             Statement state = connect.createStatement()) {
            System.out.println("Successful connection.");
            state.executeUpdate(SQL_USE_DB);
            System.out.println("Successful start of using.");
            state.executeUpdate(SQL_CREATE_TABLE);
            System.out.println("Successful creation of TABLE.");

        } catch (SQLException | IOException exc) {
            System.out.println("The table has already exist...");
            System.out.println(exc.getMessage());
        }
    }

    public static void addMen(){
        try (Connection connect = MyConnect.getConnection();
             Statement state = connect.createStatement()) {
            System.out.println("Successful connection.");
            state.executeUpdate(SQL_USE_DB);
            System.out.println("Successful start of using.");
            state.executeUpdate(SQL_INSERT_MEN);
            System.out.println("successful appending of men");

        } catch (SQLException | IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void addMan() {
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

//    private String deleteById = "DELETE FROM zoo_products.shop WHERE id = ";
////    private static final String SQL_PRODUCT_BY_ID = "DELETE FROM zoo_products.shop WHERE id = 4";
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter id that you want to delete.");
//        int userChoice = sc.nextInt();
//        try (Connection conn = ConnectToMyDB.getConnection();
//             Statement state = conn.createStatement()){
//            System.out.println("ConnectionMyDB to Store DB successful!");
//            int rows1 = state.executeUpdate("DELETE FROM zoo_products.shop WHERE number = " + userChoice);
////            int rows2 = state.executeUpdate(SQL_PRODUCT_BY_ID + userChoice);
//            System.out.printf("%d row(s) deleted", rows1);
//
//        } catch (Exception throwable) {
//            System.out.println("ConnectionMyDB failed...");
//            System.out.println(throwable.getMessage());
//        }
//    }

    public static void deleteById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which person do you want to delete?");
        int personToDelete = sc.nextInt();

        try (Connection connect = MyConnect.getConnection();
             Statement state = connect.createStatement()) {
            System.out.println("Successful connection.");
            state.executeUpdate(SQL_USE_DB);
            System.out.println("Successful start of using.");
            int row = state.executeUpdate(SQL_DELETE_BY_ID + personToDelete);
            System.out.printf("%d row(s) was/were deleted\n", row);
        } catch (SQLException | IOException exc) {
            System.out.println("There is no such person here");
            System.out.println(exc.getMessage());
        }
    }

    public static void showAll() {
        try (Connection connect = MyConnect.getConnection();
             Statement state = connect.createStatement()) {
            System.out.println("Successful connection.");
            state.executeUpdate(SQL_USE_DB);
            System.out.println("Successful start of using.");
            ResultSet resultSet = state.executeQuery(SQL_SHOW_ALL);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surName = resultSet.getString("surname");
                int age = resultSet.getInt("age");

                System.out.printf("| %3d | %10s | %10s | %d |\n", id, name, surName, age);
            }
        } catch (SQLException | IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void showMenu() {
        System.out.println("""
                -----------------------------------------
                Number |        Action                  |
                  1    |    to create a DB;             |
                  2    |    to create a table;          |
                  3    |    to add Men;                 |
                  4    |    to add a man;               |
                  5    |    to show the whole table;    |
                  6    |    to to delete by ID;    |
                  0    |    to exit.                    |
                 ----------------------------------------
                 Your choice:""");
    }

    public static void actions() {

        Scanner sc;
        int userChoice = -1;
        boolean working = true;

        do {
            sc = new Scanner(System.in);

            try {
                userChoice = sc.nextInt();
            }
            catch (InputMismatchException iME) {
                System.out.print("Wrong entering. Enter only numbers. ");
            }

            switch (userChoice) {
                case 1 -> {
                    createDataBase();
                    showMenu();
                }
                case 2 -> {
                    createTable();
                    showMenu();
                }
                case 3 -> {
                    addMen();
                    showMenu();
                }
                case 4 -> {
                    addMan();
                    showMenu();
                }
                case 5 -> {
                    showAll();
                    showMenu();
                }
                case 6 -> {
                    deleteById();
                    showMenu();
                }

                default ->
                    System.out.println("Enter a number from 0 to 5:");
                case 0 -> {
                    working = false;
                    System.out.println("EXIT...");
                }
            }
        }
        while (working);
    }

    public static void main(String[] args) {

        showMenu();
        actions();

    }
}