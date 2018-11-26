package model;

import controller.Controller;
import db.CategoryManager;
import db.QuestionManager;

// Facade Class
public class ZelfEvaluatieService {

    private Controller categoryController;
    private Controller questionController;
    private Controller testController;

    private CategoryManager categoryManager;
    private QuestionManager questionManager;

    public ZelfEvaluatieService() {
        categoryManager = new CategoryManager();
        questionManager = new QuestionManager();
    }

    public CategoryManager getCategoryManager() {
        return categoryManager;
    }

    public QuestionManager getQuestionManager() {
        return questionManager;
    }

    public void setCategoryController(Controller categoryController) {
        this.categoryController = categoryController;
    }

    public void setQuestionController(Controller questionController) {
        this.questionController = questionController;
    }

    public void setTestController(Controller testController) {
        this.testController = testController;
    }
}
