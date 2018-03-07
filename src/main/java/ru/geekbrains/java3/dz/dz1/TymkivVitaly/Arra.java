package ru.geekbrains.java3.dz.dz1.TymkivVitaly;

import java.util.*;

public class Arra<T> {
    private T[] arra;

    public Arra(T... arra) { this.arra = arra; }

    public void arraElementsExchange(int ib, int ik){
        if (arra.length > ib && arra.length > ik){
            T  elem = arra[ib];
            arra[ib] = arra[ik];
            arra[ik] = elem;
        }else System.out.println(" Fail index ");
    }

    public void setObj(T[] arra) { this.arra = arra; }

    public T[] getObj() { return arra; }

    public void info() { for (T item : arra) System.out.println(item.getClass().getName()); }

    public ArrayList<T> arrayToArrayList() {
        ArrayList<T> list = new ArrayList<>(Arrays.asList(arra));
        return list;
    }
    public ArrayList<T> arrayToList(String[] arrint) {
            ArrayList<String> arrayList = new ArrayList<>();
            for (int i = 0; i < arrint.length; i++) {
                arrayList.add(arrint[i]);
            }
        return (ArrayList<T>) arrayList;
    }
//    private static <E> List<E> convertToList(E[] array) {
//        List<E> arrays = new ArrayList<>();
//        for (E o: array) {
//            arrays.add(o);
//        }
//        return arrays;
//    }
}