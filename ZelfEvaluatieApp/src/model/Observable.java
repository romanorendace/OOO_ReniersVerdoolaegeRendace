package model;

public interface Observable {

    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyOberservers(Object args);
}
