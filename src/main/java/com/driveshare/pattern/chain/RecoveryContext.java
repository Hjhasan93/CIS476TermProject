package com.driveshare.pattern.chain;

/**
 * Holds all data needed during the password recovery process.
 * This object is passed through the chain of handlers.
 */
public class RecoveryContext {

    private int userId;
    private String answer1;
    private String answer2;
    private String answer3;
    private boolean success;

    /**
     * Default constructor.
     */
    public RecoveryContext() {
    }

    /**
     * Constructor used to initialize the recovery request.
     *
     * @param userId the user ID being recovered
     * @param answer1 answer to the first security question
     * @param answer2 answer to the second security question
     * @param answer3 answer to the third security question
     */
    public RecoveryContext(int userId, String answer1, String answer2, String answer3) {
        this.userId = userId;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.success = false;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}