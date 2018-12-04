package controller;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Observable;
import model.Observer;
import model.ZelfEvaluatieService;
import view.panels.MessagePane;
import view.panels.TestPane;
import view.panels.ViewPane;

public class TestController implements Controller, Observer {

    private ZelfEvaluatieService service;

    private TestPane testPane;
    private MessagePane messagePane;

    private Group root;
    private Scene scene;
    private BorderPane borderPane;
    private Stage stage;

    public TestController() {
    }

    public void setService(ZelfEvaluatieService service) {
        this.service = service;
    }

    public void setTestPane(TestPane testPane) {
        this.testPane = testPane;
    }

    public void setMessagePane(MessagePane messagePane) {
        this.messagePane = messagePane;
    }

    @Override
    public void handleRequest(String action) {
        if (action.equals("ShowTestPane")) {
            showTestPane();
        }

    }

    private void showTestPane() {
        stage = new Stage();
        root = new Group();
        borderPane = new BorderPane(testPane);
        scene = new Scene(root, 750, 400);

        root.getChildren().add(borderPane);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    @Override
    public void update(Observable o, Object args) {


    }
}
