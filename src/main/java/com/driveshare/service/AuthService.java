package com.driveshare.service;

import com.driveshare.dao.SecurityQuestionDAO;
import com.driveshare.dao.UserDAO;
import com.driveshare.model.SecurityQuestion;
import com.driveshare.model.User;
import com.driveshare.util.ValidationUtil;

import java.util.List;

/**
 * Handles authentication-related business logic such as
 * registration and login.
 */
public class AuthService {

    private final UserDAO userDAO;
    private final SecurityQuestionDAO securityQuestionDAO;

    /**
     * Creates an AuthService with required DAO dependencies.
     */
    public AuthService() {
        this.userDAO = new UserDAO();
        this.securityQuestionDAO = new SecurityQuestionDAO();
    }

    /**
     * Registers a new user and their three security questions.
     *
     * @param user the user to register
     * @param securityQuestions the list of 3 security questions
     * @return true if registration succeeds, false otherwise
     */
    public boolean registerUser(User user, List<SecurityQuestion> securityQuestions) {

        // Validate user object
        if (user == null) {
            System.out.println("Registration failed: user object is null.");
            return false;
        }

        // Validate basic user fields
        if (ValidationUtil.isNullOrEmpty(user.getFullName()) ||
                ValidationUtil.isNullOrEmpty(user.getEmail()) ||
                ValidationUtil.isNullOrEmpty(user.getPassword())) {

            System.out.println("Registration failed: required user fields are missing.");
            return false;
        }

        // Validate email format
        if (!ValidationUtil.isValidEmail(user.getEmail())) {
            System.out.println("Registration failed: invalid email format.");
            return false;
        }

        // Validate exactly 3 security questions
        if (securityQuestions == null || securityQuestions.size() != 3) {
            System.out.println("Registration failed: exactly 3 security questions are required.");
            return false;
        }

        // Check if email already exists
        if (userDAO.findUserByEmail(user.getEmail()) != null) {
            System.out.println("Registration failed: email already exists.");
            return false;
        }

        // Insert user first
        boolean userInserted = userDAO.insertUser(user);
        if (!userInserted) {
            System.out.println("Registration failed: could not insert user.");
            return false;
        }

        // Retrieve inserted user to get generated user_id
        User insertedUser = userDAO.findUserByEmail(user.getEmail());
        if (insertedUser == null) {
            System.out.println("Registration failed: inserted user could not be retrieved.");
            return false;
        }

        // Insert the 3 security questions linked to the new user
        for (SecurityQuestion question : securityQuestions) {
            question.setUserId(insertedUser.getUserId());

            boolean questionInserted = securityQuestionDAO.insertSecurityQuestion(question);
            if (!questionInserted) {
                System.out.println("Registration warning: failed to insert one of the security questions.");
                return false;
            }
        }

        return true;
    }

    /**
     * Validates login credentials.
     *
     * @param email entered email
     * @param password entered password
     * @return User object if valid, otherwise null
     */
    public User loginUser(String email, String password) {

        // Validate input fields
        if (ValidationUtil.isNullOrEmpty(email) || ValidationUtil.isNullOrEmpty(password)) {
            System.out.println("Login failed: email or password is empty.");
            return null;
        }

        // Validate email format
        if (!ValidationUtil.isValidEmail(email)) {
            System.out.println("Login failed: invalid email format.");
            return null;
        }

        // Ask DAO to validate login
        return userDAO.validateLogin(email, password);
    }
}