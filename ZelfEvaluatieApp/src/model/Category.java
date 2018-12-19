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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMainCategoryTitle(String mainCategoryTitle) {
        this.mainCategoryTitle = mainCategoryTitle;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category that = (Category) o;
        return     this.title.equals(that.getTitle())
                && this.description.equals(that.getDescription())
                && this.mainCategoryTitle.equals(that.getMainCategoryTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDescription());
    }

    @Override
    public int compareTo(Category other) {
        return this.title.compareTo(other.getTitle());
    }

    @Override
    public String toString() {
        return title;
    }
}
