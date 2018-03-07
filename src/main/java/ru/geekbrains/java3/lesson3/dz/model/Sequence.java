package ru.geekbrains.java3.lesson3.dz.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Sequence {
    public static final String NAME = "keys";
    public static final int START = 1;

    public static int nextVal(Connection conn) {
        int result  = 0;
        Statement stat = null;
        try {
            String selectSql = "SELECT nextval('" + NAME + "')";
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(selectSql);
            if (rs.next()) {
                result = rs.getInt(1);
            }
        }
        catch(SQLException e) {
            System.err.println("Ошибка выполнения команды: " + e.getLocalizedMessage());
            System.err.println("Код ошибки: " + e.getErrorCode());
        }
        finally {
            try {
                stat.close();
            }
            catch (SQLException e) {
                System.err.println("Ошибка при закрытии оператора: " + e.getLocalizedMessage());
                System.err.println("Код ошибки: " + e.getErrorCode());
            }

        }
        return result;
    }
}
