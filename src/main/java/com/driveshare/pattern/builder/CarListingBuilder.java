package com.driveshare.pattern.builder;

import com.driveshare.model.Car;

/**
 * Concrete builder used to create Car objects.
 */
public class CarListingBuilder implements CarBuilder {

    private int ownerId;
    private String model;
    private int year;
    private int mileage;
    private String pickupLocation;
    private double rentalPricePerDay;
    private String availableFrom;
    private String availableTo;

    @Override
    public CarBuilder setOwnerId(int ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    @Override
    public CarBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    @Override
    public CarBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    @Override
    public CarBuilder setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    @Override
    public CarBuilder setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
        return this;
    }

    @Override
    public CarBuilder setRentalPricePerDay(double rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
        return this;
    }

    @Override
    public CarBuilder setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
        return this;
    }

    @Override
    public CarBuilder setAvailableTo(String availableTo) {
        this.availableTo = availableTo;
        return this;
    }

    /**
     * Builds the final Car object using collected values.
     *
     * @return a new Car object
     */
    @Override
    public Car build() {
        return new Car(
                ownerId,
                model,
                year,
                mileage,
                pickupLocation,
                rentalPricePerDay,
                availableFrom,
                availableTo
        );
    }
}