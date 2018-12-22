package controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;
import view.panels.FeedbackPane;
import view.panels.ResultPane;
import view.panels.TestPane;

import java.util.HashMap;
import java.util.Map;

public class TestController extends Controller  {

    private TestPane testPane;
    private FeedbackPane feedbackPane;
    private ResultPane resultPane;

    private Group root;
    private Scene scene;
    private BorderPane borderPane;
    private Stage stage;

    private TestAttempt test;
    private Question question;

    private String result = "";
    private String feedbackString = "";

    @Override
    protected Controller getController() {
        return this;
    }

    @Override
    public void handleRequest(String action) {


        if (action.equals("ShowTestPane")) {
            generateTestAttempt();
            showTestPane();
        }
        if (action.equals("verifyAnswer")){
            verifyAnswer();
            stage.close();
            nextQuestion();
        }
        if(action.equals("nextQuestion")) {
            if(question!=null){
                setTestPane(new TestPane());
                testPane.setController(this);
                showTestPane();
            }
        }
        if(action.equals("showResult")){
            resultPane = new ResultPane();
            resultPane.SetResult(result);
            resultPane.setController(this);
            showResultPane();
        }
        if(action.equals("showFeedback")){
            feedbackPane = new FeedbackPane();
            feedbackPane.setFeedbackField(feedbackString);
            feedbackPane.setController(this);
            showFeedbackPane();
        }
        if(action.equals("ClosePane")){
            stage.close();
        }

    }

    public void setTestPane(TestPane testPane) {
        this.testPane = testPane;
    }

    public void setFeedbackPane(FeedbackPane feedbackPane) {
        this.feedbackPane=feedbackPane;
    }

    public void setResultPane(ResultPane resultPane) {
        this.resultPane=resultPane;
    }

    public void setNextQuestion() {
        question = test.getNextQuestion();
        if (question != null) {
            testPane.setQuestionField(question.getQuestion());
        } else {
            if (getService().getFeedbackMethod().equals("feedback")) {
                generateFeedback();
            } else if (getService().getFeedbackMethod().equals("score")) {
                calculateResult();
            } else {
                System.out.println(getService().getFeedbackMethod());

            }
        }
    }

    private void generateTestAttempt() {
        this.test = getService().generateTestAttempt();
    }

    private void nextQuestion(){
        handleRequest("nextQuestion");
    }

    private void setStatementsGroup(){
        if(question instanceof MultipleChoiceQuestion)
        testPane.setStatementGroup(((MultipleChoiceQuestion) question).getStatements());
    }

    private void verifyAnswer() {
        if(question instanceof MultipleChoiceQuestion){
            String correctAnswer = ((MultipleChoiceQuestion) question).getStatements().get(0);
            String choice = testPane.getSelectedAnswer();
            if(choice.equals(correctAnswer)){
                test.setVerification(question,true);
            }
            else{
                test.setVerification(question,false);
            }
        }
    }

    private void calculateResult() {
        Map map = test.getQuestionsAndIsCorrectlyAnswered();
        Map<Category, Integer> categories = new HashMap<>();
        int totalResult = 0;
        Category category = null;
        String categoryResult = "";
        for (Object key :map.keySet()){
            category = ((Question)key).getCategory();
            if((Boolean)map.get(key)){
                totalResult++;
                if(categories.containsKey(category)){
                    int score = 1+categories.get(category);
                    categories.replace(category,score);
                }
                else{
                    categories.put(category,1);
                }
            }
            if(!categories.containsKey(category)&&category!=null){
                    categories.put(category,0);
                }
            }
        for (Object key : categories.keySet()) {
            categoryResult += "Category: " + key + " " + categories.get(key) + "/" + countCategory((Category)key) + "\n";
        }
        if (totalResult == map.size()){
            result = "Schitterend! U heeft alle " + totalResult + " vragen juist beantwoord!";
        }
        else{
        result = "uw totale score is " + totalResult + "/" + map.size() + "\n\n" + categoryResult;
        }
        handleRequest("showResult");
    }

    private void generateFeedback(){
        Map map = test.getQuestionsAndIsCorrectlyAnswered();
        for (Object key :map.keySet()){
            if(!(Boolean)map.get(key)){
                feedbackString +="Question:\n" + ((Question)key).getQuestion() + "\nFeedback:\n" + ((Question)key).getFeedback() + "\n\n" ;
            }
        }

        handleRequest("showFeedback");
    }

    private void showTestPane() {
        setNextQuestion();
        if (question != null) {
            setStatementsGroup();
            stage = new Stage();
            root = new Group();
            borderPane = new BorderPane(testPane);
            scene = new Scene(root, 750, 400);

            root.getChildren().add(borderPane);
            stage.setScene(scene);
            stage.sizeToScene();
            stage.show();
        }
    }

    private void showResultPane() {
        stage = new Stage();
        root = new Group();
        borderPane = new BorderPane(resultPane);
        scene = new Scene(root, 750, 400);

        root.getChildren().add(borderPane);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    private void showFeedbackPane() {
        root = new Group();
        stage = new Stage();
        borderPane = new BorderPane(feedbackPane);
        scene = new Scene(root, 750, 400);

        root.getChildren().add(borderPane);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    private int countCategory(Category category) {
        int total = 0;
        for (Object key:test.getQuestionsAndIsCorrectlyAnswered().keySet()) {
            if(((Question)key).getCategory().equals(category)){
                total++;
            }
        }
        return total;
    }


}
