package controller;


import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;
import view.panels.Pane;


public class CategoryController extends Controller {

    private CategoryOverviewPane categoryOverviewPane;
    private CategoryDetailPane categoryDetailPane;
    private Pane pane;

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

    @Override
    protected Controller getController() {
        return this;
    }
}
