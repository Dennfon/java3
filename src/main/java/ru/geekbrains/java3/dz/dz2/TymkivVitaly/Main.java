package ru.geekbrains.java3.dz.dz2.TymkivVitaly;

import java.io.*;
import java.sql.*;

public class Main {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "qw123456";
    private static Connection connection = null;
    private static PreparedStatement ps  = null;
    private static Statement statement  = null;

    public static void main(String[] args) {

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
            if (connection != null) {
                System.out.println("Подключение к базе данных прошло успешно!\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlExecuteUpdate("DROP TABLE product");
        sqlCreateTable();
//        sqlExecuteUpdate("DELETE FROM product");
        sqlInsertData();
        sqlSelectAll();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("\nКонсольные команды: ");
                System.out.println("/цена {ТОВАР}");
                System.out.println("/сменитьцену {ТОВАР} {ЦЕНА}");
                System.out.println("/товарыпоцене {ЦЕНА ОТ} {ЦЕНА ДО}");
                System.out.println("/всетовары");
                System.out.println("/выход");
                System.out.println("Введите команду: \n");
                String response = br.readLine();
                if (response.startsWith("/цена")) {
                    sqlSelectPrice(response.split(" ")[1]);
                } else if (response.startsWith("/сменитьцену")) {
                    String arg1 = response.split(" ")[1];
                    Double arg2 = Double.parseDouble(response.split(" ")[2]);
                    sqlUpdatePrice(arg1, arg2);
                } else if (response.startsWith("/товарыпоцене")) {
                    Double arg1 = Double.parseDouble(response.split(" ")[1]);
                    Double arg2 = Double.parseDouble(response.split(" ")[2]);
                    sqlSelectPricesBetween(arg1, arg2);
                } else if (response.startsWith("/всетовары")) {
                    sqlSelectAll();
                } else if (response.startsWith("/выход")) {
                    System.exit(0);
                } else {
                    System.out.println("Команда неверна!");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                ps.close();
                connection.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void sqlExecuteUpdate(String SQLQuery) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(SQLQuery);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sqlCreateTable() {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE product(" +
                    "id serial NOT NULL," +
                    "prodid numeric NOT NULL," +
                    "title text NOT NULL," +
                    "cost numeric(1000,2) NOT NULL," +
                    "CONSTRAINT id PRIMARY KEY (id)," +
                    "CONSTRAINT prodid UNIQUE (prodid))" +
                    "WITH (OIDS = FALSE);" +
                    "ALTER TABLE product OWNER TO postgres;");
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sqlInsertData() {
        try {
            ps = connection.prepareStatement("INSERT INTO product (prodid, title, cost) VALUES (?, ?, ?)");
            ps.clearBatch();
            for (int i = 1; i <= 10000; i++) {
                ps.setInt(1, (i * 10));
                ps.setString(2, "товар" + i);
                ps.setDouble(3, (Math.random() * 100000) / 100);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sqlSelectAll(){
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
            printAllColunms(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sqlSelectPrice(String title) {
        try {
            ps = connection.prepareStatement("SELECT title, cost FROM product WHERE title = ?");
            ps.setString(1, title);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next() == false) {
                System.out.println("Такого товара нет: " + title);
            } else {
                do {
                    String titl = resultSet.getString("title");
                    Double cost = resultSet.getDouble("cost");
                    System.out.println(String.format(" title: %s | cost: %s", titl, cost));
                } while (resultSet.next());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sqlUpdatePrice(String title, Double cost) {
        try {
            ps = connection.prepareStatement("UPDATE product SET cost = ? WHERE title = ?");
            ps.setDouble(1, cost);
            ps.setString(2, title);
            ps.executeUpdate();
            sqlSelectPrice(title);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sqlSelectPricesBetween(Double from, Double to) {
        try {
            ps = connection.prepareStatement("SELECT * FROM product WHERE cost BETWEEN ? AND ?");
            ps.setDouble(1, from);
            ps.setDouble(2, to);
            ResultSet resultSet = ps.executeQuery();
            printAllColunms(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printAllColunms(ResultSet resultSet) throws SQLException {
        if (resultSet.next() == false) {
            System.out.println("В выборке 0 записей");
        } else {
            do {
                String id = resultSet.getString("id");
                String prodid = resultSet.getString("prodid");
                String title = resultSet.getString("title");
                String cost = resultSet.getString("cost");
                System.out.println(String.format("id: %s | prodid: %s | title: %s | cost: %s", id, prodid, title, cost));
            } while (resultSet.next());
        }
    }
}