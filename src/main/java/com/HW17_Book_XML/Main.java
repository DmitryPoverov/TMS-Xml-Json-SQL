package com.HW17_Book_XML;

import com.HW17_Book_Json.Book;

public class Main {
    public static void main(String[] args) {

        com.HW17_Book_Json.Book book1 = new com.HW17_Book_Json.Book();
        com.HW17_Book_Json.Book book2 = new com.HW17_Book_Json.Book();
        com.HW17_Book_Json.Book book3 = new com.HW17_Book_Json.Book();
        com.HW17_Book_Json.Book book4 = new Book();

        book1.setMarking(10001);
        book1.setName("Cinderella");
        book1.setYear(2020);

        book3.setMarking(10002);
        book3.setName("Rapunzel");
        book3.setYear(2018);

    }
}
