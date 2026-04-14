package com.driveshare.controller;

import com.driveshare.pattern.mediator.UIMediator;

/**
 * Simulates the car listing controller in the UI.
 */
public class CarListingController {

    private final UIMediator mediator;

    /**
     * Creates the controller with mediator dependency.
     *
     * @param mediator the shared UI mediator
     */
    public CarListingController(UIMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * Simulates updating a listing.
     */
    public void updateListing() {
        System.out.println("CarListingController: Listing updated.");
        mediator.notify(this, "listingUpdated");
    }
}