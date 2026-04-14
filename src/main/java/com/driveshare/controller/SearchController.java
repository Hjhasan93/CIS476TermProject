package com.driveshare.controller;

import com.driveshare.pattern.mediator.UIMediator;

/**
 * Simulates the search controller in the UI.
 */
public class SearchController {

    private final UIMediator mediator;

    /**
     * Creates the controller with mediator dependency.
     *
     * @param mediator the shared UI mediator
     */
    public SearchController(UIMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * Simulates selecting a car from search results.
     */
    public void selectCar() {
        System.out.println("SearchController: Car selected.");
        mediator.notify(this, "carSelected");
    }
}