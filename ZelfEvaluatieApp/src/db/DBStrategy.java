package db;

import model.Category;

import java.util.Collection;

public interface DBStrategy {

    void loadFromStorage();

    void saveToStorage();

    void saveObjectToStorage(Object object);
}
