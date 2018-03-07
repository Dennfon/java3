package ru.geekbrains.java3.lesson5.homework;


public class Task2 {
    public static void main(String[] args) {
        Task2 task2 = new Task2();
        System.out.println("Start");
        new Thread(() -> task2.method()).start();
        new Thread(() -> task2.method()).start();
        new Thread(() -> task2.method()).start();
        System.out.println("End");

    }

    public void method() {
        for (int i = 0; i < 10; i++) {
            synchronized (this) {
                System.out.println("Строка " + i);
            }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }
}
