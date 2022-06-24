package by.tms.xml.xml_first_trying;

import by.tms.xml.xml_first_trying.Cat.Cat;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) {

        Cat cat1 = new Cat();

        cat1.setName("Tomas");
        cat1.setAge(8);
        cat1.setWeight(6);

        String resultXML = "";

        StringWriter sw = new StringWriter();

        try {
            JAXBContext context = JAXBContext.newInstance(Cat.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(cat1, sw);

            resultXML = sw.toString();

        } catch (JAXBException jaxbException) {
            System.out.println(jaxbException.getMessage());
        }

        System.out.println("=====" + resultXML);

        Cat cat2 = new Cat();
        StringReader sr = new StringReader(resultXML);

        try {
            JAXBContext context = JAXBContext.newInstance(Cat.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            cat2 = (Cat) unmarshaller.unmarshal(sr);


        } catch (JAXBException jaxbException) {
            System.out.println(jaxbException.getMessage());
        }

        System.out.println(resultXML);
        System.out.println("Cat1: " + cat1);
        System.out.println("Cat2: " + cat2);

    }
}
