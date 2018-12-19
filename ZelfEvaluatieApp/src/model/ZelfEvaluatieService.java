package model;

import controller.Controller;
import db.CategoryDB;
import db.QuestionDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Facade Class
public class ZelfEvaluatieService implements Observable {

    private Controller categoryController;
    private Controller questionController;
    private Controller testController;

    private List<Observer> observers;

    private CategoryDB categoryDB;
    private QuestionDB questionDB;

    public ZelfEvaluatieService() {
        categoryDB = CategoryDB.getInstance();
        questionDB = QuestionDB.getInstance();
        observers = new ArrayList<>();
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
    public void notifyObservers(Object args) {
        for (Observer o : observers) {
            o.update(this, null);
        }
        categoryDB.notifyObservers(args);
        questionDB.notifyObservers(args);
    }

    public CategoryDB getCategoryDB() {
        return categoryDB;
    }

    public QuestionDB getQuestionDB() {
        return questionDB;
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



    public void updateCategory(Category original, Category edited) { categoryDB.updateCategory(original, edited); }

    public Category getCategory(String title) {
        return categoryDB.getCategory(title);
    }

    public Set<Category> getCategories() {
        return categoryDB.getCategorySet();
    }

    public void saveNewCategory(Category category) {
        categoryDB.saveNewCategory(category);
    }

    public void saveNewQuestion(Question question) { questionDB.saveNewQuestion(question);}

    public void updateQuestion(Question original, Question edited) {
        questionDB.updateQuestion(original, edited);
    }

    public void loadDataFromStorageIntoLocalMemory() {
        categoryDB.loadDataInLocalMemory();
        questionDB.loadDataInLocalMemory();
    }

    public TestAttempt generateTestAttempt() {
        TestAttempt test;
        Map<Question,Boolean> questions = questionDB.getQuestionMapInRandomOrder();
        test = new TestAttempt();
        test.setQuestionsAndIsCorrectlyAnswered(questions);
        return test;
    }
}
