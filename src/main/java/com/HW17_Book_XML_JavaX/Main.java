package com.HW17_Book_XML_JavaX;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

        try {
            JAXBContext context = JAXBContext.newInstance(Book.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(book1, new File("Book1.xml"));
            marshaller.marshal(book1, System.out);
        } catch (JAXBException jaxbException) {
            System.out.println(jaxbException.getMessage());
        }

        try {
            JAXBContext context = JAXBContext.newInstance(Book.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            book2 = (Book) unmarshaller.unmarshal(new File("Book1.xml"));
        } catch (JAXBException jaxbException) {
            System.out.println(jaxbException.getMessage());
        }

        try {
            JAXBContext context = JAXBContext.newInstance(Book.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(book3, new File("Book3.xml"));
            marshaller.marshal(book3, System.out);
        } catch (JAXBException jaxbException) {
            System.out.println(jaxbException.getMessage());
        }

        try {
            JAXBContext context = JAXBContext.newInstance(Book.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            book4 = (Book) unmarshaller.unmarshal(new File("Book3.xml"));
        } catch (JAXBException jaxbException) {
            System.out.println(jaxbException.getMessage());
        }

        book2.addBook(book1);
        book2.addBook(book2);
        book4.addBook(book3);
        book4.addBook(book4);

        System.out.println("\nThe original book: " + book1);
        System.out.println("The serialized book: " + book2);
        System.out.println("\nThe original book: " + book3);
        System.out.println("The serialized book: " + book4);

        List<Book> bList1 = new ArrayList<>();
        List<Book> bList2 = new ArrayList<>();

        bList1.add(book1);
        bList1.add(book2);
        bList1.add(book3);

        bList2.add(book1);
        bList2.add(book2);
        bList2.add(book3);
        bList2.add(book4);

        Library library1 = new Library();
        library1.setAddress("Brookline str,14");
        library1.setBooks(bList1);
        library1.setOpeningAge(1983);
        Library library2 = new Library("Manchester str,89", bList2, 1897);

        Library library3 = new Library();
        Library library4 = new Library();

        System.out.println();

        try {
            JAXBContext context = JAXBContext.newInstance(Library.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(library1, new File("LibraryProperties.xml"));
            marshaller.marshal(library1, System.out);

        } catch (JAXBException jaxbException) {
            System.out.println(jaxbException.getMessage());
        }

        try {
            JAXBContext context = JAXBContext.newInstance(Library.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            library3 = (Library) unmarshaller.unmarshal(new File("LibraryProperties.xml"));
        } catch (JAXBException jaxbException) {
            System.out.println(jaxbException.getMessage());
        }

        try {
            JAXBContext context = JAXBContext.newInstance(Library.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(library2, new File("LibraryProperties.xml"));
            marshaller.marshal(library2, System.out);
        } catch (JAXBException jaxbException) {
            System.out.println(jaxbException.getMessage());
        }

        try {
            JAXBContext context = JAXBContext.newInstance(Library.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            library4 = (Library) unmarshaller.unmarshal(new File("LibraryProperties.xml"));
        } catch (JAXBException jaxbException) {
            System.out.println(jaxbException.getMessage());
        }

        System.out.println("\nLibrary 1 (Original): " + library1);
        System.out.println(  "Library 2 (Original): " + library2);
        System.out.println("Library 3 (Serialized): " + library3);
        System.out.println("Library 4 (Serialized): " + library4);
    }
}