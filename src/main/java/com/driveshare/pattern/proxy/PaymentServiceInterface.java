package com.driveshare.pattern.proxy;

import com.driveshare.model.Payment;

/**
 * Subject interface for payment processing.
 */
public interface PaymentServiceInterface {

    /**
     * Processes a payment.
     *
     * @param payment the payment to process
     * @return true if successful, false otherwise
     */
    boolean processPayment(Payment payment);
}