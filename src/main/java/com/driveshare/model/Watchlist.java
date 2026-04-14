package com.driveshare.model;

/**
 * Represents a watch entry where a renter monitors a car
 * for price or availability changes.
 */
public class Watchlist {

    private int watchId;
    private int renterId;
    private int carId;
    private double targetPrice;
    private String desiredStartDate;
    private String desiredEndDate;
    private boolean isActive;

    public Watchlist() {
    }

    // Getters and Setters

    public int getWatchId() {
        return watchId;
    }

    public void setWatchId(int watchId) {
        this.watchId = watchId;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(double targetPrice) {
        this.targetPrice = targetPrice;
    }

    public String getDesiredStartDate() {
        return desiredStartDate;
    }

    public void setDesiredStartDate(String desiredStartDate) {
        this.desiredStartDate = desiredStartDate;
    }

    public String getDesiredEndDate() {
        return desiredEndDate;
    }

    public void setDesiredEndDate(String desiredEndDate) {
        this.desiredEndDate = desiredEndDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}