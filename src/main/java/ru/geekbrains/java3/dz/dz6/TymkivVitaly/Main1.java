package ru.geekbrains.java3.dz.dz6.TymkivVitaly;

import java.util.Arrays;

public class Main1 {
    public static void main(String[] args) {
        Integer[] array1 = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        Integer[] array2 = {1, 4, 4, 4, 1, 4, 4, 4, 1};
//        Integer[] array3 = {1, 4, 4, 4, 1, 4, 4, 1, 7};
//        Integer[] array4 = {7, 2, 4, 4, 2, 3, 4, 1, 4};
//        Integer[] array5 = {7, 2, 1, 1, 2, 3, 1, 1, 1};
        System.out.println(Arrays.toString(Main1.getPartOfArray(array1)));
        System.out.println("Массив состоит 1 и 4: " + Main1.checkArrayFor1And4(array1));
        System.out.println(Arrays.toString(Main1.getPartOfArray(array2)));
        System.out.println("Массив состоит 1 и 4: " + Main1.checkArrayFor1And4(array2));
    }

    public static Integer[] getPartOfArray(Integer[] arr) throws RuntimeException {

        if (arr.length == 0) { throw new NullPointerException(); }

        int lastIndexOf4 = -1;
        for (int i = 0; i < arr.length; i++) if (arr[i] == 4) lastIndexOf4 = i + 1;

        if (lastIndexOf4 == -1) throw new RuntimeException();
        else return Arrays.copyOfRange(arr, lastIndexOf4, arr.length);
    }

    public static boolean checkArrayFor1And4(Integer[] arr) {
        int count1 = 0, count4 = 0;
        for (int x : arr) {
            if (x == 1) count1++;
            else if (x == 4) count4++;
            else return false;
        }

        return (count1 > 0 && count4 > 0);
    }
}
