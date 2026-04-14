package com.driveshare.pattern.mediator;

import com.driveshare.controller.BookingController;
import com.driveshare.controller.CarListingController;
import com.driveshare.controller.NotificationController;
import com.driveshare.controller.SearchController;

/**
 * Concrete mediator that coordinates communication between
 * dashboard-related controllers.
 */
public class DashboardMediator implements UIMediator {

    private SearchController searchController;
    private BookingController bookingController;
    private NotificationController notificationController;
    private CarListingController carListingController;

    /**
     * Registers the search controller.
     *
     * @param searchController the search controller
     */
    public void setSearchController(SearchController searchController) {
        this.searchController = searchController;
    }

    /**
     * Registers the booking controller.
     *
     * @param bookingController the booking controller
     */
    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }

    /**
     * Registers the notification controller.
     *
     * @param notificationController the notification controller
     */
    public void setNotificationController(NotificationController notificationController) {
        this.notificationController = notificationController;
    }

    /**
     * Registers the car listing controller.
     *
     * @param carListingController the car listing controller
     */
    public void setCarListingController(CarListingController carListingController) {
        this.carListingController = carListingController;
    }

    /**
     * Coordinates controller interactions based on the event received.
     *
     * @param sender the object that triggered the event
     * @param event the event name
     */
    @Override
    public void notify(Object sender, String event) {

        if ("carSelected".equals(event)) {
            System.out.println("Mediator: Car selected in search controller.");

            if (bookingController != null) {
                bookingController.loadSelectedCar();
            }
        }

        if ("bookingCreated".equals(event)) {
            System.out.println("Mediator: Booking created.");

            if (notificationController != null) {
                notificationController.refreshNotifications();
            }
        }

        if ("listingUpdated".equals(event)) {
            System.out.println("Mediator: Car listing updated.");

            if (notificationController != null) {
                notificationController.refreshNotifications();
            }
        }
    }
}