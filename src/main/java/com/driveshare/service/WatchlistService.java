package com.driveshare.service;

import com.driveshare.dao.WatchlistDAO;
import com.driveshare.model.Car;
import com.driveshare.model.Watchlist;
import com.driveshare.pattern.observer.ObservableCar;
import com.driveshare.pattern.observer.RenterObserver;
import com.driveshare.util.ValidationUtil;

import java.util.List;

/**
 * Handles business logic related to watchlists and observer notifications.
 */
public class WatchlistService {

    private final WatchlistDAO watchlistDAO;

    /**
     * Creates the service with required DAO dependency.
     */
    public WatchlistService() {
        this.watchlistDAO = new WatchlistDAO();
    }

    /**
     * Validates and inserts a watchlist entry.
     *
     * @param watchlist the watchlist entry
     * @return true if insertion succeeds, false otherwise
     */
    public boolean addWatchlist(Watchlist watchlist) {
        if (watchlist == null) {
            System.out.println("Watchlist failed: watchlist object is null.");
            return false;
        }

        if (watchlist.getRenterId() <= 0 ||
                watchlist.getCarId() <= 0 ||
                ValidationUtil.isNullOrEmpty(watchlist.getDesiredStartDate()) ||
                ValidationUtil.isNullOrEmpty(watchlist.getDesiredEndDate())) {

            System.out.println("Watchlist failed: missing required fields.");
            return false;
        }

        return watchlistDAO.insertWatchlist(watchlist);
    }

    /**
     * Loads all active watchlists for a car, attaches observers,
     * and triggers notification evaluation after a car update.
     *
     * @param car the updated car
     */
    public void notifyWatchers(Car car) {
        List<Watchlist> watchlists = watchlistDAO.getActiveWatchlistsByCarId(car.getCarId());

        ObservableCar observableCar = new ObservableCar(car);

        // Attach one observer per active watchlist entry
        for (Watchlist watchlist : watchlists) {
            observableCar.attach(new RenterObserver(watchlist));
        }

        // Trigger notifications
        observableCar.notifyObservers();
    }
}