package controller.requestHandler;

import controller.QuestionController;


public class RemoveStatement extends RequestHandler {

    QuestionController controller;

    @Override
    public void handleRequest() {
        controller = (QuestionController) getController();
        String statement = controller.getQuestionDetailPane().getSelectedStatement();
        controller.getStatements().remove(statement);
        controller.updateStatementsListOverview();
    }

}
