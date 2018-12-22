package controller.requestHandler;

import controller.QuestionController;

/**
 * @author Romano Rendace
 */
public class AddStatement extends RequestHandler {

    QuestionController controller;

    @Override
    public void handleRequest() {
        controller = (QuestionController) getController();

        String statement = controller.getQuestionDetailPane().getStatementFieldString();
        if (isValidStatement(statement)) {
            controller.getStatements().add(statement);
            controller.updateStatementsListOverview();
        }
        controller.getQuestionDetailPane().clearStatementField();
    }

    public boolean isValidStatement(String statement) {
        return statement != null && !statement.trim().isEmpty();
    }
}
