package com.driveshare.model;

public class User {

    private int userId;
    private String fullName;
    private String email;
    private String password;
    private double balance;

    public User() {
    }

    public User(int userId, String fullName, String email, String password, double balance) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public User(String fullName, String email, String password, double balance) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                '}';
    }
}