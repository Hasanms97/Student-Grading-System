package com.example.elearning.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection con = null;
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/elearning";
    private static final String USER = "root";
    private static final String PASSWORD = "hassan123H@";

    static {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
