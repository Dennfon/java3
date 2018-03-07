package ru.geekbrains.java3.lesson5.homework;


public class Task3 {
    class MFU {
        Object printLock = new Object();
        Object scanLock = new Object();

        public void print() {
            synchronized (printLock) {

            }
        }

        public void scan() {
            synchronized (scanLock) {

            }
        }

        public void copy() {
            synchronized (scanLock) {

            }
            synchronized (printLock) {

            }
        }
    }
}
