package com.MySQL_2_trying;

import java.sql.Connection;

public class Demo1_Connection_to_DB {
    public static void main(String[] args) {

        try (Connection conn = ConnectToMyDB.getConnection()){
            System.out.println("ConnectionMyDB to Store DB successful!");
        } catch (Exception e1) {
            System.out.println("ConnectionMyDB failed...");
            System.out.println(e1);
        }
    }
}
