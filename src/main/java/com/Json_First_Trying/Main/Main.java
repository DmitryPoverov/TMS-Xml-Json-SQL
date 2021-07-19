package com.Json_First_Trying.Main;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        ObjectMapper objMapper = new ObjectMapper();

        User user1 = new User();
        user1.setId(1000002562L);
        user1.setName("Marry");
        user1.setPhone("+37529-711-77-17");

        User user2 = new User();

        try {
            String resultString = objMapper.writeValueAsString(user1);
            System.out.println("User1: " + resultString);
            Converter.toJSON(user1);
            user2 = Converter.toJavaObject();
        } catch (IOException iOE) {
            System.out.println(iOE.getMessage());
        }

        user2.setLevel("Middle");
        System.out.println("User2: " + user2);

        User user3 = new User();
        user3.setId(10002556);
        user3.setName("Sybil");
        user3.setPhone("+375-29-889-78-67");
        user3.setLevel("Countess 1");

        try {
            objMapper.writeValue(new File("Json2.json"), user3);
        } catch (IOException iOE) {
            System.out.println(iOE.getMessage());
        }

        User user4 = new User();
        try {
            user4 = objMapper.readValue(new File("Json2.json"), User.class);
        } catch (IOException iOE) {
            System.out.println(iOE.getMessage());
        }
        user4.setLevel("Countess 2");
        System.out.println("user4: " + user4);
    }
}
