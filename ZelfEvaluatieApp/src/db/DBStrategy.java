package db;

import model.Category;

import java.util.Collection;

public interface DBStrategy {

    void loadFromStorage(Collection<Object> objectCollection);

    void saveToStorage(Collection<Object> objectCollection);

    void saveObjectToStorage(Object object);
}
