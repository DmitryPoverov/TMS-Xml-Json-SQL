package by.epamTask.model;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ItCompanyMain {

    public static void main(String[] args) {

        List<ItEmployees> itEmployeesList = new ArrayList<>();

        Document doc = null;

        try {
            doc = buildDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }

        var firstChild = doc.getFirstChild();
        var rootChildren = firstChild.getChildNodes();

        Node employee = null;

        for (int i = 0; i < rootChildren.getLength(); i++) {
            if (rootChildren.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            if (rootChildren.item(i).getNodeName().equals("employee")) {
                employee = rootChildren.item(i);
            }

            if (employee == null) {
                return;
            }

            String name = "";
            String profession = "";
            int salary = 0;

            var childNodes = employee.getChildNodes();

            for (int j = 0; j < childNodes.getLength(); j++) {
                if (childNodes.item(j).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                switch (childNodes.item(j).getNodeName()) {
                    case "name" -> name = childNodes.item(j).getTextContent();
                    case "profession" -> profession = childNodes.item(j).getTextContent();
                    case "salary" -> salary = Integer.parseInt(childNodes.item(j).getTextContent());
                }
            }

            var itEmployee = new ItEmployees(name, profession, salary);

            itEmployeesList.add(itEmployee);
        }

        Team team1 = new Team(createTeam(itEmployeesList));
        Team team2 = new Team(createTeam(itEmployeesList));

        totalCounter(team1);
        totalCounter(team2);

        Path path1 = Path.of("src", "main", "resources", "team1.xml");
        Path path2 = Path.of("src", "main", "resources", "team2.xml");

        teamMarshaller(team1, path1);
        teamMarshaller(team2, path2);
    }

    private static void totalCounter(Team team) {

        for(int i=0; i<team.getTeam().size(); i++) {
            if(team.getTeam().get(i).getProfession().equals("QA")) {
                team.setTotalQA(1);
                team.setTotalSalary(team.getTeam().get(i).getSalary());
            }
            if(team.getTeam().get(i).getProfession().equals("Developer")) {
                team.setTotalDev(1);
                team.setTotalSalary(team.getTeam().get(i).getSalary());
            }
            if(team.getTeam().get(i).getProfession().equals("PM")) {
                team.setTotalPM(1);
                team.setTotalSalary(team.getTeam().get(i).getSalary());
            }
        }
    }

    private static void teamMarshaller(Team team1, Path path) {
        String resultXML = "";

        StringWriter sw = new StringWriter();

        try {
            JAXBContext context = JAXBContext.newInstance(Team.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(team1, sw);

            resultXML = sw.toString();

        } catch (JAXBException jaxbException) {
            System.out.println(jaxbException.getMessage());
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(resultXML);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<ItEmployees> createTeam(List<ItEmployees> itEmployeeList) {
        boolean needInTeam = true;
        var list = new ArrayList<ItEmployees>();

        for (int i = 0; i < itEmployeeList.size(); i++) {

            if (itEmployeeList.get(i).getProfession().equals("QA")) {
                for (ItEmployees itEmployees : list) {
                    needInTeam = !itEmployees.getProfession().equals("QA");
                }
                if (needInTeam) {
                    list.add(itEmployeeList.get(i));
                    itEmployeeList.remove(i);
                }
                needInTeam = true;
            }

            if (itEmployeeList.get(i).getProfession().equals("Developer")) {
                for (ItEmployees itEmployees : list) {
                    needInTeam = !itEmployees.getProfession().equals("Developer");
                }
                if (needInTeam) {
                    list.add(itEmployeeList.get(i));
                    itEmployeeList.remove(i);
                }
                needInTeam = true;
            }

            if (itEmployeeList.get(i).getProfession().equals("PM")) {
                for (ItEmployees itEmployees : list) {
                    needInTeam = !itEmployees.getProfession().equals("PM");
                }
                if (needInTeam) {
                    list.add(itEmployeeList.get(i));
                    itEmployeeList.remove(i);
                }
            }
        }
        return list;
    }

    private static Document buildDocument() throws Exception {
        var file = new File("src/main/resources/itCompany.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        return dbf.newDocumentBuilder().parse(file);
    }
}
