package db;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Observable;
import model.Observer;
import model.Question;

import java.util.*;

// SINGLETON
public class QuestionDB implements Observable {

    private static QuestionDB uniqueInstance;
    private Set<Question> questionSet;
    private DBStrategy dbStrategy;

    private List<Observer> observerList;

    private QuestionDB() {
        setQuestionSet(new LinkedHashSet<>());
        setDbStrategy(new QuestionTextDBStrategy());
        observerList = new ArrayList<>();
    }

    public static synchronized QuestionDB getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new QuestionDB();
        }
        return uniqueInstance;
    }

    public void setDbStrategy(DBStrategy dbStrategy) {
        this.dbStrategy = dbStrategy;
    }

    public Set<Question> getQuestionSet() {
        return questionSet;
    }

    public Map<Question,Boolean> getQuestionMapInRandomOrder(){
        Map<Question,Boolean> questionMap = new HashMap<>();
        List<Question> questions = new ArrayList<>(questionSet);
        Collections.shuffle(questions);
        for (Question question:questions) {
            questionMap.put(question,false);
        }
        return questionMap;
    }

    public void setQuestionSet(Set<Question> questionSet) {
        this.questionSet = questionSet;
    }

    public  void saveNewQuestion(Question question){
        questionSet.add(question);
        saveQuestionToStorage(question);
    }

    private void saveQuestionToStorage(Question question) {
        dbStrategy.saveObjectToStorage(question);
        notifyObservers(null);
    }

    public void loadDataInLocalMemory() {
        dbStrategy.loadFromStorage();
    }

    public ObservableList<Question> getObservableListOfQuestions() {
        return FXCollections.observableArrayList(questionSet);
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
