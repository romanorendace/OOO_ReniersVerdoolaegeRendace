package controller.requestHandler;

import controller.QuestionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class CancelQuestion extends RequestHandler {

    QuestionController controller;

    @Override
    public void handleRequest() {
        controller = (QuestionController) getController();
        resetStatementsList();
        resetStatementsListOverview();
        clearInputFields();
        controller.getPane().getStage().close();
    }

    public void resetStatementsList() {
        controller.setStatements(new ArrayList<>());
    }

    public void resetStatementsListOverview() {
        updateStatementsListOverview();
    }

    public void updateStatementsListOverview() {
        ObservableList<String> statementsObservableList = FXCollections.observableList(controller.getStatements());
        controller.getQuestionDetailPane().updateStatementsInView(statementsObservableList);
    }

    public void clearInputFields() {
        controller.getQuestionDetailPane().clearInputFields();
    }
}
