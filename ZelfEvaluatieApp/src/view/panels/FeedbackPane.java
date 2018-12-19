package view.panels;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Observable;
import model.Observer;

public class FeedbackPane extends GridPane implements Observer {

    private Controller controller;

    private Label feedbackField;

    public FeedbackPane() {
        setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        feedbackField = new Label();

    }


    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setFeedbackField(String feedback){
        feedbackField=new Label(feedback);
        add(feedbackField, 0, 0, 1, 1);
    }

    @Override
    public void update(Observable o, Object args) {

    }

}
