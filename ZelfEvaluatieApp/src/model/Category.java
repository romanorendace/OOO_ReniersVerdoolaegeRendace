package model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Category implements Comparable<Category>, Serializable {

    private String title;
    private String description;
    private String mainCategoryTitle;

    public Category(String title, String description, String mainCategoryTitle) {
        setTitle(title);
        setDescription(description);
        setMainCategoryTitle(mainCategoryTitle);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getMainCategoryTitle() {
        return mainCategoryTitle;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setMainCategoryTitle(String mainCategoryTitle) {
        this.mainCategoryTitle = mainCategoryTitle;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(getTitle(), category.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription());
    }

    @Override
    public int compareTo(Category other) {
        return this.title.compareTo(other.getTitle());
    }
}
