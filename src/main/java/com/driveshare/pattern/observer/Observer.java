package com.driveshare.pattern.observer;

import com.driveshare.model.Car;

/**
 * Observer interface for classes that want to receive car update notifications.
 */
public interface Observer {

    /**
     * Called when the observed car is updated.
     *
     * @param car the updated car
     */
    void update(Car car);
}