package view.panels;

import controller.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Observable;
import model.Observer;
import model.ZelfEvaluatieService;


public class QuestionDetailPane extends GridPane implements ViewPane, Observer {

	private Controller controller;

	private Button btnSave, btnCancel;
	//private TextArea statementsArea;
	private ListView  statementsArea;
	private TextField questionField, statementField, feedbackField;
	private Button btnAdd, btnRemove;
	private ComboBox categoryField;

	public QuestionDetailPane() {
		this.setPrefHeight(300);
		this.setPrefWidth(320);
		
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		add(new Label("MultipleChoiceQuestion: "), 0, 0, 1, 1);
		questionField = new TextField();
		add(questionField, 1, 0, 2, 1);
		
		add(new Label("Statement: "), 0, 1, 1, 1);
		statementField = new TextField();
		add(statementField, 1, 1, 2, 1);

		add(new Label("Statements: "), 0, 2, 1, 1);
		//TextArea best vervangen door listview!
		statementsArea = new ListView();
		//statementsArea.setPrefRowCount(5);
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
		btnCancel.setText("Cancel");
		add(btnCancel, 0, 11, 1, 1);

		btnSave = new Button("Save");
		btnSave.isDefaultButton();
		btnSave.setText("Save");
		setSaveAction(new SaveQuestionHandler());
		add(btnSave, 1, 11, 2, 1);
		
	}

    public String getQuestionFieldString() {
        return questionField.getCharacters().toString();
    }

    public String getCategoryFieldString() {
        return categoryField.getValue().toString();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setSaveAction(EventHandler<ActionEvent> saveAction) {
		btnSave.setOnAction(saveAction);
	}

	public void setCancelAction(EventHandler<ActionEvent> cancelAction) {
		btnCancel.setOnAction(cancelAction);
	}

	public void updateStatementsInView(ObservableList statements) {
	    statementsArea.setItems(statements);
    }

    public String getStatementFieldString() {
        return statementField.getCharacters().toString();
    }

    public String getFeedbackField(){return feedbackField.getCharacters().toString();}

    public ObservableList getStatementsListField(){return statementsArea.getItems();}

    class AddStatementListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
		    controller.handleRequest("AddStatement");
		}
	}

	class RemoveStatementListener implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
		}
	}

	class SaveQuestionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("SaveQuestion");
        }
    }

    @Override
    public void update(Observable o, Object args) {

    }

}
