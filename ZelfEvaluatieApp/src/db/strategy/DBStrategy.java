package db.strategy;

import model.Category;

import java.util.Collection;

/**
 * @author Romano Rendace
 */
public interface DBStrategy {

    void loadFromStorage();

    void saveToStorage();

    void saveObjectToStorage(Object object);
}
