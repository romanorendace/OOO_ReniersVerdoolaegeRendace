package controller;


import model.Category;
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;
import view.panels.Pane;


public class CategoryController extends Controller {

    private CategoryOverviewPane categoryOverviewPane;
    private CategoryDetailPane categoryDetailPane;
    private Pane pane;

    private Category originalCategoryToEdit;

    public CategoryController() {
        pane = new Pane();
    }


    // GETTERS & SETTERS
    public void setCategoryOverviewPane(CategoryOverviewPane categoryOverviewPane) {
        this.categoryOverviewPane = categoryOverviewPane;
    }
    public void setCategoryDetailPane(CategoryDetailPane categoryDetailPane) {
        this.categoryDetailPane = categoryDetailPane;
    }

    public CategoryOverviewPane getCategoryOverviewPane() {
        return categoryOverviewPane;
    }
    public CategoryDetailPane getCategoryDetailPane() {
        return categoryDetailPane;
    }

    public Pane getPane() {
        return pane;
    }

    public Category getOriginalCategoryToEdit() {
        return originalCategoryToEdit;
    }
    public void setOriginalCategoryToEdit(Category originalCategoryToEdit) {
        this.originalCategoryToEdit = originalCategoryToEdit;
    }

    @Override
    protected Controller getController() {
        return this;
    }
}
