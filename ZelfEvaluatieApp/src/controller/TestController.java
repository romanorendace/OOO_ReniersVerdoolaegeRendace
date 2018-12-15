package controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;
import view.panels.MessagePane;
import view.panels.TestPane;

import java.util.Set;

public class TestController extends Controller implements Observer {

    private TestPane testPane;
    private MessagePane messagePane;

    private Group root;
    private Scene scene;
    private BorderPane borderPane;
    private Stage stage;

    private TestAttempt test;
    private Question question;
   // private Set<Question> questions;


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
        if(action.equals(("nextQuestion"))){
            question = test.getNextQuestion();
            showTestPane();
        }

    }

    private void generateTestAttempt() {
        this.test = getService().generateTestAttempt();
    }

    private Question nextQuestion(){
        question = test.getNextQuestion();
        return question;
    }
    public void setNextQuestion(){
        testPane.setQuestionField(nextQuestion().getQuestion());
    }

    private void setStatementsGroup(){
        if(question instanceof MultipleChoiceQuestion)
        testPane.setStatementGroup(((MultipleChoiceQuestion) question).getStatements());
    }

    private void showTestPane() {
        setNextQuestion();
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

    @Override
    public void update(Observable o, Object args) {


    }
}
