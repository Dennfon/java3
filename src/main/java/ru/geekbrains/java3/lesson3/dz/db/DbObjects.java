package ru.geekbrains.java3.lesson3.dz.db;


import ru.geekbrains.java3.lesson3.dz.model.Product;
import ru.geekbrains.java3.lesson3.dz.model.Sequence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DbObjects {

    public static void createObjects(Connection connection) {
        createSequence(connection);
        createTables(connection);
    }

    public static void dropObjects(Connection connection) {
        dropTable(connection);
        dropSequence(connection);
    }

    private static void createTables(Connection conn) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
            String sqlStr = String.format("CREATE TABLE IF NOT EXISTS %s ( %s integer PRIMARY KEY, " +
                    "%s UUID, %s varchar(64), %s double precision);", Product.TABLE, Product.ID, Product.COLUMN1,
                    Product.COLUMN2, Product.COLUMN3);
            statement.execute(sqlStr);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private static void dropTable(Connection conn) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
            String sqlStr = String.format("DROP TABLE IF EXISTS %s;", Product.TABLE);
            statement.execute(sqlStr);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createSequence(Connection conn) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
            String sqlStr = String.format("CREATE SEQUENCE %s START %d;", Sequence.NAME, Sequence.START);
            statement.execute(sqlStr);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private static void dropSequence(Connection conn) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
            String sqlStr = String.format("DROP SEQUENCE IF EXISTS %s;", Sequence.NAME);
            statement.execute(sqlStr);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
