package db;


import model.MultipleChoiceQuestion;
import model.Observable;
import model.Observer;
import model.Question;

import java.util.*;

// SINGLETON
public class QuestionDB implements Observable {

    private static QuestionDB uniqueInstance;
    private Set<Question> multipleChoiceQuestionSet;
    private DBStrategy dbStrategy;

    private QuestionDB() {
        multipleChoiceQuestionSet = new LinkedHashSet<>();
        dbStrategy = new QuestionTextDBStrategy();
    }

    public static synchronized QuestionDB getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new QuestionDB();
        }
        return uniqueInstance;
    }

    public  void saveNewQuestion(Question q){
        multipleChoiceQuestionSet.add(q);
        //notify observers
    }

    public void setDbStrategy(DBStrategy dbStrategy) {
        this.dbStrategy = dbStrategy;
    }

    @Override
    public void registerObserver(Observer o) {

    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void notifyOberservers(Object args) {

    }
}
