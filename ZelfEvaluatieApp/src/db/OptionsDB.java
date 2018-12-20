package db;

import model.Observable;
import model.Observer;

import java.util.ArrayList;
import java.util.List;

// SINGLETON
public class OptionsDB implements Observable {

    private static db.OptionsDB uniqueInstance;
    private String method;

    private List<Observer> observerList;

    private OptionsDB(){
        method = "score";

        observerList = new ArrayList<>();
    }


    public static synchronized OptionsDB getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new OptionsDB();
        }
        return uniqueInstance;
    }

    public void setMethod(String method){
        this.method=method;
    }

    public String getMethod(){
        return method;
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
