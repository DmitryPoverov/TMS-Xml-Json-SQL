package by.enumPack;

public class EnumUsing {

    public static void main(String[] args) {

        AccessoryEnumClass firstVar = AccessoryEnumClass.FIRST_VAR;
        System.out.println(AccessoryEnumClass.FIRST_VAR);

        AccessoryEnumClass.FIRST_VAR.setText("NewText1");
        System.out.println(AccessoryEnumClass.FIRST_VAR);

        String str = AccessoryEnumClass.FIRST_VAR.getText();
        NotEnum ne = new NotEnum();

        ne.setStr(AccessoryEnumClass.FIRST_VAR.getText());

        int num = AccessoryEnum.FIRST_VAR.getNumber();
        System.out.println(num);

        AccessoryEnum using1 = AccessoryEnum.FIRST_VAR;
        AccessoryEnum using2 = AccessoryEnum.FIRST_VAR;

        System.out.println(using1.getNumber());
        System.out.println(using2.getNumber());

        AccessoryEnum.FIRST_VAR.setNumber(111);

        System.out.println(using1.getNumber());
        System.out.println(using2.getNumber());

        using1.setNumber(222);

        System.out.println(using1.getNumber());
        System.out.println(using2.getNumber());
        System.out.println(AccessoryEnum.FIRST_VAR.getNumber());
    }
}
