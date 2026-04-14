package com.driveshare.dao;

import com.driveshare.model.Payment;
import com.driveshare.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Handles database operations related to the payments table.
 */
public class PaymentDAO {

    /**
     * Inserts a payment record into the database.
     *
     * @param payment the payment to insert
     * @return true if insertion succeeds, false otherwise
     */
    public boolean insertPayment(Payment payment) {
        String sql = "INSERT INTO payments (booking_id, amount, payment_date, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, payment.getBookingId());
            statement.setDouble(2, payment.getAmount());
            statement.setString(3, payment.getPaymentDate());
            statement.setString(4, payment.getStatus());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting payment: " + e.getMessage());
            return false;
        }
    }
}