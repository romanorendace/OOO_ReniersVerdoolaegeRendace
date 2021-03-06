package view.panels;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Observable;
import model.Observer;

public class MessagePane extends GridPane implements Observer {

    private Controller controller;

    private Button testButton;

    public MessagePane() {
        setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        testButton = new Button("Evaluate");
        testButton.setOnAction(new EvaluateHandler());
        add(testButton, 0, 1, 1, 1);
        setHalignment(testButton, HPos.CENTER);
    }


    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void update(Observable o, Object args) {

    }

    class EvaluateHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("ShowTestPane");
        }
    }
}
