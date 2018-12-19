package view.panels;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ResultPane extends GridPane {

    private Group root;
    private Scene scene;
    private BorderPane borderPane;
    private Stage stage;
    private Label resultField;

    public ResultPane() {
        root = new Group();
        stage = new Stage();
        scene = new Scene(root, 750, 400);
        borderPane = new BorderPane();

        resultField = new Label();

        root.getChildren().add(borderPane);
        stage.setScene(scene);
        stage.sizeToScene();
    }

    public Group getRoot() {
        return root;
    }
    public void setRoot(Group root) {
        this.root = root;
    }

    /*public Scene getScene() {
        return scene;
    }*/
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }
    public void setBorderPane(BorderPane borderPane) {

        this.borderPane = borderPane;
    }

    public void addGridPaneToBorderPane(GridPane pane) {
        setBorderPane(new BorderPane(pane));
        root.getChildren().add(borderPane);
    }


    public void SetResult(String result) {
        resultField=new Label(result);
        add(resultField, 0, 0, 1, 1);
    }
}
