package by.tms.xml.xml_second_trying;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name","weight","award"})
@XmlRootElement

public class Dogs {

    private String name;
    private int award;
    private double weight;

    public Dogs() {
    }

    public Dogs(String name, int award, double weight) {
        this.name = name;
        this.award = award;
        this.weight = weight;
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