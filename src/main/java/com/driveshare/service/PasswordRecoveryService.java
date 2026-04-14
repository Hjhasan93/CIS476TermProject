package com.driveshare.service;

import com.driveshare.dao.UserDAO;
import com.driveshare.model.User;
import com.driveshare.pattern.chain.FirstQuestionHandler;
import com.driveshare.pattern.chain.RecoveryContext;
import com.driveshare.pattern.chain.SecondQuestionHandler;
import com.driveshare.pattern.chain.ThirdQuestionHandler;
import com.driveshare.util.ValidationUtil;

/**
 * Handles password recovery business logic using
 * the Chain of Responsibility pattern.
 */
public class PasswordRecoveryService {

    private final UserDAO userDAO;

    /**
     * Creates the service with required DAO dependency.
     */
    public PasswordRecoveryService() {
        this.userDAO = new UserDAO();
    }

    /**
     * Validates the 3 security question answers in sequence.
     *
     * @param email the user email
     * @param answer1 answer to question 1
     * @param answer2 answer to question 2
     * @param answer3 answer to question 3
     * @return true if all answers are correct, false otherwise
     */
    public boolean recoverPassword(String email, String answer1, String answer2, String answer3) {

        // Validate input values
        if (ValidationUtil.isNullOrEmpty(email) ||
                ValidationUtil.isNullOrEmpty(answer1) ||
                ValidationUtil.isNullOrEmpty(answer2) ||
                ValidationUtil.isNullOrEmpty(answer3)) {

            System.out.println("Recovery failed: all fields are required.");
            return false;
        }

        // Find the user by email
        User user = userDAO.findUserByEmail(email);
        if (user == null) {
            System.out.println("Recovery failed: user not found.");
            return false;
        }

        // Build recovery context
        RecoveryContext context = new RecoveryContext(
                user.getUserId(),
                answer1,
                answer2,
                answer3
        );

        // Create the chain
        FirstQuestionHandler firstHandler = new FirstQuestionHandler();
        SecondQuestionHandler secondHandler = new SecondQuestionHandler();
        ThirdQuestionHandler thirdHandler = new ThirdQuestionHandler();

        firstHandler.setNextHandler(secondHandler);
        secondHandler.setNextHandler(thirdHandler);

        // Start the chain
        return firstHandler.handle(context);
    }

    /**
     * Returns the user's password only if the answers are all correct.
     * This is acceptable for your academic project.
     *
     * @param email the user email
     * @param answer1 answer to question 1
     * @param answer2 answer to question 2
     * @param answer3 answer to question 3
     * @return the password if recovery succeeds, otherwise null
     */
    public String getRecoveredPassword(String email, String answer1, String answer2, String answer3) {
        boolean success = recoverPassword(email, answer1, answer2, answer3);

        if (!success) {
            return null;
        }

        User user = userDAO.findUserByEmail(email);
        return user != null ? user.getPassword() : null;
    }
}