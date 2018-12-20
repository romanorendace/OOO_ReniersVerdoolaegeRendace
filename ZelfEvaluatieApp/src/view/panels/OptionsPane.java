package view.panels;

import controller.Controller;
import controller.OptionsController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Observable;
import model.Observer;

public class OptionsPane extends GridPane implements Observer {

    private Controller controller;

    private Label optionField;
    private Button button;
    private ToggleGroup optionsGroup;
    private VBox vbox = new VBox();

    public OptionsPane() {
        this.setPrefHeight(300);
        this.setPrefWidth(750);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        optionField = new Label("How would you like to get your feedback?");

        optionsGroup = new ToggleGroup();

        ToggleButton scoreButton = new RadioButton("score");
        scoreButton.setToggleGroup(optionsGroup);

        ToggleButton feedbackButton = new RadioButton("feedback");
        feedbackButton.setToggleGroup(optionsGroup);

        vbox.getChildren().add(scoreButton);
        vbox.getChildren().add(feedbackButton);

        add(vbox,0,1,1,1);

        button = new Button("Save");
        setAction(new OptionsPane.ConfirmOptionsHandler());
        button.setText("Save");
        add(button, 0, 11, 1, 1);
    }

    public void setAction(EventHandler<ActionEvent> confirmOptionAction) {
        button.setOnAction(confirmOptionAction);
    }

    private void displayAlert() {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Preference saved!");
        infoAlert.setHeaderText("You have set your feedback preference to " + getSelectedAnswer() + ".");
        infoAlert.setContentText("Saved succesfully!");
        infoAlert.showAndWait();
    }

    public String getSelectedAnswer(){
        return((RadioButton)optionsGroup.getSelectedToggle()).getText();
    }

    public void setController(OptionsController optionsController) {
        this.controller=optionsController;
    }

    class ConfirmOptionsHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("confirmOptions");
            displayAlert();
        }
    }

    @Override
    public void update(Observable o, Object args) {


    }
}
