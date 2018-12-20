package view.panels;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;


public class AssesMainPane extends BorderPane {

	public AssesMainPane(Pane messagePane, Pane categoryOverviewPanel, Pane questionOverviewPanel,Pane optionsPanel){
	    TabPane tabPane = new TabPane();
	    
	    FlowPane messageBox = new FlowPane(messagePane);
        	messageBox.setAlignment(Pos.CENTER);
        Tab testTab = new Tab("TestAttempt", messageBox);
        Tab categoriesTab = new Tab("Categories", categoryOverviewPanel);
        Tab questionsTab = new Tab("Questions", questionOverviewPanel);
        Tab optionsTab = new Tab("Options", optionsPanel);
        tabPane.getTabs().add(testTab);
        tabPane.getTabs().add(categoriesTab);
        tabPane.getTabs().add(questionsTab);
        tabPane.getTabs().add(optionsTab);

	    this.setCenter(tabPane);
	}
}
