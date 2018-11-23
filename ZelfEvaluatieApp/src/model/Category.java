package model;

public class Category {
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
}
