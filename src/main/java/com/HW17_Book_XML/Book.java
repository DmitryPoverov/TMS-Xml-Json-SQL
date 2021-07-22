package com.HW17_Book_XML;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(propOrder = { "name", "yea", "marking"})
@XmlRootElement( name = "book111" )

public class Book {

    private int marking;
    private String name;
    private int yea;
    private List<Book> theSameBooks = new ArrayList<>();

    public Book() {
    }

    public Book(int marking, String name, int yea) {
        this.marking = marking;
        this.name = name;
        this.yea = yea;
    }

    public int getMarking() {
        return marking;
    }
    public String getName() {
        return name;
    }
    public int getYear() {
        return yea;
    }
    public void getSameBooks() {
        for (Book b : theSameBooks) {
            System.out.println(b);
        }
    }

    public void setMarking(int marking) {
        this.marking = marking;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setYear(int yea) {
        this.yea = yea;
    }
    public void setTheSameBooks(Book book) {
        theSameBooks.add(book);
    }

    private String getBookName() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<theSameBooks.size(); i++) {
            if (i == theSameBooks.size() - 1) {
                sb.append(theSameBooks.get(i).getName()).append('.');
            } else {
                sb.append(theSameBooks.get(i).getName()).append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "№" + marking + " [" + name + ", " + yea + "]"
                + (theSameBooks.size()==0? "." : (", same books: " + getBookName()));
    }
}