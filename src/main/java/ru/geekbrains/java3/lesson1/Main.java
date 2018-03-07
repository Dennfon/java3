package ru.geekbrains.java3.lesson1;

import java.util.List;
import java.util.Map;

/**
 * Created by i on 18.12.2017.
 */
public class Main {
    public static void main(String[] args) {
//        Box box1 = new Box(1);
//        Box box2 = new Box("str");
//        box1.info();
//        box2.info();
////        ... много кода
//        int x = 10;
//        x = x + (Integer) box1.getObj();
//        System.out.println("x= " + x);
//
//        x = 10;
//        x = x + (Integer) box2.getObj();
//        System.out.println("x= " + x);

//        BoxInt boxInt1 = new BoxInt(1);
//        BoxInt boxInt2 = new BoxInt(5);
//        boxInt1.info();
//        boxInt2.info();
////        ... много кода
//        int x = 10;
//        x = x + boxInt1.getObj();
//        System.out.println("x= " + x);
//
//        x = 10;
//        x = x + boxInt2.getObj();
//        System.out.println("x= " + x);


//        BoxUltimate<Integer> intBox = new BoxUltimate<>(10);
//        int d = intBox.getObj();
//        intBox.info();
//        int x = 10;
//        x = x + intBox.getObj();
//        System.out.println("x= " + x);
//
//        BoxUltimate<String> strBox = new BoxUltimate<>("fdjf");
//        strBox.info();


//        BoxNumber<Double> doubleBoxNumber = new BoxNumber<>(45.45);
//        doubleBoxNumber.info();
//        System.out.println(doubleBoxNumber.rang());


        MasContainer<Double> doubleMasContainer = new MasContainer<>(1.0, 2.0, 3.0, 4.0);
        System.out.println(doubleMasContainer.avg());

        MasContainer<Integer> integerMasContainer = new MasContainer<>(1, 2, 3, 4);
        System.out.println(integerMasContainer.avg());

        boolean result = integerMasContainer.equalsAvg(doubleMasContainer);
        System.out.println(result);




    }
}
