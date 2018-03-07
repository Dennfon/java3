package ru.geekbrains.java3.dz.dz4.TymkivVitaly;

public class outString {
    private final Object obj = new Object();
    private volatile char currentLetter = 'A';

    public void printA() {
        synchronized (obj) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') obj.wait();
                    System.out.print("A");
                    currentLetter = 'B';
                    obj.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (obj) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') obj.wait();
                    System.out.print("B");
                    currentLetter = 'C';
                    obj.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (obj) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C') obj.wait();
                    System.out.print("C");
                    currentLetter = 'A';
                    obj.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
