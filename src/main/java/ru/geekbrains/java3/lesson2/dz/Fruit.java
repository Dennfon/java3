package ru.geekbrains.java3.lesson2.dz;

/**
 * абстрактный класс фруктов. ибо нефиг создавать экземпляры неизвестного фрукта
 */
abstract class Fruit {
    private float weight;

    Fruit(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }
}
