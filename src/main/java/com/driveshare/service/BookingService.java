package com.driveshare.service;

import com.driveshare.dao.BookingDAO;
import com.driveshare.model.Booking;
import com.driveshare.util.ValidationUtil;

/**
 * Handles business logic related to bookings.
 */
public class BookingService {

    private final BookingDAO bookingDAO;

    /**
     * Creates the service with required DAO dependency.
     */
    public BookingService() {
        this.bookingDAO = new BookingDAO();
    }

    /**
     * Validates and creates a booking if no overlap exists.
     *
     * @param booking the booking to create
     * @return true if booking succeeds, false otherwise
     */
    public boolean createBooking(Booking booking) {

        // Validate booking object
        if (booking == null) {
            System.out.println("Booking failed: booking object is null.");
            return false;
        }

        // Validate required fields
        if (booking.getCarId() <= 0 ||
                booking.getRenterId() <= 0 ||
                ValidationUtil.isNullOrEmpty(booking.getStartDate()) ||
                ValidationUtil.isNullOrEmpty(booking.getEndDate()) ||
                ValidationUtil.isNullOrEmpty(booking.getStatus())) {

            System.out.println("Booking failed: missing required fields.");
            return false;
        }

        // Validate amount
        if (!ValidationUtil.isPositiveDouble(booking.getTotalAmount())) {
            System.out.println("Booking failed: invalid total amount.");
            return false;
        }

        // Check overlap rule
        boolean hasOverlap = bookingDAO.hasOverlappingBooking(
                booking.getCarId(),
                booking.getStartDate(),
                booking.getEndDate()
        );

        if (hasOverlap) {
            System.out.println("Booking failed: overlapping booking exists for this car.");
            return false;
        }

        // Insert booking if valid
        return bookingDAO.insertBooking(booking);
    }
}