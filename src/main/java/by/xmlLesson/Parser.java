package by.xmlLesson;

import by.xmlLesson.model.People;
import by.xmlLesson.model.Root;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final String TAG_MAIN_NAME = "name";
    private static final String TAG_PEOPLE = "people";
    private static final String TAG_ELEMENT = "element";
    private static final String TAG_AGE = "age";
    private static final String TAG_NAME = "name";

    public static Root parse() {

        Document doc;
        try {
            doc = buildDocument();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

// Выводим на печать все элементы с именем "name".
        var elementsByTagName = doc.getElementsByTagName("name");
        for (int i=0; i<elementsByTagName.getLength(); i++) {
            System.out.println(i + ": " + elementsByTagName.item(i).getNodeName());
        }

// Из документа doc извлекаем первый элемент и кладем его в rootNode
        var rootNode = doc.getFirstChild();
        System.out.println("The name of the first node is: " + rootNode.getNodeName());

        String mainName = null;
        Node peopleNode = null;

// из этого корневого элемента извлекаем все дочерние элементы и кладем в Node List и достаем их через цикл.
        var childNodes = rootNode.getChildNodes();

        for (int i=0; i<childNodes.getLength(); i++) {
            if(childNodes.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            System.out.println(childNodes.item(i).getNodeName());
            switch (childNodes.item(i).getNodeName()) {
                case TAG_MAIN_NAME -> mainName = childNodes.item(i).getTextContent();
                case TAG_PEOPLE -> peopleNode = childNodes.item(i);
            }
        }

        System.out.println("A main node content is: " + mainName);

        if (peopleNode == null) {
            return null;
        }

        Root root = new Root();

        List<People> peopleList = parsePeopleList(peopleNode);

        root.setName(mainName);
        root.setPeople(peopleList);
        System.out.println("Root: " + root);
        return root;
    }

    private static List<People> parsePeopleList(Node peopleNode) {
        List<People> peopleList = new ArrayList<>();
        var peopleChildren = peopleNode.getChildNodes();

        for (int i=0; i<peopleChildren.getLength(); i++) {
            if (peopleChildren.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            if (!peopleChildren.item(i).getNodeName().equals(TAG_ELEMENT)) {
                continue;
            }

            People people = parseElement(peopleChildren.item(i));
            peopleList.add(people);
        }
        return peopleList;
    }

    public static People parseElement(Node elementNode) {
        int age = 0;
        String name = "";
        NodeList elementNodes = elementNode.getChildNodes();
        for (int j=0; j<elementNodes.getLength(); j++) {
            if (elementNodes.item(j).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            switch (elementNodes.item(j).getNodeName()) {
                case TAG_AGE -> age = Integer.valueOf(elementNodes.item(j).getTextContent());
                case TAG_NAME -> name = elementNodes.item(j).getTextContent();
            }
        }
        var people = new People(age, name);
        return people;
    }

    private static Document buildDocument() throws Exception{
        var file = new File("src/main/resources/xmlTrying.xml");
        var dbf = DocumentBuilderFactory.newInstance();
        Document doc;
        // В документ, из DocumentBuilderFactory, кладем распаршенное через newDocumentBuilder() значение файла file.
        doc = dbf.newDocumentBuilder().parse(file);
        return doc;
    }

}
