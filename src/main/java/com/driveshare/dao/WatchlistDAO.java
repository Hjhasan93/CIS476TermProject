package com.driveshare.dao;

import com.driveshare.model.Watchlist;
import com.driveshare.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database operations related to the watchlists table.
 */
public class WatchlistDAO {

    /**
     * Inserts a new watchlist entry into the database.
     *
     * @param watchlist the watchlist entry to insert
     * @return true if insertion succeeds, false otherwise
     */
    public boolean insertWatchlist(Watchlist watchlist) {
        String sql = "INSERT INTO watchlists (renter_id, car_id, target_price, desired_start_date, desired_end_date, is_active) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, watchlist.getRenterId());
            statement.setInt(2, watchlist.getCarId());
            statement.setDouble(3, watchlist.getTargetPrice());
            statement.setString(4, watchlist.getDesiredStartDate());
            statement.setString(5, watchlist.getDesiredEndDate());
            statement.setInt(6, watchlist.isActive() ? 1 : 0);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting watchlist: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all active watchlist entries for a specific car.
     *
     * @param carId the car ID
     * @return list of active watchlist entries
     */
    public List<Watchlist> getActiveWatchlistsByCarId(int carId) {
        String sql = "SELECT * FROM watchlists WHERE car_id = ? AND is_active = 1";
        List<Watchlist> watchlists = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, carId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Watchlist watchlist = new Watchlist();
                watchlist.setWatchId(resultSet.getInt("watch_id"));
                watchlist.setRenterId(resultSet.getInt("renter_id"));
                watchlist.setCarId(resultSet.getInt("car_id"));
                watchlist.setTargetPrice(resultSet.getDouble("target_price"));
                watchlist.setDesiredStartDate(resultSet.getString("desired_start_date"));
                watchlist.setDesiredEndDate(resultSet.getString("desired_end_date"));
                watchlist.setActive(resultSet.getInt("is_active") == 1);

                watchlists.add(watchlist);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving watchlists: " + e.getMessage());
        }

        return watchlists;
    }
}