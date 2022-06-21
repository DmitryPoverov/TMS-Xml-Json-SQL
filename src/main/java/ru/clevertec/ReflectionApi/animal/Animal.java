package ru.clevertec.ReflectionApi.animal;

public class Animal/* implements AnimalInterface*/ {

    int age;
    public int age1;
    protected int age2;
    private int age3;

    public Animal() {
    }

    public Animal(int age, int age1, int age2, int age3) {
        this.age = age;
        this.age1 = age1;
        this.age2 = age2;
        this.age3 = age3;
    }

    String animalDefault() {
        return "animalDefault";
    }

    public String animalPublic() {
        return "animalPublic";
    }

    protected String animalProtected() {
        return "animalProtected";
    }

    private String animalPrivate() {
        return "animalPrivate";
    }

    @Override
    public String toString() {
        return "Animal{" + age + ", " + age1 + ", " + age2 + ", " + age3 + '}';
    }
}
