package ru.geekbrains.java3.lesson6.logging;

import java.util.logging.Level;
import java.util.logging.Logger;
// Levels:
// OFF
// SEVERE
// WARNING
// INFO
// CONFIG
// FINE
// FINER
// FINEST
// ALL
public class Example1Base {
    private static final Logger logger = Logger.getLogger(Example1Base.class.getName());

    public static void main(String[] args) {
        Example1Base ex1 = new Example1Base();
        ex1.tryToDivBy0();
    }

    public void tryToDivBy0() {
        try{
            int a = 5 / 0;
        } catch (ArithmeticException e) {
            logger.log(Level.SEVERE, "Ошибка деления на ноль", e);
        }
    }
}
