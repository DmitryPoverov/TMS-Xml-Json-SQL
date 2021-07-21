package com.HW17_Book_Json;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private int marking;
    private String name;
    private int year;

    @JsonIgnore
    private List<Book> theSameBooks = new ArrayList<>();

    public Book() {
    }

    public Book(int marking, String name, int year) {
        this.marking = marking;
        this.name = name;
        this.year = year;
    }

    public int getMarking() {
        return marking;
    }
    public String getName() {
        return name;
    }
    public int getYear() {
        return year;
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
    public void setYear(int year) {
        this.year = year;
    }
    public void setTheSameBooks(Book book) {
        theSameBooks.add(book);
    }

    private String getBookName() {
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<theSameBooks.size(); i++) {
            if (i == theSameBooks.size() - 1) {
                sb.append(theSameBooks.get(i).getName() + '.');
            } else {
                sb.append(theSameBooks.get(i).getName() + ", ");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "№" + marking + " [" + name + ", " + year + "]"
                + (theSameBooks.size()==0? "." : (", same books: " + getBookName()));
    }
}