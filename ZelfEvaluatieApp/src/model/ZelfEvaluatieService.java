package model;

import controller.Controller;
import db.CategoryManager;
import db.QuestionManager;

import java.util.ArrayList;
import java.util.List;

// Facade Class
public class ZelfEvaluatieService implements Observable {

    private Controller categoryController;
    private Controller questionController;
    private Controller testController;

    private List<Observer> observers;

    private CategoryManager categoryManager;
    private QuestionManager questionManager;

    public ZelfEvaluatieService() {
        categoryManager = new CategoryManager();
        questionManager = new QuestionManager();
        observers = new ArrayList<>();
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

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyOberservers(Object args) {
        for (Observer o : observers) {
            o.update(this, null);
        }
    }
}
