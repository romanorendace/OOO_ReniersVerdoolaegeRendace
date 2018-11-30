package db;

import model.Category;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public abstract class TextDBStrategy implements DBStrategy {

    Set<Category> categorySet;


    // TEMPLATE METHOD
    @Override
    public void loadFromStorage(Collection<Object> objectCollection) {
        setCategorySet(objectCollection);
        setupConnectionToStorage();
        getDataFromStorage();
        setDataInLocalMemory();
    }


    // TEMPLATE METHOD
    @Override
    public void saveToStorage(Collection<Object> objectCollection) {
        setCategorySet(objectCollection);
        setupConnectionToStorage();
        setDataToStorage();
    }


    public void setCategorySet(Collection<Object> objectCollection) {
        categorySet = new LinkedHashSet<>();
        if (objectCollection != null) {
            for(Object o : objectCollection) {
                if (o instanceof Category) {
                    categorySet.add((Category)o);
                }
            }
        }
        CategoryDB categoryDB = CategoryDB.getInstance();
        categoryDB.setCategorySet(categorySet);
    }

    protected Set<Category> getCategorySet() {
        return categorySet;
    }

    public abstract void setupConnectionToStorage();

    public abstract void getDataFromStorage();

    public abstract void setDataInLocalMemory();

    public abstract void setDataToStorage();


}
