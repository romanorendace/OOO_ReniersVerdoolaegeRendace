package controller.requestHandler;

import controller.CategoryController;
import view.panels.Pane;

public class CancelCategory extends RequestHandler {

    CategoryController controller;

    @Override
    public void handleRequest() {
        controller = (CategoryController) getController();
        Pane pane = controller.getPane();
        pane.getStage().close();
    }
}
