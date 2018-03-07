package ru.geekbrains.java3.lesson3.dz.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnector {


    private static final String driver = "jdbc:postgresql";
    private static String host;
    private static String user = "postgres";
    private static String password = "postgres";
    private static String url;

    private static Connection connection;

    public static Connection getConnection(String h, String u, String p) {
        host = h;
        user = u;
        password = p;
        if (connection == null) {
            registerDriver();
            connection = connect();
        }
        return connection;
    }



    private static void registerDriver() {

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        }
        catch (SQLException e) {
            System.err.println("Ошибка регистрации драйвера: " + e.getMessage());
            System.err.println("Код ошибки: " + e.getErrorCode());
        }
    }


    private static Connection connect() {

        String url = String.format("%s://%s:5432/%s", driver, host, "postgres");
        try {
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e) {
            System.err.println("Ошибка установления соединения: " + e.getMessage());
            System.err.println("Код ошибки: " + e.getErrorCode());
        }
        return connection;
    }

    public static void close() {
       try {
         if (connection != null)
            connection.close();
       }
       catch(SQLException e){
         System.err.println("Ошибка: " + e.getMessage());
         System.err.println("Код ошибки: " + e.getErrorCode());
        }
    }
}
