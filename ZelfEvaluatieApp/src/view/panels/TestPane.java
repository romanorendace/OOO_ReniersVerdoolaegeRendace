package view.panels;

import java.util.ArrayList;
import java.util.List;
import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.*;


public class TestPane extends GridPane implements Observer {

	private Controller controller;

	private Label questionField;
	private Button submitButton;
	private ToggleGroup statementGroup;
	private VBox vbox = new VBox();
	
	public TestPane (){
		this.setPrefHeight(300);
		this.setPrefWidth(750);
		
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

		questionField = new Label();

		statementGroup = new ToggleGroup();

		submitButton = new Button("Submit");
		setAction(new VerifyQuestionHandler());
		submitButton.setText("Submit");
		add(submitButton, 0, 11, 1, 1);
	}

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {
		submitButton.setOnAction(processAnswerAction);
	}

	public void setQuestionField(String question){
		questionField=new Label(question);
		add(questionField, 0, 0, 1, 1);
	}

	public void setStatementGroup(List statements) {

		for (Object statement : statements) {
			ToggleButton toggleButton = new RadioButton(statement.toString());
			toggleButton.setToggleGroup(statementGroup);

			vbox.getChildren().add(toggleButton);
		}
		add(vbox,0,1,1,1);
	}

	public void setAction(EventHandler<ActionEvent> verifyQuestionAction) {
		submitButton.setOnAction(verifyQuestionAction);
	}

	private void verifyQuestion() {
		controller.handleRequest("verifyAnswer");
	}

	public List<String> getSelectedStatements() {
		 List<String> selected = new ArrayList<String>();
		if(statementGroup.getSelectedToggle()!=null){
			selected.add(statementGroup.getSelectedToggle().getUserData().toString());
		}
		return selected;
	}

	public String getSelectedAnswer(){return statementGroup.getSelectedToggle().toString();}

	class VerifyQuestionHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			controller.handleRequest("verifyAnswer");
		}
	}

    @Override
    public void update(Observable o, Object args) {

    }
}
