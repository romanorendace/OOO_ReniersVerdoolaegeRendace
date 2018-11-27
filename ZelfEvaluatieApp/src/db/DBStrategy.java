package db;

public interface DBStrategy {

    void loadFromStorage();

    void saveToStorage();
}
