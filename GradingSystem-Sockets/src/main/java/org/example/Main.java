package org.example;

import Repository.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {

        Connection con = DatabaseConnection.getConnection();
    }
}
