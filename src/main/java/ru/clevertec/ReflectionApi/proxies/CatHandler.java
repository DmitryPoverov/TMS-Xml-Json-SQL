package ru.clevertec.ReflectionApi.proxies;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CatHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Any method will be processed here.");
        return null;
    }
}
