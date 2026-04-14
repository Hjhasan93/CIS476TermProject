package com.driveshare.util;

import java.util.regex.Pattern;

public class ValidationUtil {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private ValidationUtil() {
        // Prevent instantiation
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        }
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isPositiveDouble(double value) {
        return value > 0;
    }

    public static boolean isPositiveInt(int value) {
        return value > 0;
    }
}