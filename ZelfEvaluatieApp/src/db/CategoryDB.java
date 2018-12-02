package db;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Category;
import model.Observable;
import model.Observer;
import java.util.*;

// SINGLETON
public class CategoryDB implements Observable {

    private static CategoryDB uniqueInstance;
    private Set<Category> categorySet;
    private DBStrategy dbStrategy;

    private List<Observer> observerList;

    private CategoryDB() {
        categorySet = new LinkedHashSet<>();
        dbStrategy = new CategoryTextDBStrategy();
        observerList = new ArrayList<>();
    }

    public static synchronized CategoryDB getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CategoryDB();
        }
        return uniqueInstance;
    }

    public void setDbStrategy(DBStrategy dbStrategy) {
        this.dbStrategy = dbStrategy;
    }

    public void setCategorySet(Set<Category> categorySet) {
        this.categorySet = categorySet;
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }


    public Category getCategory(String title) {
        for (Category c : categorySet) {
            if (c.getTitle().equals(title))
                return c;
        }
        return null;
    }

    public void saveNewCategory(Category category) {
        categorySet.add(category);
        saveCategoryToStorage(category);
    }

    private void saveCategoryToStorage(Category category) {
        dbStrategy.saveObjectToStorage(category);
        notifyObservers(null);
    }

    public void loadDataInLocalMemory() {
        dbStrategy.loadFromStorage();
    }

    public void saveDataToStorage() {
        dbStrategy.saveToStorage();
    }

    public ObservableList<Category> getObservableListOfCategories() {
        return FXCollections.observableArrayList(categorySet);
    }

    public ObservableList<String> getObservableListOfCategoryTitles() {
        List<String> categoryTitles = new ArrayList<>();
        for (Category c : categorySet) {
            categoryTitles.add(c.getTitle());
        }
        return FXCollections.observableArrayList(categoryTitles);
    }


    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers(Object args) {
        for (Observer o : observerList) {
            o.update(this, args);
        }
    }
}
