package by.enumPack;

public enum AccessoryEnum {

    FIRST_VAR(1),
    SECOND_VAR(2);

    int number;

    AccessoryEnum() {
    }

    AccessoryEnum(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
