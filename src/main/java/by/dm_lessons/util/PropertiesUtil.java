package by.dm_lessons.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    // в скобках - значение, которое нужно найти в PROPERTIES файле
    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    // inputStream нужно закрывать, поэтому используем try с ресурсами
    // load() считывает из resourceAsStream ранее считанные настройки
    private static void loadProperties() {
        try (InputStream resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
