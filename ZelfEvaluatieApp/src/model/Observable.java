package model;

/**
 * @author Romano Rendace
 */
public interface Observable {

    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(Object args);
}
