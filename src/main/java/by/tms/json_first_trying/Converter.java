package by.tms.json_first_trying;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Converter {

    private final static String baseFile = "user.json";

    public static void toJSON(User user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(baseFile), user);
//        System.out.println("A json file created.");
    }
    public static User toJavaObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
//        System.out.println("A mapper created.");
        return mapper.readValue(new File(baseFile), User.class);
    }
}

