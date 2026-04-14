package com.driveshare.dao;

import com.driveshare.model.Booking;
import com.driveshare.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Handles database operations related to the bookings table.
 */
public class BookingDAO {

    /**
     * Inserts a new booking into the database.
     *
     * @param booking the booking object to insert
     * @return true if insertion succeeds, false otherwise
     */
    public boolean insertBooking(Booking booking) {
        String sql = "INSERT INTO bookings (car_id, renter_id, start_date, end_date, total_amount, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set query parameters
            statement.setInt(1, booking.getCarId());
            statement.setInt(2, booking.getRenterId());
            statement.setString(3, booking.getStartDate());
            statement.setString(4, booking.getEndDate());
            statement.setDouble(5, booking.getTotalAmount());
            statement.setString(6, booking.getStatus());

            // Execute insert
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting booking: " + e.getMessage());
            return false;
        }
    }

    /**
     * Checks whether a car already has an overlapping booking
     * for the requested date range.
     *
     * Overlap rule:
     * newStart <= existingEnd AND newEnd >= existingStart
     *
     * @param carId the car ID
     * @param startDate requested booking start date
     * @param endDate requested booking end date
     * @return true if overlap exists, false otherwise
     */
    public boolean hasOverlappingBooking(int carId, String startDate, String endDate) {
        String sql = "SELECT COUNT(*) FROM bookings " +
                "WHERE car_id = ? " +
                "AND status IN ('PENDING', 'CONFIRMED', 'PAID') " +
                "AND (? <= end_date AND ? >= start_date)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set query parameters
            statement.setInt(1, carId);
            statement.setString(2, startDate);
            statement.setString(3, endDate);

            // Execute query
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error checking overlapping booking: " + e.getMessage());
        }

        return false;
    }
}