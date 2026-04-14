package com.driveshare.pattern.proxy;

import com.driveshare.dao.PaymentDAO;
import com.driveshare.model.Payment;

/**
 * Real payment service that performs the actual payment storage.
 */
public class RealPaymentService implements PaymentServiceInterface {

    private final PaymentDAO paymentDAO;

    /**
     * Creates the real service with DAO dependency.
     */
    public RealPaymentService() {
        this.paymentDAO = new PaymentDAO();
    }

    /**
     * Inserts the payment into the database.
     *
     * @param payment the payment to process
     * @return true if successful, false otherwise
     */
    @Override
    public boolean processPayment(Payment payment) {
        return paymentDAO.insertPayment(payment);
    }
}