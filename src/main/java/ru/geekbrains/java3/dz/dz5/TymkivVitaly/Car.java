package ru.geekbrains.java3.dz.dz5.TymkivVitaly;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static int NA_START = 0;
    private static CyclicBarrier cyclicbarrier;

    public static void setCyclicBarrier(CyclicBarrier cyclicbarrier) {
        Car.cyclicbarrier = cyclicbarrier;
    }
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Race getRace() {
        return race;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        System.out.println(this.name + " скорость -> " + this.speed);
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            NA_START++;
            if (CARS_COUNT == NA_START){
                System.out.println("ВАЖНОЕ ОБЪЯВЕНИЕ >>> Гонка началась!!!");
            }
            cyclicbarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }
}


