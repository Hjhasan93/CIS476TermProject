package com.driveshare.service;

import com.driveshare.dao.NotificationDAO;
import com.driveshare.model.Notification;

import java.time.LocalDateTime;

/**
 * Handles business logic related to notifications.
 */
public class NotificationService {

    private final NotificationDAO notificationDAO;

    /**
     * Creates the service with required DAO dependency.
     */
    public NotificationService() {
        this.notificationDAO = new NotificationDAO();
    }

    /**
     * Creates and stores a notification for a user.
     *
     * @param userId the recipient user ID
     * @param message the notification message
     * @param type the notification type
     * @return true if successful, false otherwise
     */
    public boolean createNotification(int userId, String message, String type) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setType(type);
        notification.setCreatedAt(LocalDateTime.now().toString());
        notification.setRead(false);

        return notificationDAO.insertNotification(notification);
    }
}