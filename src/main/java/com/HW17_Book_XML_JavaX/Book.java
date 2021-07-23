package com.HW17_Book_XML_JavaX;

//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(propOrder = { "name", "year", "marking"})
@XmlRootElement( name = "book111" )
//@XmlAccessorType(XmlAccessType.FIELD)

public class Book {

    private int marking;
    private String name;
    private int year;
    private List<Book> theSameBooks = new ArrayList<>();

    public Book() {
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

    public void setMarking(int marking) {
        this.marking = marking;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void addBook(Book book) {
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
        return "№" + marking + " [" + name + ", " + year + "]"
                + (theSameBooks.size()==0? "." : (", same books: " + getBookName()));
    }
}