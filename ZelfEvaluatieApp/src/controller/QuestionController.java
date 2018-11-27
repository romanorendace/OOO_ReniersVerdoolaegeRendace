package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Observable;
import model.Observer;
import model.ZelfEvaluatieService;
import view.panels.QuestionDetailPane;
import view.panels.QuestionOverviewPane;
import view.panels.ViewPane;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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
            if (statements.add(statement)) {
                updateStatementsListView(statement);
            }
        }
        else if (action.equals("SaveQuestion")) {
            String question = questionDetailPane.getQuestionFieldString();
            String categoryString = questionDetailPane.
        }
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

    private void updateStatementsListView(String statement) {
        questionDetailPane.updateStatementsInView(statement);
    }

    @Override
    public void update(Observable o, Object args) {

    }

}
