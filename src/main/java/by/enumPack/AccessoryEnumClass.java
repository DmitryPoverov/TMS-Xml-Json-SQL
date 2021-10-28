package by.enumPack;

public class AccessoryEnumClass {

    public static final AccessoryEnumClass FIRST_VAR = new AccessoryEnumClass("text1");
    public static final AccessoryEnumClass SECOND_VAR = new AccessoryEnumClass("text2");
    public static final AccessoryEnumClass THIRD_VAR = new AccessoryEnumClass("text3");

    private String text;

    public AccessoryEnumClass(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "AccessoryEnum{" + text + "}";
    }
}
