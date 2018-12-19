package view.panels;

import controller.Controller;
import db.QuestionDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.*;

public class QuestionOverviewPane extends GridPane implements Observer {

    private Controller questionController;

    private TableView table;
    private Button btnNew;

    public QuestionOverviewPane() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("Questions:"), 0, 0, 1, 1);

        table = new TableView<>();
        table.setPrefWidth(REMAINING);

        TableColumn nameCol = new TableColumn<>("MultipleChoiceQuestion");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("question"));
        table.getColumns().add(nameCol);

        TableColumn descriptionCol = new TableColumn<>("Category");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        table.getColumns().add(descriptionCol);
        setEditAction(new EditQuestionHandler());
        this.add(table, 0, 1, 2, 6);

        btnNew = new Button("New");
        setNewAction(new NewQuestionHandler());
        this.add(btnNew, 0, 11, 1, 1);
    }


    public void setController(Controller controller) {
        this.questionController = controller;
    }

    public void setNewAction(EventHandler<ActionEvent> newAction) {
        btnNew.setOnAction(newAction);
    }

    public void setEditAction(EventHandler<MouseEvent> editAction) {
        table.setOnMouseClicked(editAction);
    }


    private void updateQuestionOverviewTable(Observable o) {
        QuestionDB questionDB = (QuestionDB) o;
        ObservableList<Question> questionObservableList = questionDB.getObservableListOfQuestions();
        table.setItems(questionObservableList);
    }

    public Question getSelectedQuestion() {
        return (Question) table.getSelectionModel().getSelectedItem();
    }

    @Override
    public void update(Observable o, Object args) {
        if (o instanceof QuestionDB) {
            updateQuestionOverviewTable(o);
        }
    }

    class NewQuestionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            questionController.handleRequest("ShowQuestionDetailPane");
        }
    }

    class EditQuestionHandler implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent event) {
            if (event.getClickCount() == 2) {
                questionController.handleRequest("ShowEditQuestionDetailPane");
            }
        }
    }

}
