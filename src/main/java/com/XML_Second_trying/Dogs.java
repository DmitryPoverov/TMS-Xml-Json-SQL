package com.XML_Second_trying;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name","weight","award"})
@XmlRootElement

public class Dogs {

    private String name;
    private int award;
    private double weight;

    public Dogs() {
    }

    public Dogs(String name1, int award2, double weight3) {
        this.name = name1;
        this.award = award2;
        this.weight = weight3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return name + " (" + award + " awards, " + weight + " kg)";
    }
}