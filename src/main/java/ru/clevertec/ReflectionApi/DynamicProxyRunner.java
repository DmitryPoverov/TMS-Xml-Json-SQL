package ru.clevertec.ReflectionApi;

import ru.clevertec.ReflectionApi.cat.Cat;
import ru.clevertec.ReflectionApi.proxies.CatHandler;

import java.lang.reflect.Proxy;
import java.util.Arrays;

public class DynamicProxyRunner {

    public static void main(String[] args) {

        Class<Cat> catClass = Cat.class;
        ClassLoader catClassLoader = catClass.getClassLoader();
        Class<?>[] catInterfaces = catClass.getInterfaces();

/*        Class<?>[] animalInterfaces = catClass.getSuperclass().getInterfaces();*/
/*        Arrays.stream(animalInterfaces).forEach(catInterface-> System.out.println(catInterface.getSimpleName()));*/

        Arrays.stream(catInterfaces).forEach(catInterface-> System.out.println(catInterface.getSimpleName()));

        Cat proxyCat = (Cat) Proxy.newProxyInstance(catClassLoader, catInterfaces, new CatHandler());
        String animalPublicString = proxyCat.animalPublic();
        System.out.println(animalPublicString);
    }
}
