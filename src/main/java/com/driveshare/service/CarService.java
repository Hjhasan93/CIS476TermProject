package com.driveshare.service;

import com.driveshare.dao.CarDAO;
import com.driveshare.model.Car;
import com.driveshare.util.ValidationUtil;

import java.util.List;

/**
 * Handles business logic related to car listings.
 */
public class CarService {

    private final CarDAO carDAO;

    /**
     * Creates the service with required DAO dependency.
     */
    public CarService() {
        this.carDAO = new CarDAO();
    }

    /**
     * Validates and saves a car listing.
     *
     * @param car the car to save
     * @return true if successful, false otherwise
     */
    public boolean createCarListing(Car car) {

        // Validate object
        if (car == null) {
            System.out.println("Car listing failed: car object is null.");
            return false;
        }

        // Validate required fields
        if (car.getOwnerId() <= 0 ||
                ValidationUtil.isNullOrEmpty(car.getModel()) ||
                ValidationUtil.isNullOrEmpty(car.getPickupLocation()) ||
                ValidationUtil.isNullOrEmpty(car.getAvailableFrom()) ||
                ValidationUtil.isNullOrEmpty(car.getAvailableTo())) {

            System.out.println("Car listing failed: missing required fields.");
            return false;
        }

        // Validate numeric values
        if (!ValidationUtil.isPositiveInt(car.getYear()) ||
                !ValidationUtil.isPositiveInt(car.getMileage()) ||
                !ValidationUtil.isPositiveDouble(car.getRentalPricePerDay())) {

            System.out.println("Car listing failed: invalid numeric values.");
            return false;
        }

        // Save car through DAO
        return carDAO.insertCar(car);
    }

    /**
     * Searches cars by pickup location.
     *
     * @param location the pickup location
     * @return list of matching cars
     */
    public List<Car> searchCarsByLocation(String location) {
        if (ValidationUtil.isNullOrEmpty(location)) {
            return List.of();
        }

        return carDAO.searchCarsByLocation(location);
    }
}