package com.driveshare.pattern.mediator;

/**
 * Mediator interface used to coordinate communication
 * between UI-related controllers.
 */
public interface UIMediator {

    /**
     * Receives an event from one controller and coordinates
     * actions for other related controllers.
     *
     * @param sender the object that triggered the event
     * @param event the event name
     */
    void notify(Object sender, String event);
}