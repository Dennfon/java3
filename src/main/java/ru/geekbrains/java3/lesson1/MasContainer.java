package ru.geekbrains.java3.lesson1;

import java.lang.reflect.Array;

/**
 * Created by i on 18.12.2017.
 */
public class MasContainer<T extends Number> {
    private T[] mas;

    public double avg() {
        if (mas.length == 0) {
            return 0.0;
        }
        double sum = 0.0;
        for (T item : mas) {
            sum += item.doubleValue();
        }
        return sum / mas.length;
    }

//    public boolean equalsAvg(MasContainer<? extends Number> masContainer) {
//        return avg() == masContainer.avg();
//    }

    public <M extends Number> boolean equalsAvg(MasContainer<M> masContainer) {
        return avg() == masContainer.avg();
    }


    public MasContainer(T... mas) {
        this.mas = mas;
    }

    public T[] getMas() {
        return mas;
    }

    public void setMas(T[] mas) {
        this.mas = mas;
    }
}
