package com.driveshare.model;

/**
 * Represents a booking made by a renter for a car.
 * This is where the overlapping booking rule will apply.
 */
public class Booking {

    private int bookingId;
    private int carId;
    private int renterId;
    private String startDate;
    private String endDate;
    private double totalAmount;
    private String status;

    public Booking() {
    }

    public Booking(int carId, int renterId, String startDate, String endDate,
                   double totalAmount, String status) {

        this.carId = carId;
        this.renterId = renterId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // Getters and Setters

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}