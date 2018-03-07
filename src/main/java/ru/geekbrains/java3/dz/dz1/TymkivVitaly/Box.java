package ru.geekbrains.java3.dz.dz1.TymkivVitaly;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits;
    public String name;

    public Box(){
        fruits = new ArrayList<>();
    }
    public Box(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public ArrayList<T> getFruits() { return fruits; }

    public void setFruits(ArrayList<T> fruits) { this.fruits = fruits; }

    public float getWeight() {
        float summ = fruits.size() == 0 ? 0 : fruits.size() * fruits.get(0).getWeight();
        System.out.println(" Size box - " + fruits.size() + " box Weight - " + summ );
        return summ;
    }
    public boolean compare(Box<?> otherBox) {
        return getWeight() == otherBox.getWeight();
    }

    public void outAllFruitsToBox(Box<T> otherBox) {
        otherBox.fruits.addAll(fruits);
        fruits.clear();
    }
    public void addFruit(T fruitToAdd) { fruits.add(fruitToAdd); }
}
