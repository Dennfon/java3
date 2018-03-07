package ru.geekbrains.java3.dz.dz4.TymkivVitaly;

public class deviceMFU {
    private boolean  valueSet  = false;
//    простая реализация

    public synchronized void printDocuments(int count) {
                for (int i = 0; i < count; i++) {
                    while (!valueSet) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    valueSet = false;
                    this.notifyAll();
                    try {
                        Thread.sleep(50);
                        System.out.println("распечатано " + (i + 1) + " страниц");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
    }

    public synchronized void scanDocuments(int count) {
                for (int i = 0; i < count; i++) {
                        while (valueSet) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    valueSet = true;
                    this.notifyAll();
                    try {
                        Thread.sleep(50);
                        System.out.println("отсканировано " + (i + 1) + " страниц");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
    }
}