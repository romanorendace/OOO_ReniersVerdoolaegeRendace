package controller.requestHandler;

import controller.CategoryController;
import model.Category;
import view.panels.CategoryDetailPane;

/**
 * @author Romano Rendace
 */
public class ShowEditCategoryDetailPane extends RequestHandler {

    CategoryController controller;
    CategoryDetailPane categoryDetailPane;
    Category category;

    @Override
    public void handleRequest() {
        controller = (CategoryController) getController();
        category = controller.getCategoryOverviewPane().getSelectedCategory();
        categoryDetailPane = controller.getCategoryDetailPane();

        categoryDetailPane.setTitleField(category.getTitle());
        categoryDetailPane.setDescriptionField(category.getDescription());
        categoryDetailPane.setCategoryField(category.getMainCategoryTitle());

        categoryDetailPane.setSaveActionForEdit();

        controller.setOriginalCategoryToEdit(category);
        controller.getPane().addGridPaneToBorderPane(controller.getCategoryDetailPane());
        controller.getPane().getStage().show();
    }
}
