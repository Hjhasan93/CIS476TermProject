package com.driveshare.model;

/**
 * Represents a car listing created by an owner.
 * This class will be used in search, booking, and observer pattern.
 */
public class Car {

    private int carId;
    private int ownerId;
    private String model;
    private int year;
    private int mileage;
    private String pickupLocation;
    private double rentalPricePerDay;
    private String availableFrom;
    private String availableTo;

    public Car() {
    }

    public Car(int ownerId, String model, int year, int mileage,
               String pickupLocation, double rentalPricePerDay,
               String availableFrom, String availableTo) {

        this.ownerId = ownerId;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.pickupLocation = pickupLocation;
        this.rentalPricePerDay = rentalPricePerDay;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
    }

    // Getters and Setters

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(double rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(String availableTo) {
        this.availableTo = availableTo;
    }
}