package view.panels;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import model.Observable;
import model.Observer;
import model.Question;
import model.ZelfEvaluatieService;

public class TestPane extends GridPane implements ViewPane, Observer {

	private Controller controller;

	private Label questionField;
	private Button submitButton;
	private ToggleGroup statementGroup;
	
	public TestPane (){
		this.setPrefHeight(300);
		this.setPrefWidth(750);
		
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

		/*add(new Label("Question: "), 0, 0, 1, 1);
		questionField = new TextField();
		add(questionField, 1, 0, 2, 1);
*/

		questionField = new Label("vraag");
		add(questionField, 0, 0, 1, 1);
		
		statementGroup = new ToggleGroup();

		submitButton = new Button("Submit");
	}

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setProcessAnswerAction(EventHandler<ActionEvent> processAnswerAction) {
		submitButton.setOnAction(processAnswerAction);
	}

	public List<String> getSelectedStatements() {
		 List<String> selected = new ArrayList<String>();
		if(statementGroup.getSelectedToggle()!=null){
			selected.add(statementGroup.getSelectedToggle().getUserData().toString());
		}
		return selected;
	}

    @Override
    public void update(Observable o, Object args) {

    }
}
