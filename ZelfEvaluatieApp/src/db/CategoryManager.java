package db;

import model.Category;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.FileHandler;

public class CategoryManager {

    private Set<Category> categorySet;
    private Reader reader;
    private Writer writer;

    public CategoryManager() {
        categorySet = new LinkedHashSet<>();
    }

    public Category getCategory(String title) {
        for (Category c : this.categorySet) {
            if (c.getTitle().equals(title))
                return c;
        }
        return null;
    }

    public void saveNewCategory(Category c) {
        //notify observers
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
