package ru.geekbrains.java3.lesson3.dz;


import ru.geekbrains.java3.lesson3.dz.db.DbConnector;
import ru.geekbrains.java3.lesson3.dz.db.DbObjects;
import ru.geekbrains.java3.lesson3.dz.model.Product;

import java.sql.Connection;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Сформировать таблицу товаров (id, prodid, title, cost) запросом из Java приложения.
 id - порядковый номер записи, первичный ключ
 prodid - уникальный номер товара
 title - название товара
 cost - стоимость

 При запуске приложения очистить таблицу и заполнить 10.000 товаров вида:
 id_товара 1 товар1 10
 id_товара 2 товар2 20
 id_товара 3 товар3 30
 ...
 id_товара 10000 товар10000 100010
 т.е. просто тестовые данные


 Написать консольное приложение, которое позволяет узнать цену товара по
 его имени, либо если такого товара нет, то должно быть выведено сообщение
 "Такого товара нет". Пример консольной комманды для получения цены:"/цена товар545"

 В этом же приложении должна быть возможность изменения цены
 товара(указываем имя товара и новую цену). Пример: "/сменитьцену товар10 10000"

 Вывести товары в заданном ценовом диапазоне. Консольная комманда: "/товарыпоцене 100 600"
 */

public class Main {

    public static final Random RANDOM = new Random();
    public static final String EXIT = "exit";

    public static void main(String[] args) {
        Connection conn = DbConnector.getConnection("192.168.1.153", "postgres", "123456");
        if (conn == null) {
            exit(0);
            System.out.println("Соединение не установлено");
        }

        DbObjects.createObjects(conn);
        generateProducts(conn, 1000);
        Scanner input = new Scanner(System.in);
        String console;
        while (true) {
            System.out.println("Введите команду (exit - для выхода): ");
            System.out.println("доступны команды:");
            System.out.println("цена  товар");
            System.out.println("сменитьцену товар новая цена");
            System.out.println("товарыпоцене цена1 цена2");
            console = input.nextLine();
            if (console.equals(EXIT)) break;
            String[] commands = console.split(" ");
            if (commands.length < 2) continue;
            showResult(commands, conn);
        }

        DbObjects.dropObjects(conn);
        DbConnector.close();

    }

    public static void print(List<Product> list) {
        System.out.println("Список товаров");

        for (Product p: list) {
            System.out.printf(" %5d %s %16s %8.2f\n", p.getId(),  p.getProdId().toString(),
                    p.getTitle(), p.getCost());
        }
        System.out.println("-----------------------------------------------------------------");

    }

    public static void generateProducts(Connection conn, int num) {
        System.out.println("Создание товаров ....");
        for (int i = 0; i < num; i++) {
            Product p = new Product("товар" + i, RANDOM.nextInt(1000));
            p.insert(conn);
        }
    }

    public static void showResult(String[] str, Connection conn) {
        List<Product> list;
        switch(str[0]) {
            case "цена" :
                list = Product.findByName(conn, str[1]);
                if (list.size() == 0)
                    System.out.println("Такого товара нет!");
                else
                    print(list);
                break;
            case "сменитьцену" :
                if (str.length == 3) {
                    list = Product.findByName(conn, str[1]);
                    if (list.size() > 0)
                        updateCost(conn, list, str[2]);
                    else
                        System.out.println("Такого товара нет!");
                }
                break;
            case "товарыпоцене" :
                if (str.length == 3) {
                    list = selectProductByCost(conn, str[1], str[2]);
                    if (list.size() > 0)
                        print(list);
                    else
                        System.out.println("Такого товара нет!");
                }
                break;

        }
   }

   public static void updateCost(Connection conn, List<Product> list, String strCost) {
       double cost;

       try {
           cost = Double.valueOf(strCost);
       }
       catch (NumberFormatException e) {
           System.out.println("Не верно указана цена");
           return;
       }
       for (Product p : list) {
           p.setCost(cost);
           p.update(conn);
       }
   }

   public static List<Product> selectProductByCost(Connection conn, String strCost1, String strCost2) {
       double cost1;
       double cost2;
       try {
           cost1 = Double.valueOf(strCost1);
           cost2 = Double.valueOf(strCost2);
       }
       catch (NumberFormatException e) {
           System.out.println("Не верно указана цена");
           return null;
       }
       return Product.findByCostInterval(conn, cost1, cost2);
   }
}
