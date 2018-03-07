package ru.geekbrains.java3.dz.dz4.TymkivVitaly;

public class Main {
    public static void main(String[] args) {
//        outString();
//        outFile();
//        deviceMFU();
        deviceMFU_3();
    }
    public static void outString() {
        outString w = new outString();
        Thread t1 = new Thread(() -> {
            w.printA();
        });
        Thread t2 = new Thread(() -> {
            w.printB();
        });
        Thread t3 = new Thread(() -> {
            w.printC();
        });
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void outFile() {
        outFile t = new outFile();
        Thread f1 = new Thread((Runnable) () ->  t.printToFile("Data1 "));
        Thread f2 = new Thread((Runnable) () ->  t.printToFile("Data2 "));
        Thread f3 = new Thread((Runnable) () ->  t.printToFile("Data3 "));
        f1.start();
        f2.start();
        f3.start();
        try {
            f1.join();
            f2.join();
            f3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void deviceMFU() {
        deviceMFU devicemfu = new deviceMFU();
        int counter = 30;
        new Thread(() -> devicemfu.printDocuments(counter)).start();
        new Thread(() -> devicemfu.scanDocuments(counter)).start();
    }
    public static void deviceMFU_3() {
        deviceMFU_3 devicemfu = new deviceMFU_3();
        new Thread(() -> {
            try {
                devicemfu.printDocuments(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                devicemfu.scanDocuments(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}