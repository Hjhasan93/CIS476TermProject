package com.driveshare.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Provides a reusable connection to the SQLite database.
 */
public class DBConnection {

    // Full absolute path to the real SQLite database file
    private static final String URL = "jdbc:sqlite:C:/Users/m101/DriveShare.db";

    /**
     * Private constructor to prevent object creation.
     */
    private DBConnection() {
    }

    /**
     * Creates and returns a connection to the SQLite database.
     * Also enables foreign key support in SQLite.
     *
     * @return a valid database connection
     * @throws SQLException if the connection fails
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL);

        // Enable foreign key support in SQLite
        try (Statement statement = connection.createStatement()) {
            statement.execute("PRAGMA foreign_keys = ON");
        }

        return connection;
    }
}