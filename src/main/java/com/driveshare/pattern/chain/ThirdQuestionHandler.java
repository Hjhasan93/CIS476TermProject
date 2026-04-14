package com.driveshare.pattern.chain;

import com.driveshare.dao.SecurityQuestionDAO;
import com.driveshare.model.SecurityQuestion;

import java.util.List;

/**
 * Validates the answer to the third security question.
 * If correct, recovery succeeds.
 */
public class ThirdQuestionHandler extends RecoveryHandler {

    private final SecurityQuestionDAO securityQuestionDAO;

    /**
     * Creates a handler with DAO dependency.
     */
    public ThirdQuestionHandler() {
        this.securityQuestionDAO = new SecurityQuestionDAO();
    }

    /**
     * Checks the third answer and marks recovery as successful if correct.
     *
     * @param context recovery context
     * @return true if successful, false otherwise
     */
    @Override
    public boolean handle(RecoveryContext context) {
        List<SecurityQuestion> questions = securityQuestionDAO.getQuestionsByUserId(context.getUserId());

        // Make sure at least 3 questions exist
        if (questions.size() < 3) {
            return false;
        }

        // Compare the third answer ignoring case and extra spaces
        String correctAnswer = questions.get(2).getAnswerText().trim();
        String givenAnswer = context.getAnswer3().trim();

        if (!correctAnswer.equalsIgnoreCase(givenAnswer)) {
            return false;
        }

        // Mark the recovery process as successful
        context.setSuccess(true);
        return true;
    }
}