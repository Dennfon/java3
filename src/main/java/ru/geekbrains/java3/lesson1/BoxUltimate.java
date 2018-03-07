package ru.geekbrains.java3.lesson1;

/**
 * Created by i on 18.12.2017.
 */
public class BoxUltimate<T> {
    private T obj;

    public BoxUltimate(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public void info() {
        System.out.println("obj: " + obj);
        System.out.println("type: " + obj.getClass());
    }
}

