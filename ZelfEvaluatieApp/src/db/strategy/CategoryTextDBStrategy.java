package db.strategy;


import db.CategoryDB;
import model.Category;
import java.io.*;
import java.util.*;

/**
 * @author Romano Rendace
 */
public class CategoryTextDBStrategy extends TextDBStrategy {

    private File file;
    private List<Category> data;

    @Override
    public void setupConnectionToStorage() {
        file = new File("testdatabase/groep.txt");
    }

    @Override
    public void getDataFromStorage() {
        data = new ArrayList<>();
        try (Scanner reader = new Scanner(file)) {

            String line;
            while (reader.hasNextLine()) {
                line = reader.nextLine();

                String[] instances = line.trim().split("/");
                String title = instances[0];
                String description = instances[1];
                String mainCategoryTitle = instances[2];

                Category category = new Category(title, description, mainCategoryTitle);
                data.add(category);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDataInLocalMemory() {
        if (data != null) {
            Set<Category> categorySet = new LinkedHashSet<>(data);
            CategoryDB categoryDB = CategoryDB.getInstance();
            categoryDB.setCategorySet(categorySet);
        }
    }

    @Override
    public void setDataToStorage() {
        clearFile();
        Set<Category> categorySet = CategoryDB.getInstance().getCategorySet();
        for (Category category : categorySet) {
            saveObjectToStorage(category);
        }
    }

    @Override
    public void saveObjectToStorage(Object object) {
        try (FileOutputStream fos = new FileOutputStream(file, true);
             PrintWriter writer = new PrintWriter(fos)) {

            Category category = (Category) object;
            String title = category.getTitle();
            String description = category.getDescription();
            String mainCategoryTitle = category.getMainCategoryTitle();

            writer.println(title + "/" + description + "/" + mainCategoryTitle);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFile() {
        try {
            FileOutputStream fos = new FileOutputStream(file, false);
            fos.write("".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
