package ru.geekbrains.java3.dz.dz1.TymkivVitaly;

public class Apple extends Fruit {
    private float weight = 1.0f;
    private String name = " Apple ";

    @Override
    public float getWeight() { return weight; }

    public String getName() {
        return name;
    }
}