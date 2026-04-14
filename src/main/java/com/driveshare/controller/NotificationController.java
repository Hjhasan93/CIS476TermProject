package com.driveshare.controller;

import com.driveshare.pattern.mediator.UIMediator;

/**
 * Simulates the notification controller in the UI.
 */
public class NotificationController {

    private final UIMediator mediator;

    /**
     * Creates the controller with mediator dependency.
     *
     * @param mediator the shared UI mediator
     */
    public NotificationController(UIMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * Simulates refreshing notifications in the UI.
     */
    public void refreshNotifications() {
        System.out.println("NotificationController: Notifications refreshed.");
    }
}