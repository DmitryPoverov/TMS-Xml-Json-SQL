package by.epamTask.model;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)

public class Team {

    @XmlElement(name = "employee")
    private List<ItEmployees> team;

    private int totalSalary = 0;
    private int totalQA = 0;
    private int totalDev = 0;
    private int totalPM = 0;

    public Team() {
    }

    public Team(List<ItEmployees> team) {
        this.team = team;
    }

    public List<ItEmployees> getTeam() {
        return team;
    }

    public void setTeam(List<ItEmployees> team) {
        this.team = team;
    }

    public void setTotalSalary(int totalSalary) {
        this.totalSalary += totalSalary;
    }

    public void setTotalQA(int totalQA) {
        this.totalQA += totalQA;
    }

    public void setTotalDev(int totalDev) {
        this.totalDev += totalDev;
    }

    public void setTotalPM(int totalPM) {
        this.totalPM += totalPM;
    }

    @Override
    public String toString() {
        return "team=" + team  ;
    }
}
