package by.tms.xml_second_trying;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        Dogs dog1 = new Dogs("Toby", 3, 19.028);

        try {
            JAXBContext context = JAXBContext.newInstance(Dogs.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(dog1, new File("DogProperty.xml"));
//            marshaller.marshal(dog1, System.out);
            System.out.println("\nXML file wrote successfully.\n");

        } catch (JAXBException jaxbException) {
            System.out.println(jaxbException.getMessage());
        }

        try {
            int c;
            BufferedReader bf = new BufferedReader(new FileReader("DogProperty.xml"));
            System.out.println("The current file content:");
            while ((c = bf.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (IOException iOE) {
            System.out.println(iOE.getMessage());
        }
    }
}