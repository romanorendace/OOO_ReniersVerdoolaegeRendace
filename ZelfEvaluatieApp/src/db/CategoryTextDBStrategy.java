package db;


import model.Category;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.Set;

public class CategoryTextDBStrategy extends TextDBStrategy {

    private File file;
    private Collection<Object> data;

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
                String[] fields = new String[3];
                fields = line.trim().split("/");
                String title = fields[0];
                String description = fields[1];
                String mainCategoryTitle = fields[2];
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
        setCategorySet(data);
    }

    @Override
    public void setDataToStorage() {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
            for (Category category : getCategorySet()) {

                String title = category.getTitle();
                String description = category.getDescription();
                String mainCategoryTitle = category.getMainCategoryTitle();

                writer.println(title + "/" + description + "/" + mainCategoryTitle);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveObjectToStorage(Object object) {
        Category category = (Category) object;
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {

            String title = category.getTitle();
            String description = category.getDescription();
            String mainCategoryTitle = category.getMainCategoryTitle();

            writer.println(title + "/" + description + "/" + mainCategoryTitle);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
