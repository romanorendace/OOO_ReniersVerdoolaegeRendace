package controller;

import db.CategoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;
import view.panels.ViewPane;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class QuestionController implements Controller, Observer {

    private ZelfEvaluatieService service;

    private QuestionOverviewPane questionOverviewPane;
    private QuestionDetailPane questionDetailPane;

    private Group root;
    private Scene scene;
    private BorderPane borderPane;
    private Stage stage;

    private Set<String> statements;



    public QuestionController() {
        statements = new LinkedHashSet<>();

    }

    public void setService(ZelfEvaluatieService service) {
        this.service = service;
    }

    public void setQuestionOverviewPane(QuestionOverviewPane questionOverviewPane) {
        this.questionOverviewPane = questionOverviewPane;
    }

    public void setQuestionDetailPane(QuestionDetailPane questionDetailPane) {
        this.questionDetailPane = questionDetailPane;
    }

    @Override
    public void handleRequest(String action) {
        if (action.equals("ShowQuestionDetailPane")) {
            showQuestionDetailPane();
        }
        else if (action.equals("AddStatement")) {
            String statement = questionDetailPane.getStatementFieldString();
            ObservableList statements = questionDetailPane.getStatementsListField();
            if (statements.add(statement)) {
                updateStatementsListView(statements);
            }
        }
        /*else if (action.equals(("RemoveStatement"))){
            String statement = questionDetailPane.get
            statements.remove()
        }*/
        else if (action.equals("SaveQuestion")) {
            saveQuestion();
        }
        else if (action.equals("CancelQuestion")){ stage.close(); }
    }

    private void saveQuestion() {
        String questionString = questionDetailPane.getQuestionFieldString();
        String categoryString = questionDetailPane.getCategoryFieldString();
        String feedbackString = questionDetailPane.getFeedbackField();
        Category category = service.getCategory(categoryString);
        List<String> statementsList = new ArrayList(statements);
        Question question = new Question(questionString,category,statementsList,feedbackString);
        service.saveNewQuestion(question);
        stage.close();


    }


    private void showQuestionDetailPane() {
        root = new Group();
        stage = new Stage();
        scene = new Scene(root, 750, 400);
        borderPane = new BorderPane(questionDetailPane);

        root.getChildren().add(borderPane);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    private void updateStatementsListView(ObservableList statements) {
        questionDetailPane.updateStatementsInView(statements);
    }

    @Override
    public void update(Observable o, Object args) {

    }

}
