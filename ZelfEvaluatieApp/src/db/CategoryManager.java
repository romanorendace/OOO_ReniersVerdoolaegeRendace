package db;

import model.Category;

import java.io.Reader;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Set;

public class CategoryManager {

    private Set<Category> categorySet;
    private Reader reader;
    private Writer writer;

    public CategoryManager() {
        categorySet = new LinkedHashSet<>();
    }
}
