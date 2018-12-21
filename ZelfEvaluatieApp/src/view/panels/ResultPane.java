package view.panels;

import controller.Controller;
import controller.TestController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Observable;
import model.Observer;

public class ResultPane extends GridPane {
    Controller controller;

    private Group root;
    private Scene scene;
    private BorderPane borderPane;
    private Stage stage;
    private Label resultField;
    private Button retryButton,closeButton;

    public ResultPane() {
        root = new Group();
        stage = new Stage();
        scene = new Scene(root, 750, 400);
        borderPane = new BorderPane();

        resultField = new Label();

        root.getChildren().add(borderPane);
        stage.setScene(scene);
        stage.sizeToScene();

        retryButton = new Button("Retry");
        setAction(new ResultPane.RetryTestHandler());
        retryButton.setText("Retry");
        add(retryButton, 0, 11, 1, 1);

        closeButton = new Button("Close");
        setCloseAction(new ResultPane.ClosePaneHandler());
        closeButton.setText("Close");
        add(closeButton, 1, 11, 1, 1);
    }

    public void setAction(EventHandler<ActionEvent> retryAction) {
        retryButton.setOnAction(retryAction);
    }
    public void setCloseAction(EventHandler<ActionEvent> retryAction) {
        closeButton.setOnAction(retryAction);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage getStage() {
        return stage;
    }

    public void SetResult(String result) {
        resultField=new Label(result);
        add(resultField, 0, 0, 1, 1);
    }

    public void setController(TestController testController) {
        this.controller = testController;
    }

    class RetryTestHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("ClosePane");
            controller.handleRequest("ShowTestPane");
        }
    }

    class ClosePaneHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("ClosePane");
        }
    }
}
