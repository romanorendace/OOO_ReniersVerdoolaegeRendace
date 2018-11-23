package db;

import model.Category;
import model.Question;

import java.io.Reader;
import java.io.Writer;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class QuestionManager {

    private Map<Category, Set<Question>> questions;
    private Reader reader;
    private Writer writer;

    public QuestionManager() {
        questions = new TreeMap<>();
    }
}
