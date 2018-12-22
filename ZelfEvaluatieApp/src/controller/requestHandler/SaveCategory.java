package controller.requestHandler;

import controller.CategoryController;
import model.Category;
import view.panels.CategoryDetailPane;

/**
 * @author Romano Rendace
 */
public class SaveCategory extends RequestHandler {

    CategoryController controller;

    @Override
    public void handleRequest() {
        controller = (CategoryController) getController();
        CategoryDetailPane categoryDetailPane = controller.getCategoryDetailPane();

        String title = categoryDetailPane.getTitleFieldString();
        String description = categoryDetailPane.getDescriptionFieldString();
        String mainCategoryTitle = categoryDetailPane.getCategoryFieldString();
        Category category = new Category(title, description, mainCategoryTitle);

        controller.getService().saveNewCategory(category);
        controller.getPane().getStage().close();
    }
}
