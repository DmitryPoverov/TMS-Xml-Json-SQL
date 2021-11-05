package by.aquariumTask.employees;

import by.epamTask.model.ItEmployees;

public class Developers extends ItEmployees {

    private String name;
    private int salary;

    public Developers() {
    }

    public Developers(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }
    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Developers: " + name + ", " + salary + "$";
    }
}
