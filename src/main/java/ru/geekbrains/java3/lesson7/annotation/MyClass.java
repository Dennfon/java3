package ru.geekbrains.java3.lesson7.annotation;

import ru.geekbrains.java3.lesson7.annotation.advanced.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClass {

    @MarkingAnnotation
    public void markedMethod() {
        System.out.println("Java");
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        MyClass myClass = new MyClass();
        Method[] methods = MyClass.class.getDeclaredMethods();
        for (Method o : methods) {
            if (o.getAnnotation(MarkingAnnotation.class) != null) {
                System.out.println(o);
                o.invoke(myClass);
            }
        }
    }

    public void markedMethod2() {

        System.out.println("Java2");
    }

    @Test
    public void markedMethod3() {
        System.out.println("Java3");
    }

}
