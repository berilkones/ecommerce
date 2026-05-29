package com.ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/ecommerce_db";

    private static final String USER = "postgres";

    private static final String PASSWORD = "1234";

    public static Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Veritabanı bağlantısı başarılı!");

        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }

        return connection;
    }
}