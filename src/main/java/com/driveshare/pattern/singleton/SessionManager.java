package com.driveshare.pattern.singleton;

import com.driveshare.model.User;

/**
 * Singleton class used to manage the currently logged-in user session.
 * Only one instance of this class can exist in the system.
 */
public class SessionManager {

    // The single shared instance of SessionManager
    private static SessionManager instance;

    // Stores the currently logged-in user
    private User currentUser;

    /**
     * Private constructor prevents external object creation.
     */
    private SessionManager() {
    }

    /**
     * Returns the single instance of SessionManager.
     * Creates it if it does not already exist.
     *
     * @return the single SessionManager instance
     */
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    /**
     * Stores the logged-in user in the session.
     *
     * @param user the authenticated user
     */
    public void loginUser(User user) {
        this.currentUser = user;
    }

    /**
     * Clears the current session user.
     */
    public void logout() {
        this.currentUser = null;
    }

    /**
     * Returns the currently logged-in user.
     *
     * @return current user, or null if no user is logged in
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Checks whether there is an active logged-in user.
     *
     * @return true if a user is logged in, otherwise false
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }
}