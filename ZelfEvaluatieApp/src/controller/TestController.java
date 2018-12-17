package controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;
import view.panels.MessagePane;
import view.panels.ResultPane;
import view.panels.TestPane;

import java.util.Map;
import java.util.Set;

public class TestController extends Controller implements Observer {

    private TestPane testPane;
    private MessagePane messagePane;
    private ResultPane resultPane;

    private Group root;
    private Scene scene;
    private BorderPane borderPane;
    private Stage stage;

    private TestAttempt test;
    private Question question;

    int result = 0;

    public void setTestPane(TestPane testPane) {
        this.testPane = testPane;
    }

    public void setMessagePane(MessagePane messagePane) {
        this.messagePane = messagePane;
    }

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
            question = test.getNextQuestion();
            if(question!=null){
                setTestPane(new TestPane());
                testPane.setController(this);
                showTestPane();
            }
        }
        if(action.equals("showResult")){
            resultPane = new ResultPane();
            resultPane.SetResultScore(result);
            showResultPane();
        }

    }

    private void generateTestAttempt() {
        this.test = getService().generateTestAttempt();
    }

    private void nextQuestion(){
        handleRequest("nextQuestion");
    }

    public void setNextQuestion(){
        question = test.getNextQuestion();
        if(question!=null) {
            testPane.setQuestionField(question.getQuestion());
        }
        else{
            calculateResult();
        }
    }

    private void setStatementsGroup(){
        if(question instanceof MultipleChoiceQuestion)
        testPane.setStatementGroup(((MultipleChoiceQuestion) question).getStatements());
    }

    private void verifyAnswer() {
        if(question instanceof MultipleChoiceQuestion){
            String correctAnswer = ((MultipleChoiceQuestion) question).getStatements().get(0);
            if(testPane.getSelectedAnswer().equals(correctAnswer)){
                test.setVerification(question,true);
            }
            else{
                test.setVerification(question,false);
            }
        }
    }

    private void calculateResult() {

        Map map = test.getQuestionsAndIsCorrectlyAnswered();
            for (Object value :map.values()){
                if((Boolean)value){
                    result++;
                }
            }
            handleRequest("showResult");
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

    @Override
    public void update(Observable o, Object args) {


    }
}
