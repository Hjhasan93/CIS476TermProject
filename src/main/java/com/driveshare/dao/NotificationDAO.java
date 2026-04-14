package com.driveshare.dao;

import com.driveshare.model.Notification;
import com.driveshare.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Handles database operations related to the notifications table.
 */
public class NotificationDAO {

    /**
     * Inserts a new notification into the database.
     *
     * @param notification the notification to insert
     * @return true if insertion succeeds, false otherwise
     */
    public boolean insertNotification(Notification notification) {
        String sql = "INSERT INTO notifications (user_id, message, type, created_at, is_read) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, notification.getUserId());
            statement.setString(2, notification.getMessage());
            statement.setString(3, notification.getType());
            statement.setString(4, notification.getCreatedAt());
            statement.setInt(5, notification.isRead() ? 1 : 0);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting notification: " + e.getMessage());
            return false;
        }
    }
}