package ru.geekbrains.java3.dz.dz6.TymkivVitaly;

import org.junit.*;
import java.sql.*;
import java.io.*;

public class Main2 {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "qw123456";
    private static Connection connection = null;
    private static PreparedStatement ps  = null;
    private static Statement statement  = null;
//

    public static void main(String[] args) {
//        Main2.sqlCreateTable();
//        Main2.sqlSelectAll("petrov");
//        Main2.sqlUpdateQuery("petrov");
    }

    public Main2() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sqlCreateTable() {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE students(" +
                    "id serial NOT NULL," +
                    "surname text NOT NULL," +
                    "score numeric(1) NOT NULL," +
                    "CONSTRAINT studentid PRIMARY KEY(id))" +
                    "WITH (OIDS = FALSE);" +
                    "ALTER TABLE students OWNER TO postgres;");
            System.out.println("таблица students создана");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean sqlSelectAll(String query) {
        try {
            statement = connection.createStatement();
            return statement.execute(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static int sqlUpdateQuery(String query) {
        try {
            statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
}