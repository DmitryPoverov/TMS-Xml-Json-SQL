package com.Json_Second_trying.Main;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        Book book4 = new Book();

        book1.setMarking(10001);
        book1.setName("Cinderella");
        book1.setYear(2020);

        book3.setMarking(10002);
        book3.setName("Rapunzel");
        book3.setYear(2018);

        ObjectMapper oMapper = new ObjectMapper();

        try {
            oMapper.writeValue(new File("Json2_1.json"), book1);
        } catch (IOException iOE) {
            System.out.println(iOE.getMessage());
        }

        try {
            book2 = oMapper.readValue(new File("Json2_1.json"), Book.class);
        } catch (IOException iOE) {
            System.out.println(iOE.getMessage());
        }

        try {
            oMapper.writeValue(new File("Json2_2.json"), book3);
        } catch (IOException iOE) {
            System.out.println(iOE.getMessage());
        }

        try {
            book4 = oMapper.readValue(new File("Json2_2.json"), Book.class);
        } catch (IOException iOE) {
            System.out.println(iOE.getMessage());
        }

        System.out.println("\nBook1: " + book1);
        System.out.println("Book2: " + book2);
        System.out.println("Book3: " + book3);
        System.out.println("Book4: " + book4);

        book1.setTheSameBooks(book1);
        book1.setTheSameBooks(book2);
        book2.setTheSameBooks(book1);
        book2.setTheSameBooks(book2);
        book3.setTheSameBooks(book3);
        book3.setTheSameBooks(book4);
        book4.setTheSameBooks(book3);
        book4.setTheSameBooks(book4);

        System.out.println("\nBook1: " + book1);
        System.out.println("Book2: " + book2);
        System.out.println("Book3: " + book3);
        System.out.println("Book4: " + book4);
    }
}
