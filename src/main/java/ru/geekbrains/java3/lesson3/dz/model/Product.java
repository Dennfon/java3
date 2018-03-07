package ru.geekbrains.java3.lesson3.dz.model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Product  {

    public static final String TABLE = "products";
    public static final String ID = "id";
    public static final String COLUMN1 = "prodid";
    public static final String COLUMN2 = "title";
    public static final String COLUMN3 = "cost";


    private int id;
    private UUID prodId;
    private String title;
    private double cost;

    public Product() {
        this.prodId = UUID.randomUUID();
    }

    public Product(String title, double cost) {
        this.prodId = UUID.randomUUID();
        this.title = title;
        this.cost = cost;
    }
    public Product(int id, UUID prodId, String title, double cost) {
        this.id = id;
        this.prodId = prodId;
        this.title = title;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getProdId() {
        return prodId;
    }

    public void setProdId(UUID prodId) {
        this.prodId = prodId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void insert(Connection conn) {
        PreparedStatement stat = null;
        String insertSql = "INSERT into " + TABLE + " values(?, ?, ?, ?);";
        try {
            id = Sequence.nextVal(conn);
            stat = conn.prepareStatement(insertSql);
            stat.setInt(1, id);
            stat.setObject(2, prodId);
            stat.setString(3, title);
            stat.setBigDecimal(4, new BigDecimal(cost));
            stat.executeUpdate();
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
    }
    public void update(Connection conn) {

        PreparedStatement stat = null;
        String insertSql = "Update " + TABLE + " SET title = ? , cost = ? WHERE id = ?;";
        try {
            stat = conn.prepareStatement(insertSql);
            stat.setString(1, title);
            stat.setBigDecimal(2, new BigDecimal(cost));
            stat.setInt(3, id);
            stat.executeUpdate();
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
    }

    public void delete(Connection conn) {

        int result = -1;
        PreparedStatement stat = null;
        String insertSql = "DELETE from " + TABLE + " WHERE id = ?;";
        try {
            stat = conn.prepareStatement(insertSql);
            stat.setInt(1, id);
            int count = stat.executeUpdate();
            result = 0;

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
    }

    public static List<Product> selectAll(Connection conn) {
        Statement stat = null;
        List<Product> products =  new ArrayList<>();
        try {
            String selectSql = "SELECT * from " + TABLE + ";";
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(selectSql);
            while (rs.next()) {
                products.add(new Product(rs.getInt(1), (UUID)rs.getObject(2),
                        rs.getString(3), 10));
                System.out.println(rs.getString(4));
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
        return products;
    }

    public static List<Product> findByName(Connection conn, String title) {
        PreparedStatement stat = null;
        List<Product> products =  new ArrayList<>();
        try {
            String selectSql = "SELECT * from " + TABLE + " WHERE title = ?;";
            stat = conn.prepareStatement(selectSql);
            stat.setString(1, title);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt(1), (UUID)rs.getObject(2),
                        rs.getString(3), rs.getDouble(4)));
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
        return products;
    }

    public static List<Product> findByCostInterval(Connection conn, double cost1, double cost2) {
        PreparedStatement stat = null;
        List<Product> products =  new ArrayList<>();
        try {
            String selectSql = "SELECT * from " + TABLE + " WHERE cost >= ? AND cost <= ?;";
            stat = conn.prepareStatement(selectSql);
            stat.setDouble(1, cost1);
            stat.setDouble(2, cost2);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt(1), (UUID)rs.getObject(2),
                        rs.getString(3), rs.getDouble(4)));
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
        return products;
    }
}
