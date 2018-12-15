package controller.requestHandler;

import controller.QuestionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AddStatement extends RequestHandler {

    QuestionController controller;

    @Override
    public void handleRequest() {
        controller = (QuestionController) getController();

        String statement = controller.getQuestionDetailPane().getStatementFieldString();
        if (isValidStatement(statement)) {
            controller.getStatements().add(statement);
            updateStatementsListOverview();
        }
        clearInputFields();
    }


    public boolean isValidStatement(String statement) {
        return statement != null && !statement.trim().isEmpty();
    }

    public void updateStatementsListOverview() {
        ObservableList<String> statementsObservableList = FXCollections.observableList(controller.getStatements());
        controller.getQuestionDetailPane().updateStatementsInView(statementsObservableList);
    }

    public void clearInputFields() {
        controller.getQuestionDetailPane().clearInputFields();
    }

}
