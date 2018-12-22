package controller.requestHandler;

import controller.QuestionController;
import view.panels.Pane;

/**
 * @author Romano Rendace
 */
public class ShowQuestionDetailPane extends RequestHandler {

    @Override
    public void handleRequest() {
        QuestionController controller = (QuestionController) getController();
        Pane pane = controller.getPane();

        pane.addGridPaneToBorderPane(controller.getQuestionDetailPane());
        pane.getStage().show();
    }
}
