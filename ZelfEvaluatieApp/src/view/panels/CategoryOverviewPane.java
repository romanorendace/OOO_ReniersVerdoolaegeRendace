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

	private Controller controller;

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
        setEditAction(new EditCategoryHandler());
		this.add(table, 0, 1, 2, 6);

		
		btnNew = new Button("New");
		setNewAction(new NewCategoryHandler());
		this.add(btnNew, 0, 11, 1, 1);
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void setNewAction(EventHandler<ActionEvent> newAction) {
		btnNew.setOnAction(newAction);
	}
	
	public void setEditAction(EventHandler<MouseEvent> editAction) {
		table.setOnMouseClicked(editAction);
	}


	class NewCategoryHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			controller.handleRequest("ShowCategoryDetailPane");
		}
	}

	class EditCategoryHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			if (event.getClickCount() == 2)
			controller.handleRequest("ShowEditCategoryDetailPane");
		}
	}

	@Override
	public void update(Observable o, Object args) {
		if (o instanceof CategoryDB) {
			ObservableList<Category> categories = ((CategoryDB) o).getObservableListOfCategories();
			table.setItems(categories);
		}
	}

	public Category getSelectedCategory() {
		Category selectedCategory = (Category) table.getSelectionModel().getSelectedItem();
		return selectedCategory;
	}
}
