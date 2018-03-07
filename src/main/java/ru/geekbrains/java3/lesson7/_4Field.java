package ru.geekbrains.java3.lesson7;

import java.lang.reflect.Field;

public class _4Field {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class catClass = Cat.class;
        Field f = catClass.getDeclaredField("name");
        System.out.println(f.getName());
    }
}
