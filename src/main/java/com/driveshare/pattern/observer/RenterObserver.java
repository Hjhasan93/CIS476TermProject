package com.driveshare.pattern.observer;

import com.driveshare.model.Car;
import com.driveshare.model.Watchlist;
import com.driveshare.service.NotificationService;

/**
 * Concrete observer representing a renter watching a car.
 */
public class RenterObserver implements Observer {

    private final Watchlist watchlist;
    private final NotificationService notificationService;

    /**
     * Creates a renter observer with watch criteria.
     *
     * @param watchlist the watchlist criteria
     */
    public RenterObserver(Watchlist watchlist) {
        this.watchlist = watchlist;
        this.notificationService = new NotificationService();
    }

    @Override
    public void update(Car car) {

        // Check if car price meets the renter's target
        boolean priceMatches = car.getRentalPricePerDay() <= watchlist.getTargetPrice();

        // Check if car availability matches the renter's desired date range
        boolean dateMatches =
                car.getAvailableFrom().compareTo(watchlist.getDesiredStartDate()) <= 0 &&
                        car.getAvailableTo().compareTo(watchlist.getDesiredEndDate()) >= 0;

        // If both conditions match, create a notification
        if (priceMatches && dateMatches) {
            String message = "Watched car " + car.getModel() +
                    " is now available and matches your price criteria.";

            notificationService.createNotification(
                    watchlist.getRenterId(),
                    message,
                    "WATCHLIST"
            );

            System.out.println("Observer notification sent to renter ID: " + watchlist.getRenterId());
        }
    }
}