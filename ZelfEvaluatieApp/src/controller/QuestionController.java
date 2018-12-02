package controller;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;

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

    private List<String> statements;


    public QuestionController() {
        statements = new ArrayList<>();
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
            addStatementToStatementsList();
        }
        /*else if (action.equals(("RemoveStatement"))){
            String statement = questionDetailPane.get
            statements.remove()
        }*/
        else if (action.equals("SaveQuestion")) {
            saveQuestion();
        }
        else if (action.equals("CancelQuestion")) {
            cancelQuestion();
        }
    }

    private void saveQuestion() {
        String questionString = questionDetailPane.getQuestionFieldString();
        String categoryString = questionDetailPane.getCategoryFieldString();
        String feedbackString = questionDetailPane.getFeedbackField();
        Category category = service.getCategory(categoryString);
        List<String> statementsList = new ArrayList(statements);

        Question question = new MultipleChoiceQuestion(questionString, category, feedbackString, statementsList);
        service.saveNewQuestion(question);
        stage.close();
    }

    private void cancelQuestion() {
        resetStatementsList();
        resetStatementsListOverview();
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

    private void addStatementToStatementsList() {
        String statement = questionDetailPane.getStatementFieldString();
        if (isValidStatement(statement)) {
            statements.add(statement);
            updateStatementsListOverview();
        }
        clearStatementField();
    }

    private boolean isValidStatement(String statement) {
        return statement != null && !statement.trim().isEmpty();
    }

    private void updateStatementsListOverview() {
        ObservableList<String> statementsObservableList = FXCollections.observableList(statements);
        questionDetailPane.updateStatementsInView(statementsObservableList);
    }

    private void clearStatementField() {
        questionDetailPane.clearStatementField();
    }

    private void resetStatementsList() {
        statements = new ArrayList<>();
    }

    private void resetStatementsListOverview() {
        updateStatementsListOverview();
    }


    @Override
    public void update(Observable o, Object args) {

    }



}
