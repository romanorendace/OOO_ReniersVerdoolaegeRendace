package controller.requestHandler;

import controller.CategoryController;
import model.Category;
import view.panels.CategoryDetailPane;

public class EditCategory extends RequestHandler {

    CategoryController controller;
    CategoryDetailPane categoryDetailPane;

    @Override
    public void handleRequest() {
        controller = (CategoryController) getController();
        Category originalCategory = controller.getOriginalCategoryToEdit();
        Category editedCategory = getEditedCategory();

        if (!originalCategory.equals(editedCategory)) {
            controller.getService().updateCategory(originalCategory, editedCategory);
        }
        controller.getPane().getStage().close();
    }

    private Category getEditedCategory() {
        categoryDetailPane = controller.getCategoryDetailPane();
        String title = categoryDetailPane.getTitleFieldString();
        String description = categoryDetailPane.getDescriptionFieldString();
        String mainCategoryTitle = categoryDetailPane.getCategoryFieldString();

        return new Category(title, description, mainCategoryTitle);
    }
}
