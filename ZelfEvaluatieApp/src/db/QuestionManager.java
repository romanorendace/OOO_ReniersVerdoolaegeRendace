package db;

import model.Category;
import model.Question;

import java.io.Reader;
import java.io.Writer;
import java.util.*;

public class QuestionManager {

    private Set<Question> questionSet;
    private Reader reader;
    private Writer writer;

    public QuestionManager() {
        questionSet = new LinkedHashSet<>();
    }
    public  void saveNewQuestion(Question q){
        questionSet.add(q);
        //notify observers
    }
}
