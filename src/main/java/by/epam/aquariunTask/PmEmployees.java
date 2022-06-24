package by.epam.aquariunTask;

import by.epam.companyTask.model.ItEmployees;

public class PmEmployees extends ItEmployees {

    private String name;
    private int salary;

    public PmEmployees() {
    }

    public PmEmployees(String name, int salary) {
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
        return "ProjectManager: " + name + ", " + salary + "$";
    }
}
