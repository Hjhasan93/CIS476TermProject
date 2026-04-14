package com.driveshare.pattern.proxy;

import com.driveshare.model.Payment;
import com.driveshare.util.ValidationUtil;

/**
 * Proxy class that validates payment requests before
 * forwarding them to the real payment service.
 */
public class PaymentProxy implements PaymentServiceInterface {

    private final RealPaymentService realPaymentService;

    /**
     * Creates the proxy with the real payment service.
     */
    public PaymentProxy() {
        this.realPaymentService = new RealPaymentService();
    }

    /**
     * Validates the payment before sending it to the real service.
     *
     * @param payment the payment to process
     * @return true if valid and successful, false otherwise
     */
    @Override
    public boolean processPayment(Payment payment) {

        // Validate payment object
        if (payment == null) {
            System.out.println("Payment failed: payment object is null.");
            return false;
        }

        // Validate required fields
        if (payment.getBookingId() <= 0 ||
                ValidationUtil.isNullOrEmpty(payment.getPaymentDate()) ||
                ValidationUtil.isNullOrEmpty(payment.getStatus())) {

            System.out.println("Payment failed: missing required fields.");
            return false;
        }

        // Validate amount
        if (!ValidationUtil.isPositiveDouble(payment.getAmount())) {
            System.out.println("Payment failed: invalid payment amount.");
            return false;
        }

        // Forward to real service if validation succeeds
        return realPaymentService.processPayment(payment);
    }
}