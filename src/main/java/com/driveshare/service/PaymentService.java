package com.driveshare.service;

import com.driveshare.model.Payment;
import com.driveshare.pattern.proxy.PaymentProxy;

import java.time.LocalDateTime;

/**
 * Handles payment-related business logic.
 */
public class PaymentService {

    private final PaymentProxy paymentProxy;
    private final NotificationService notificationService;

    /**
     * Creates the service with required dependencies.
     */
    public PaymentService() {
        this.paymentProxy = new PaymentProxy();
        this.notificationService = new NotificationService();
    }

    /**
     * Creates and processes a payment through the proxy.
     *
     * @param bookingId the booking ID
     * @param amount the payment amount
     * @param userId the user receiving the payment notification
     * @return true if successful, false otherwise
     */
    public boolean makePayment(int bookingId, double amount, int userId) {
        Payment payment = new Payment();
        payment.setBookingId(bookingId);
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDateTime.now().toString());
        payment.setStatus("COMPLETED");

        boolean success = paymentProxy.processPayment(payment);

        if (success) {
            notificationService.createNotification(
                    userId,
                    "Payment completed successfully for booking ID: " + bookingId,
                    "PAYMENT"
            );
        }

        return success;
    }
}