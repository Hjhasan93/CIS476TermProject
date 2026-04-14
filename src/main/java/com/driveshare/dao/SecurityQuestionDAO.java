package com.driveshare.dao;

import com.driveshare.model.SecurityQuestion;
import com.driveshare.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database operations related to the security_questions table.
 */
public class SecurityQuestionDAO {

    /**
     * Inserts a security question into the database.
     *
     * @param question the security question object to insert
     * @return true if insertion succeeds, false otherwise
     */
    public boolean insertSecurityQuestion(SecurityQuestion question) {
        String sql = "INSERT INTO security_questions (user_id, question_text, answer_text, question_order) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set query parameters
            statement.setInt(1, question.getUserId());
            statement.setString(2, question.getQuestionText());
            statement.setString(3, question.getAnswerText());
            statement.setInt(4, question.getQuestionOrder());

            // Execute insert
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting security question: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves all security questions for a specific user ordered by question_order.
     *
     * @param userId the user ID
     * @return list of security questions
     */
    public List<SecurityQuestion> getQuestionsByUserId(int userId) {
        String sql = "SELECT * FROM security_questions WHERE user_id = ? ORDER BY question_order";
        List<SecurityQuestion> questions = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set user ID parameter
            statement.setInt(1, userId);

            // Execute query
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                SecurityQuestion question = new SecurityQuestion();
                question.setQuestionId(resultSet.getInt("question_id"));
                question.setUserId(resultSet.getInt("user_id"));
                question.setQuestionText(resultSet.getString("question_text"));
                question.setAnswerText(resultSet.getString("answer_text"));
                question.setQuestionOrder(resultSet.getInt("question_order"));

                questions.add(question);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving security questions: " + e.getMessage());
        }

        return questions;
    }
}