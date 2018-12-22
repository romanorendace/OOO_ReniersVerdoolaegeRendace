package view.panels;

import controller.Controller;
import db.CategoryDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.*;

import java.util.List;

/**
 * @author Romano Rendace
 */
public class QuestionDetailPane extends GridPane implements Observer {

    private Controller controller;

    private Button btnSave, btnCancel;
    private ListView statementsArea;
    private TextField questionField, statementField, feedbackField;
    private Button btnAdd, btnRemove;
    private ComboBox categoryField;

    public QuestionDetailPane() {
        this.setPrefHeight(300);
        this.setPrefWidth(320);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        add(new Label("Question: "), 0, 0, 1, 1);
        questionField = new TextField();
        add(questionField, 1, 0, 2, 1);

        add(new Label("Statement: "), 0, 1, 1, 1);
        statementField = new TextField();
        add(statementField, 1, 1, 2, 1);

        add(new Label("Statements: "), 0, 2, 1, 1);
        statementsArea = new ListView();
        statementsArea.setEditable(false);
        add(statementsArea, 1, 2, 2, 5);

        Pane addRemove = new HBox();
        btnAdd = new Button("add");
        btnAdd.setOnAction(new AddStatementListener());
        addRemove.getChildren().add(btnAdd);

        btnRemove = new Button("remove");
        btnRemove.setOnAction(new RemoveStatementListener());
        addRemove.getChildren().add(btnRemove);
        add(addRemove, 1, 8, 2, 1);

        add(new Label("Category: "), 0, 9, 1, 1);
        categoryField = new ComboBox();
        add(categoryField, 1, 9, 2, 1);

        add(new Label("Feedback: "), 0, 10, 1, 1);
        feedbackField = new TextField();
        add(feedbackField, 1, 10, 2, 1);

        btnCancel = new Button("Cancel");
        setCancelAction(new CancelQuestionHandler());
        btnCancel.setText("Cancel");
        add(btnCancel, 0, 11, 1, 1);

        btnSave = new Button("Save");
        setSaveAction(new SaveQuestionHandler());
        btnSave.isDefaultButton();
        btnSave.setText("Save");
        setSaveAction(new SaveQuestionHandler());
        add(btnSave, 1, 11, 2, 1);

    }

    // GETTERS & SETTERS
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public String getQuestionFieldString() {
        return questionField.getCharacters().toString();
    }

    public String getCategoryFieldString() {
        return categoryField.getValue().toString();
    }

    public void setSaveAction(EventHandler<ActionEvent> saveAction) {
        btnSave.setOnAction(saveAction);
    }

    public void setCancelAction(EventHandler<ActionEvent> cancelAction) {
        btnCancel.setOnAction(cancelAction);
    }

    public void setQuestionField(String question) {
        this.questionField.setText(question);
    }

    public void setFeedbackField(String feedback) {
        this.feedbackField.setText(feedback);
    }

    public void setCategoryField(String category) {
        categoryField.getSelectionModel().select(category);
    }

    // AUX METHODS
    public void updateStatementsInView(ObservableList statements) {
        statementsArea.setItems(statements);
    }

    public String getStatementFieldString() {
        return statementField.getCharacters().toString();
    }

    public String getSelectedStatement() {
        Object statement = statementsArea.getSelectionModel().getSelectedItem();
        return String.valueOf(statement);
    }

    public List<String> getStatements() {
        return statementsArea.getItems();
    }

    public void clearInputFields() {
        statementField.setText("");
        feedbackField.setText("");
        questionField.setText("");
        categoryField.getSelectionModel().selectFirst();
    }

    public void clearStatementField() {
        statementField.setText("");
    }

    public String getFeedbackField() {
        return feedbackField.getCharacters().toString();
    }

    private void updateCategoryListInCategoryField(Observable o) {
        CategoryDB categoryDB = (CategoryDB) o;
        ObservableList<String> categoryTitlesObservableList = categoryDB.getObservableListOfCategoryTitles();
        categoryField.setItems(categoryTitlesObservableList);
    }

    public void setSaveActionForEdit() {
        setSaveAction(new EditQuestionHandler());
    }

    public void setSaveActionToSave() {
        setSaveAction(new SaveQuestionHandler());
    }


    class AddStatementListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            controller.handleRequest("AddStatement");
        }
    }

    class RemoveStatementListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            controller.handleRequest("RemoveStatement");
        }
    }

    class SaveQuestionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("SaveQuestion");
        }
    }

    class CancelQuestionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            clearInputFields();
            setSaveActionToSave();
            controller.handleRequest("CancelQuestion");
        }
    }

    class EditQuestionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            setSaveAction(new SaveQuestionHandler());
            controller.handleRequest("EditQuestion");
        }
    }


    @Override
    public void update(Observable o, Object args) {
        if (o instanceof CategoryDB) {
            updateCategoryListInCategoryField(o);
        }
    }


}
