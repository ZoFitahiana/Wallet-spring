package com.api.wallet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String url;
    private static final String userName;
    private static final String password;

    static {
        String dbHost = System.getenv("DB_HOST");
        String dbPort = System.getenv("DB_PORT");
        String dbName = System.getenv("DB_NAME");
        String dbUsername = System.getenv("DB_USERNAME");
        String dbPassword = System.getenv("DB_PASSWORD");

        if (dbHost == null || dbPort == null || dbName == null || dbUsername == null || dbPassword == null) {
            throw new IllegalStateException("Database environment variables not properly configured");
        }

        url = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;
        userName = dbUsername;
        password = dbPassword;
    }

    public static Connection createConnection() {
        try {
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create a database connection", e);
        }

    }
}
