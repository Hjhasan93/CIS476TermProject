package com.driveshare.pattern.builder;

import com.driveshare.model.Car;

/**
 * Builder interface for constructing Car objects step by step.
 */
public interface CarBuilder {

    /**
     * Sets the owner ID of the car listing.
     *
     * @param ownerId the owner user ID
     * @return the builder instance
     */
    CarBuilder setOwnerId(int ownerId);

    /**
     * Sets the car model.
     *
     * @param model the car model
     * @return the builder instance
     */
    CarBuilder setModel(String model);

    /**
     * Sets the car year.
     *
     * @param year the car year
     * @return the builder instance
     */
    CarBuilder setYear(int year);

    /**
     * Sets the car mileage.
     *
     * @param mileage the car mileage
     * @return the builder instance
     */
    CarBuilder setMileage(int mileage);

    /**
     * Sets the pickup location.
     *
     * @param pickupLocation the pickup location
     * @return the builder instance
     */
    CarBuilder setPickupLocation(String pickupLocation);

    /**
     * Sets the rental price per day.
     *
     * @param rentalPricePerDay the daily rental price
     * @return the builder instance
     */
    CarBuilder setRentalPricePerDay(double rentalPricePerDay);

    /**
     * Sets the availability start date.
     *
     * @param availableFrom start date
     * @return the builder instance
     */
    CarBuilder setAvailableFrom(String availableFrom);

    /**
     * Sets the availability end date.
     *
     * @param availableTo end date
     * @return the builder instance
     */
    CarBuilder setAvailableTo(String availableTo);

    /**
     * Builds and returns the final Car object.
     *
     * @return constructed Car object
     */
    Car build();
}