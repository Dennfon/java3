package ru.geekbrains.java3.dz.dz4.TymkivVitaly;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class outFile {
    private final Object obj = new Object();

    public void printToFile(String s) {
        File file = new File("E:/soft/JAVA/java3/java3/src/main/java/ru/geekbrains/java3/dz/dz4/TymkivVitaly/test.txt");
        BufferedWriter bw = null;
        for (int i = 0; i < 10; i++) {
            synchronized (obj) {
                try  {
                    try {
                        bw = new BufferedWriter(new FileWriter(file, true));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    bw.write(s +  System.getProperty("line.separator"));
                    System.out.println(s + " print N " + i);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        bw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}