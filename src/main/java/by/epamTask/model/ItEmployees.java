package by.epamTask.model;

import jakarta.xml.bind.annotation.*;

@XmlType(propOrder = {"name","profession","salary"})
@XmlAccessorType(XmlAccessType.FIELD)

public class ItEmployees {

    private String name;
    private String profession;
    private int salary;

    public ItEmployees() {
    }

    public ItEmployees(String name, String profession, int salary) {
        this.name = name;
        this.profession = profession;
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }
    public String getProfession() {
        return profession;
    }
    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "[" + name + ", " + profession + ", " + salary + "$" + "]";
    }
}
