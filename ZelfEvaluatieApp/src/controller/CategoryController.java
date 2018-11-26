package controller;

import model.ZelfEvaluatieService;
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;
import view.panels.ViewPane;

public class CategoryController implements Controller {

    private ZelfEvaluatieService service;

    private ViewPane categoryOverviewPane;
    private ViewPane categoryDetailPane;

    public CategoryController() {
    }

    public void setService(ZelfEvaluatieService service) {
        this.service = service;
    }

    public void setCategoryOverviewPane(ViewPane categoryOverviewPane) {
        this.categoryOverviewPane = categoryOverviewPane;
    }

    public void setCategoryDetailPane(ViewPane categoryDetailPane) {
        this.categoryDetailPane = categoryDetailPane;
    }

    @Override
    public void handleRequest(ViewPane viewPane) {

    }
}
