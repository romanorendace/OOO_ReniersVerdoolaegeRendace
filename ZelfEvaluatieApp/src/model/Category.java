package model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Category implements Comparable<Category>, Serializable {

    private String title;
    private String description;
    private Category mainCategory;

    public Category(String title, String description, Category mainCategory) {
        setTitle(title);
        setDescription(description);
        setMainCategory(mainCategory);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Category getMainCategory() {
        return mainCategory;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setMainCategory(Category mainCategory) {
        this.mainCategory = mainCategory;
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
