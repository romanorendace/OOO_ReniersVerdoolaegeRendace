package db;


import model.MultipleChoiceQuestion;

import java.util.*;

// SINGLETON
public class QuestionDB {

    private static QuestionDB uniqueInstance;
    private Set<MultipleChoiceQuestion> multipleChoiceQuestionSet;
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

    public void setDbStrategy(DBStrategy dbStrategy) {
        this.dbStrategy = dbStrategy;
    }
}
