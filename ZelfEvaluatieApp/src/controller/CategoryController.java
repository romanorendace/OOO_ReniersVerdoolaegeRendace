package controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Category;
import model.Observable;
import model.Observer;
import model.ZelfEvaluatieService;
import view.panels.AssesMainPane;
import view.panels.CategoryDetailPane;
import view.panels.CategoryOverviewPane;
import db.CategoryManager;
import view.panels.ViewPane;

public class CategoryController implements Controller, Observer {

    private ZelfEvaluatieService service;

    private CategoryOverviewPane categoryOverviewPane;
    private CategoryDetailPane categoryDetailPane;

    private Group root;
    private Scene scene;
    private BorderPane borderPane;
    private Stage stage;

    public CategoryController() {
    }

    public void setService(ZelfEvaluatieService service) {
        this.service = service;
    }

    public void setCategoryOverviewPane(CategoryOverviewPane categoryOverviewPane) {
        this.categoryOverviewPane = categoryOverviewPane;
    }

    public void setCategoryDetailPane(CategoryDetailPane categoryDetailPane) {
        this.categoryDetailPane = categoryDetailPane;
    }


    @Override
    public void handleRequest(String  action) {
        if (action.equals("ShowCategoryDetailPane")) {
            showCategoryDetailPane();
        }
        else if (action.equals("SaveCategory")) {
            saveCategory();
        }
        else if (action.equals("cancelCategory")) {
            cancelCategory();
        }
    }

    public void showCategoryDetailPane() {
        stage = new Stage();
        root = new Group();
        scene = new Scene(root, 750, 400);
        borderPane = new BorderPane(categoryDetailPane);

        root.getChildren().add(borderPane);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public void saveCategory() {
        String title = categoryDetailPane.getTitleFieldString();
        String description = categoryDetailPane.getDescriptionFieldString();
        String mainCategoryString = categoryDetailPane.getCategoryFieldString();
        Category mainCategory = null;
        if (!mainCategoryString.trim().equals("")) {
            service.getCategory(mainCategoryString);
        }
        Category category = new Category(title, description, mainCategory);

        service.saveNewCategory(category);
        stage.close();
    }

    public void cancelCategory() {
        stage.close();
    }

    @Override
    public void update(Observable o, Object args) {

    }
}
