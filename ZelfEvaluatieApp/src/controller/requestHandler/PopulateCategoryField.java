package controller.requestHandler;

import controller.CategoryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Category;
import java.util.Set;

public class PopulateCategoryField extends RequestHandler {

    CategoryController controller;

    @Override
    public void handleRequest() {
        controller = (CategoryController) getController();

        ObservableList<String> categoryList = FXCollections.observableArrayList();
        categoryList.add("None");

        Set<Category> categories = controller.getService().getCategories();
        for (Category category : categories) {
            categoryList.add(category.getTitle());
        }
        controller.getCategoryDetailPane().setCategoryField(categoryList);
    }
}
