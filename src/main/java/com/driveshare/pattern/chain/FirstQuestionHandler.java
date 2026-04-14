package com.driveshare.pattern.chain;

import com.driveshare.dao.SecurityQuestionDAO;
import com.driveshare.model.SecurityQuestion;

import java.util.List;

/**
 * Validates the answer to the first security question.
 */
public class FirstQuestionHandler extends RecoveryHandler {

    private final SecurityQuestionDAO securityQuestionDAO;

    /**
     * Creates a handler with DAO dependency.
     */
    public FirstQuestionHandler() {
        this.securityQuestionDAO = new SecurityQuestionDAO();
    }

    /**
     * Checks the first answer. If correct, passes control to the next handler.
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

        // Compare the first answer ignoring case and extra spaces
        String correctAnswer = questions.get(0).getAnswerText().trim();
        String givenAnswer = context.getAnswer1().trim();

        if (!correctAnswer.equalsIgnoreCase(givenAnswer)) {
            return false;
        }

        // Pass to next handler if available
        if (nextHandler != null) {
            return nextHandler.handle(context);
        }

        return true;
    }
}