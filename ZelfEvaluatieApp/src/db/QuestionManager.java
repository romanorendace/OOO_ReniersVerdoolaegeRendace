package db;

import model.Category;
import model.Question;

import java.io.Reader;
import java.io.Writer;
import java.util.*;

public class QuestionManager {

    private Map<Category, Question> questionMap;
    private Reader reader;
    private Writer writer;

    public QuestionManager() {
        questionMap = new LinkedHashMap<>();
    }
}
