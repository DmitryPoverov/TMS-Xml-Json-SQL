package com.HW17_Book_XML_JavaX;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;

@XmlType(propOrder = {"openingAge", "address", "books"})
@XmlRootElement(name = "library_TAG")

public class Library {

    String address;
    List<Book> books;
    int openingAge;

    public Library() {
    }

    public Library(String address, List<Book> books, int openingAge) {
        this.address = address;
        this.books = books;
        this.openingAge = openingAge;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }


    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getOpeningAge() {
        return openingAge;
    }

    public void setOpeningAge(int openingAge) {
        this.openingAge = openingAge;
    }

    @Override
    public String toString() {
        return address + " (" + openingAge + ").";
    }
}