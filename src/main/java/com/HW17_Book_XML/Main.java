package com.HW17_Book_XML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

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

        book2.setTheSameBooks(book1);
        book2.setTheSameBooks(book2);
        book4.setTheSameBooks(book3);
        book4.setTheSameBooks(book4);

        System.out.println("\nThe original book: " + book1);
        System.out.println("The serialized book: " + book2);
        System.out.println("\nThe original book: " + book3);
        System.out.println("The serialized book: " + book4);
    }
}