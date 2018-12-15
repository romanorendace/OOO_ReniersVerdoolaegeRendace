package view.panels;

import controller.Controller;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Observable;
import model.Observer;

public class CategoryDetailPane extends GridPane implements Observer {


	private Controller controller;

	private Button btnSave, btnCancel;
	private TextField titleField, descriptionField;
	private ComboBox categoryField;

	public CategoryDetailPane() {
		this.setPrefHeight(150);
		this.setPrefWidth(300);
		
		this.setPadding(new Insets(5, 5, 5, 5));
		this.setVgap(5);
		this.setHgap(5);

		this.add(new Label("Title:"), 0, 0, 1, 1);
		titleField = new TextField();
		this.add(titleField, 1, 0, 1, 1);

		this.add(new Label("Description:"), 0, 1, 1, 1);
		descriptionField = new TextField();
		this.add(descriptionField, 1, 1, 1, 1);

		this.add(new Label("Main Category:"), 0, 2, 1, 1);
		categoryField = new ComboBox<>();

		this.add(categoryField, 1, 2, 1, 1);

		btnCancel = new Button("Cancel");
		setCancelAction(new CancelCategoryHandler());
		this.add(btnCancel, 0, 3, 1, 1);

		btnSave = new Button("Save");
		btnSave.isDefaultButton();
		setSaveAction(new SaveCategoryHandler());
		this.add(btnSave, 1, 3, 1, 1);
	}

	public String getTitleFieldString() {
		return titleField.getCharacters().toString();
	}

	public String getDescriptionFieldString() {
		return descriptionField.getCharacters().toString();
	}

	public String getCategoryFieldString() {
		return categoryField.getValue().toString();
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void setSaveAction(EventHandler<ActionEvent> saveAction) {
		btnSave.setOnAction(saveAction);
	}

	public void setCancelAction(EventHandler<ActionEvent> cancelAction) {
		btnCancel.setOnAction(cancelAction);
	}


	private void populateCategoryField() {
		controller.handleRequest("PopulateCategoryField");
	}

	public void setCategoryField(ObservableList<String> categoryList) {
		categoryField.setItems(categoryList);
	}

	@Override
	public void update(Observable o, Object args) {
		populateCategoryField();
	}

	class SaveCategoryHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			controller.handleRequest("SaveCategory");
		}
	}

	class CancelCategoryHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			controller.handleRequest("CancelCategory");
		}
	}
}
