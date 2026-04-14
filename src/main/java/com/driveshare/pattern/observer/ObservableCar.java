package com.driveshare.pattern.observer;

import com.driveshare.model.Car;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete subject that wraps a Car and notifies observers when it changes.
 */
public class ObservableCar implements CarSubject {

    private final Car car;
    private final List<Observer> observers;

    /**
     * Creates an observable wrapper around a car.
     *
     * @param car the car to observe
     */
    public ObservableCar(Car car) {
        this.car = car;
        this.observers = new ArrayList<>();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(car);
        }
    }

    /**
     * Updates the car price and availability, then notifies observers.
     *
     * @param newPrice updated price
     * @param newAvailableFrom updated available from date
     * @param newAvailableTo updated available to date
     */
    public void updateCarDetails(double newPrice, String newAvailableFrom, String newAvailableTo) {
        car.setRentalPricePerDay(newPrice);
        car.setAvailableFrom(newAvailableFrom);
        car.setAvailableTo(newAvailableTo);

        notifyObservers();
    }
}