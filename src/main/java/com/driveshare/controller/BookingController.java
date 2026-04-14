package com.driveshare.controller;

import com.driveshare.pattern.mediator.UIMediator;

/**
 * Simulates the booking controller in the UI.
 */
public class BookingController {

    private final UIMediator mediator;

    /**
     * Creates the controller with mediator dependency.
     *
     * @param mediator the shared UI mediator
     */
    public BookingController(UIMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * Simulates loading the selected car for booking.
     */
    public void loadSelectedCar() {
        System.out.println("BookingController: Loading selected car.");
    }

    /**
     * Simulates booking creation.
     */
    public void createBooking() {
        System.out.println("BookingController: Booking created.");
        mediator.notify(this, "bookingCreated");
    }
}