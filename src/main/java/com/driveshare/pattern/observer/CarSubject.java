package com.driveshare.pattern.observer;

/**
 * Subject interface for observable car objects.
 */
public interface CarSubject {

    /**
     * Attaches an observer to the subject.
     *
     * @param observer the observer to attach
     */
    void attach(Observer observer);

    /**
     * Detaches an observer from the subject.
     *
     * @param observer the observer to detach
     */
    void detach(Observer observer);

    /**
     * Notifies all attached observers.
     */
    void notifyObservers();
}