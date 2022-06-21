package ru.clevertec.ReflectionApi.cat;

import ru.clevertec.ReflectionApi.animal.Animal;

public class Cat extends Animal implements CatInterface {

    String name;
    public String name1;
    protected String name2;
    private String name3;

    public Cat() {
    }

    public Cat(int age, int age1, int age2, int age3, String name, String name1, String name2, String name3) {
        super(age, age1, age2, age3);
        this.name = name;
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
    }

    @Override
    public String animalPublic() {
        return "Cat animalPublic";
    }

    @Override
    protected String animalProtected() {
        return "Cat animalProtected";
    }

    String catDefault() {
        return "catDefault";
    }

    public String catPublic() {
        return "catPublic";
    }

    protected String catProtected() {
        return "catProtected";
    }

    private String catPrivate() {
        return "catPrivate";
    }

    private String catPrivateParameter(int number, Boolean flag) {
        return "catPrivate, " + number + ", " + flag;
    }

    @Override
    public String toString() {
        return "Cat{" + name + ", " + name1 + ", " + name2 + ", " + name3 + ", " + super.toString() + '}';
    }
}
