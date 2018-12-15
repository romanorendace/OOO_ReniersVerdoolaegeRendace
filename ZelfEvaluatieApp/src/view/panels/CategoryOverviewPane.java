package view.panels;

import controller.Controller;
import db.CategoryDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Category;
import model.Observable;
import model.Observer;


public class CategoryOverviewPane extends GridPane implements Observer {

	private Controller contoller;

	private TableView table;
	private Button btnNew;
	
	public CategoryOverviewPane() {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);
        
		this.add(new Label("Categories:"), 0, 0, 1, 1);
		
		table = new TableView<>();
		table.setPrefWidth(REMAINING);

        TableColumn nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        table.getColumns().add(nameCol);

        TableColumn descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        table.getColumns().add(descriptionCol);
		this.add(table, 0, 1, 2, 6);

		populateTableWithCategories();
		
		btnNew = new Button("New");
		setNewAction(new NewCategoryHandler());
		this.add(btnNew, 0, 11, 1, 1);
	}


	public void setContoller(Controller contoller) {
		this.contoller = contoller;
	}

	public void setNewAction(EventHandler<ActionEvent> newAction) {
		btnNew.setOnAction(newAction);
	}
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}

	@Override
	public void update(Observable o, Object args) {
		if (o instanceof CategoryDB) {
			ObservableList<Category> categories = ((CategoryDB) o).getObservableListOfCategories();
			table.setItems(categories);
		}
	}

	class NewCategoryHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			contoller.handleRequest("ShowCategoryDetailPane");
		}
	}

	private void populateTableWithCategories() {
		//TODO
	}
}
