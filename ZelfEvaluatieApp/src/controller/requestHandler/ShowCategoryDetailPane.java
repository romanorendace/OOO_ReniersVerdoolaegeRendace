package controller.requestHandler;

import controller.CategoryController;
import view.panels.Pane;

public class ShowCategoryDetailPane extends RequestHandler {

    CategoryController controller;

    @Override
    public void handleRequest() {
        controller = (CategoryController) getController();
        Pane pane = controller.getPane();

        pane.addGridPaneToBorderPane(controller.getCategoryDetailPane());
        pane.getStage().show();
    }
}
