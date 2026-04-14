package com.driveshare.model;

/**
 * Represents a security question used for password recovery.
 * Each user must have exactly 3 questions.
 */
public class SecurityQuestion {

    private int questionId;
    private int userId;
    private String questionText;
    private String answerText;
    private int questionOrder;

    public SecurityQuestion() {
    }

    public SecurityQuestion(int userId, String questionText, String answerText, int questionOrder) {
        this.userId = userId;
        this.questionText = questionText;
        this.answerText = answerText;
        this.questionOrder = questionOrder;
    }

    // Getters and Setters

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public int getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(int questionOrder) {
        this.questionOrder = questionOrder;
    }
}