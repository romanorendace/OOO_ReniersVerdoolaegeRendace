package controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.panels.OptionsPane;



public class OptionsController extends  Controller{

    private  OptionsPane optionsPane;

    private Group root;
    private Scene scene;
    private BorderPane borderPane;
    private Stage stage;

    private String method = "score";

    public OptionsController(){
        setOptionsPane();
    }

    @Override
    protected Controller getController() {
        return this;
    }

    @Override
    public void handleRequest(String action) {
        if(action.equals("confirmOptions")){
            method = optionsPane.getSelectedAnswer();
            getService().getOptionsDB().setMethod(method);
            stage.close();
        }
        else{stage.close();}
    }

    private void setOptionsPane(){
            stage = new Stage();
            root = new Group();
            scene = new Scene(root, 750, 400);
            borderPane = new BorderPane(optionsPane);


            root.getChildren().add(borderPane);
            stage.setScene(scene);
            stage.sizeToScene();
        }

    public void setOptionsPane(OptionsPane optionsPane){this.optionsPane = optionsPane;}
}
