package ru.clevertec.ReflectionApi;

import ru.clevertec.ReflectionApi.cat.Cat;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionRunner {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        System.out.println("Examples with class Class instances");

        Class<?> class1 = Class.forName("ru.clevertec.ReflectionApi.cat.Cat");

        Cat tomCat = new Cat(0, 1, 2, 3,  "Tom", "Tom1", "Tom2", "Tom3");
        Class<? extends Cat> class2 = tomCat.getClass();

        Class<Cat> class3 = Cat.class;

        System.out.println("Way 1: " + class1);
        System.out.println("Way 2: " + class2);
        System.out.println("Way 3: " + class3);

        System.out.println("\n********************************************************\n");
        System.out.println("Examples with subClass.\n");

        Field[] fields = class3.getFields();
        Field[] class3DeclaredFields = class3.getDeclaredFields();
        Method[] methods = class3.getMethods();
        Method[] declaredMethods = class3.getDeclaredMethods();

        Arrays.stream(fields)
                .forEach(field -> System.out.println(class3.getSimpleName() + " public field: " + field.getName()));

        Arrays.stream(class3DeclaredFields)
                .forEach(field -> System.out.println(class3.getSimpleName() +  " any field: " + field.getName()));

        Arrays.stream(methods)
                        .forEach(method ->
                                System.out.println(class3.getSimpleName() +  " public method: " + method.getName()));
        Arrays.stream(declaredMethods)
                        .forEach(method ->
                                System.out.println(class3.getSimpleName() +  " any method: " + method.getName()));

        Method catPublic = class3.getMethod("catPublic");
        System.out.println("*** " + catPublic.getName() +" ***");

        Method catPrivate = class3.getDeclaredMethod("catPrivateParameter", int.class, Boolean.class);
        catPrivate.setAccessible(true);
        Object invoke = catPrivate.invoke(tomCat, 123, true);
        System.out.println("*** " + invoke + " ***");

        System.out.println("\n********************************************************\n");
        System.out.println("Examples with superClass.\n");

/*      Both methods return <? extends Cat>, so We can't be sure that we got correct type.
        Class<? super Cat> superclass = Cat.class.getSuperclass();
        Class<? super Cat> superclass = class3.getSuperclass();
*/
        Class<? super Cat> superclass = class3.getSuperclass();

        Field[] catSuperFields = class3.getSuperclass().getFields();
        Field[] catSuperDeclaredFields = class3.getSuperclass().getDeclaredFields();

        Arrays.stream(catSuperFields)
                .forEach(field -> System.out.println(superclass.getSimpleName() + " public field: " + field.getName()));

        Arrays.stream(catSuperDeclaredFields)
                .forEach(field -> System.out.println(superclass.getSimpleName() + " any field: " + field.getName()));

        Field catName3 = class3.getDeclaredField("name3");
        catName3.setAccessible(true);
        String o = (String) catName3.get(tomCat);
        System.out.println(o);
        o = "Jerry3";
        System.out.println(o);

        System.out.println("\n********************************************************\n");
        System.out.println("Examples with a new instance without a constructor.\n");

        Class<Cat> catClass = Cat.class;

// It's better to use constructor and invoke newInstance() on it
//        Cat newCatInstance = catClass.newInstance();            // this method is deprecated

        Cat newCatInstance = catClass.getConstructor().newInstance();
        System.out.println(newCatInstance);

        Field[] superDeclaredFields = catClass.getSuperclass().getDeclaredFields();
        Field[] declaredFields = catClass.getDeclaredFields();

        String[] stringArgs = {"tom", "tom1", "tom2", "tom3"};
        int[] intArgs = {0, 1, 2, 3};

        for (int i=0; i<declaredFields.length; i++) {
            superDeclaredFields[i].setAccessible(true);
            superDeclaredFields[i].set(newCatInstance, intArgs[i]);
            declaredFields[i].setAccessible(true);
            declaredFields[i].set(newCatInstance, stringArgs[i]);
        }
        System.out.println(newCatInstance);

        Field name3 = catClass.getDeclaredField("name3");
        name3.setAccessible(true);
        name3.set(newCatInstance, "TOMAS");

        System.out.println(newCatInstance);

        System.out.println("\n********************************************************\n");
        System.out.println("Examples with a new instance with a constructor.\n");

        Class<?>[] catArgs = new Class[]
                {int.class, int.class, int.class, int.class, String.class, String.class, String.class, String.class};

        Cat newCatInstanceWithConstructor =
                catClass.getConstructor(catArgs).newInstance(0, 1, 2, 3, "tommy", "tommy1", "tommy2", "tommy3");

        System.out.println(newCatInstanceWithConstructor);


    }
}
