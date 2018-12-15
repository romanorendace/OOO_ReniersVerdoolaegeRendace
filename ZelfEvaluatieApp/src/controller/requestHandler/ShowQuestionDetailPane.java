package controller.requestHandler;

import controller.QuestionController;
import view.panels.Pane;

public class ShowQuestionDetailPane extends RequestHandler {

    @Override
    public void handleRequest() {
        QuestionController controller = (QuestionController) getController();
        Pane pane = controller.getPane();

        pane.addGridPaneToBorderPane(controller.getQuestionDetailPane());
        pane.getStage().show();
    }
}
