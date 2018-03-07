package ru.geekbrains.java3.dz.dz3.TymkivVitaly;

import java.io.*;
        import java.nio.file.*;
        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.Enumeration;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        readFileAsByteArrayAndShowInConsole();
        unionFilesToOne();
//        unionFilesToOneV2();
        readBigFile();
    }

    static void readFileAsByteArrayAndShowInConsole() {
        try (ByteArrayInputStream in = new ByteArrayInputStream(Files.readAllBytes(Paths.get("dzOne.txt")))) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
                System.out.println(b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void unionFilesToOne()  {
        ArrayList<InputStream> al = new ArrayList<>();
        try {
            for (int i = 0; i < 10; i++) {
                al.add(new FileInputStream(i + ".txt"));
            }
            BufferedInputStream in = new BufferedInputStream(new SequenceInputStream(Collections.enumeration(al)));
            int x;
            while ((x = in.read()) != -1) {
                System.out.print((char) x);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try (FileOutputStream fos = new FileOutputStream("dzTwo.txt")) {
//            FileInputStream fis;
//            for (int i = 0; i < 10; i++) {
//                byte[] arr = Files.readAllBytes(Paths.get("dzTwo" + i + ".txt"));
//                fos.write(arr);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

//    static void unionFilesToOneV2() {
//        try (FileOutputStream fos = new FileOutputStream("dzTwoV2.txt")) {
//            ArrayList<FileInputStream> a1 = new ArrayList<>();
//            for (int i = 0; i < 10; i++) a1.add(new FileInputStream("dzTwo" + i + ".txt"));
//            Enumeration<FileInputStream> e = Collections.enumeration(a1);
//
//            SequenceInputStream sis = new SequenceInputStream(e);
//
//            int b;
//            while (true) {
//                if ((b = sis.read()) != -1) fos.write(b);
//                else break;
//            }
//            sis.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    static void readBigFile() {
        final int PAGE_SIZE = 1800;
        try {
            RandomAccessFile raf = new RandomAccessFile("1.txt", "rw");
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter page:");
            int p = sc.nextInt() - 1;
            raf.seek(p * PAGE_SIZE);
            for (int i = 0; i < PAGE_SIZE; i++) {
                System.out.print((char) raf.read());
            }
            raf.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

//        try (RandomAccessFile raf = new RandomAccessFile("dzThree.txt", "r");
//             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//            long fileLength = raf.length();
//            long pageLength = 1800;
//            long pagesCount = fileLength/pageLength;
//            byte[] b = new byte[1800];
//            System.out.println("fileLength: " + fileLength + " | pageLength: " + pageLength + " | pagesCount: " + pagesCount);
//            while (true) {
//                System.out.println("\nEnter the page between 0 and " + pagesCount + ". -1 to Exit.");
//                long p = Long.parseLong(br.readLine());
//                if (p <= pagesCount && p >= 0) {
//                    raf.seek(p * pageLength);
//                    raf.read(b, 0, b.length);
//                    for (byte bb : b) System.out.print((char) bb);
//                } else if (p == -1) {
//                    System.out.println("Bye!");
//                    System.exit(0);
//                } else {
//                    System.out.println("Incorrect page: " + p);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}