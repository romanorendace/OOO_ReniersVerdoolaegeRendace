package db;

import model.Category;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

// SINGLETON
public class CategoryDB {

    private static CategoryDB uniqueInstance;
    private Set<Category> categorySet;
    private DBStrategy dbStrategy;

    private CategoryDB() {
        categorySet = new LinkedHashSet<>();
        dbStrategy = new CategoryTextDBStrategy();
    }

    public static synchronized CategoryDB getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CategoryDB();
        }
        return uniqueInstance;
    }

    public void setDbStrategy(DBStrategy dbStrategy) {
        this.dbStrategy = dbStrategy;
    }

    public Set<Category> getCategorySet() {
        return categorySet;
    }

    public Category getCategory(String title) {
        for (Category c : categorySet) {
            if (c.getTitle().equals(title))
                return c;
        }
        return null;
    }

    public void saveNewCategory(Category category) {
        categorySet.add(category);
        saveCategorySetToFile();
        //TODO
    }

    private void saveCategorySetToFile() {

        try (FileOutputStream fos = new FileOutputStream("testdatabase/groep.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(categorySet);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void loadCategorySetFromFile() {

        try (FileInputStream fis = new FileInputStream("testdatabase/groep.txt");
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            categorySet = (LinkedHashSet) ois.readObject();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void writeFile() throws Exception {
        ObjectOutputStream oos = null;
        FileOutputStream fs = null;
        try{
            fs = new FileOutputStream("groep.txt");
            oos = new ObjectOutputStream(fs);
            oos.writeObject(categorySet);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(oos != null){
                oos.close();
            }
        }
    }

    public void readFile() throws Exception {
        ObjectInputStream objectinputstream = null;
        try {
            FileInputStream streamIn = new FileInputStream("groep.txt");
            objectinputstream = new ObjectInputStream(streamIn);
            Category c = null;
            c = (Category) objectinputstream.readObject();
                if(c != null){
                    categorySet.add(c);
                }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(objectinputstream != null){
                objectinputstream.close();
            }
        }
    }









}
