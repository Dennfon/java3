package ru.geekbrains.java3.dz.dz4.TymkivVitaly;

public class deviceMFU_3 {
    private final Object obj = new Object();
    private volatile char  valueSet  = 'A';

// продолжение работы принтера при разном количестве операций печати и сканирования

    public  void printDocuments(int count) throws InterruptedException {
                for (int i = 0; i < count; i++) {
                    synchronized (obj) {
                        while (valueSet != 'P' && valueSet != 'A') obj.wait();
                        valueSet = 'S';
                        obj.notifyAll();
                        try {
                            System.out.println("распечатано " + (i + 1) + " страниц");
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        Thread dem = new Thread(() -> {
            try {
                this.standby();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        dem.setDaemon(true);
        dem.start();
    }

    public  void scanDocuments(int count) throws InterruptedException {
                for (int i = 0; i < count; i++) {
                    synchronized (obj) {
                        while (valueSet != 'S' && valueSet != 'A') obj.wait();
                        valueSet = 'P';
                        obj.notifyAll();
                        try {
                            System.out.println("отсканировано " + (i + 1) + " страниц");
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        Thread dem = new Thread(() -> {
            try {
                this.standby();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        dem.setDaemon(true);
        dem.start();
    }
    public  void standby() throws InterruptedException {
            while (true) {
                synchronized (obj) {
//                while (valueSet != 'A') obj.wait();
                    if (valueSet == 'P'){
                        valueSet = 'S';
                    }else if (valueSet == 'S'){
                        valueSet = 'P';
                    }
//                    else valueSet = 'A';
                    obj.notifyAll();
                    try {
                        System.out.println("standby ...");
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}