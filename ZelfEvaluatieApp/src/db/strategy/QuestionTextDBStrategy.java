package db.strategy;

import db.QuestionDB;
import model.Category;
import model.MultipleChoiceQuestion;
import model.Question;
import model.QuestionFactory;
import java.io.*;
import java.util.*;


public class QuestionTextDBStrategy extends TextDBStrategy {

    private File file;
    private List<Question> data;

    @Override
    public void setupConnectionToStorage() {
        file = new File("testdatabase/vraag.txt");
    }

    @Override
    public void getDataFromStorage() {
        data = new ArrayList<>();
        try (Scanner reader = new Scanner(file)) {

            String line;
            while (reader.hasNextLine()) {
                line = reader.nextLine();

                String[] instances = line.split("/");
                Question question = createQuestionFromInstanceStrings(instances);

                data.add(question);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDataInLocalMemory() {
        if (data != null) {
            Set<Question> questionSet = new LinkedHashSet<>(data);
            QuestionDB questionDB = QuestionDB.getInstance();
            questionDB.setQuestionSet(questionSet);
        }
    }

    @Override
    public void setDataToStorage() {
        clearFile();
        Set<Question> questionSet = QuestionDB.getInstance().getQuestionSet();
        for (Question question : questionSet) {
            saveObjectToStorage(question);
        }
    }

    @Override
    public void saveObjectToStorage(Object object) {
        setupConnectionToStorage();
        if (object instanceof MultipleChoiceQuestion) {
            MultipleChoiceQuestion question = (MultipleChoiceQuestion) object;
            saveMultipleChoiceQuestionToStorage(question);
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


    private void saveMultipleChoiceQuestionToStorage(MultipleChoiceQuestion MCquestion) {
        try (FileOutputStream fos = new FileOutputStream(file, true);
             PrintWriter writer = new PrintWriter(fos)) {

            String className = "MultipleChoiceQuestion";
            String question = MCquestion.getQuestion();
            String categoryToString = getCategoryToString(MCquestion);
            String statementsToString = getStatementsToString(MCquestion);
            String feedback = MCquestion.getFeedback();

            writer.println(className + "/" + question + "/" + categoryToString + "/" + feedback
                    + "/" + statementsToString);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCategoryToString(MultipleChoiceQuestion MCquestion) {
        Category category = MCquestion.getCategory();
        String categoryTitle = category.getTitle();
        String categoryDescription = category.getDescription();
        String categoryMainCategoryTitle = category.getMainCategoryTitle();

        return categoryTitle + ";" + categoryDescription + ";" + categoryMainCategoryTitle;
    }

    private String getStatementsToString(MultipleChoiceQuestion MCquestion) {
        String statementsToString = "";
        for (String s : MCquestion.getStatements()) {
            statementsToString += s + ";";
        }
        return statementsToString.substring(0, statementsToString.length()-1);
    }

    private Question createQuestionFromInstanceStrings(String[] instanceStrings) {
        String questionType = instanceStrings[0];
        String question = instanceStrings[1];
        Category category = getCategoryFromString(instanceStrings[2]);
        String feedback = instanceStrings[3];

        QuestionFactory factory = new QuestionFactory();
        Question createdQuestion = factory.createQuestion(questionType, question, category, feedback);

        if (createdQuestion instanceof MultipleChoiceQuestion) {
            List<String> statements = getStatementsFromString(instanceStrings[4]);
            ((MultipleChoiceQuestion) createdQuestion).setStatements(statements);
        }

        return createdQuestion;
    }

    private Category getCategoryFromString(String instance) {
        String[] instances = instance.split(";");

        String title = instances[0];
        String description = instances[1];
        String mainCategoryTitle = instances[2];

        return new Category(title, description, mainCategoryTitle);
    }

    private List<String> getStatementsFromString(String instance) {
        String[] statements = instance.split(";");

        return new ArrayList<>(Arrays.asList(statements));
    }
}
