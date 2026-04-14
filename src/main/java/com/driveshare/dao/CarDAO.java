package com.driveshare.dao;

import com.driveshare.model.Car;
import com.driveshare.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database operations related to the cars table.
 */
public class CarDAO {

    /**
     * Inserts a new car listing into the database.
     *
     * @param car the car object to insert
     * @return true if insertion succeeds, false otherwise
     */
    public boolean insertCar(Car car) {
        String sql = "INSERT INTO cars (owner_id, model, year, mileage, pickup_location, rental_price_per_day, available_from, available_to) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set query parameters
            statement.setInt(1, car.getOwnerId());
            statement.setString(2, car.getModel());
            statement.setInt(3, car.getYear());
            statement.setInt(4, car.getMileage());
            statement.setString(5, car.getPickupLocation());
            statement.setDouble(6, car.getRentalPricePerDay());
            statement.setString(7, car.getAvailableFrom());
            statement.setString(8, car.getAvailableTo());

            // Execute insert
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting car: " + e.getMessage());
            return false;
        }
    }

    /**
     * Searches cars by pickup location.
     *
     * @param location the pickup location entered by the user
     * @return list of matching cars
     */
    public List<Car> searchCarsByLocation(String location) {
        String sql = "SELECT * FROM cars WHERE pickup_location LIKE ?";
        List<Car> cars = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Search using partial match
            statement.setString(1, "%" + location + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Car car = new Car();
                car.setCarId(resultSet.getInt("car_id"));
                car.setOwnerId(resultSet.getInt("owner_id"));
                car.setModel(resultSet.getString("model"));
                car.setYear(resultSet.getInt("year"));
                car.setMileage(resultSet.getInt("mileage"));
                car.setPickupLocation(resultSet.getString("pickup_location"));
                car.setRentalPricePerDay(resultSet.getDouble("rental_price_per_day"));
                car.setAvailableFrom(resultSet.getString("available_from"));
                car.setAvailableTo(resultSet.getString("available_to"));

                cars.add(car);
            }

        } catch (SQLException e) {
            System.out.println("Error searching cars by location: " + e.getMessage());
        }

        return cars;
    }
}