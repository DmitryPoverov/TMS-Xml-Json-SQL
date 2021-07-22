package com.XML_Third_trying;

public class Person {

    private String firstNAme;
    private String lastName;

    public Person() {
    }

    public Person(String firstNAme, String lastName) {
        this.firstNAme = firstNAme;
        this.lastName = lastName;
    }

    public String getFirstNAme() {
        return firstNAme;
    }

    public void setFirstNAme(String firstNAme) {
        this.firstNAme = firstNAme;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
