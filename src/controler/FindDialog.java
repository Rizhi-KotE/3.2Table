package controler;

import application.LocalizedStage;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Library;
import view.MainTable;

public class FindDialog {
	private Stage stage;

	/**
	 * @return the stage
	 */
	public Stage getDialog() {
		return stage;
	}

	private Library library;

	private TextField bookNameField = new TextField();
	private TextField athorNameField = new TextField();
	private TextField tomesNameField = new TextField();
	private TextField circulationNameField = new TextField();
	private Button addButton = new Button("find");

	public FindDialog(Library lib) {
		stage = new Stage();
		library = lib;
		Pane pane = new VBox();
		
		stage.setOnCloseRequest((e) -> {
			stage.close();
		});
		
		
		stage.setScene(new LocalizedStage(pane, 400, 800));

		TableView<?> table = new MainTable(lib).getTable();
		bookNameField.setPromptText("bookName");
		athorNameField.setPromptText("authorName");
		tomesNameField.setPromptText("tomeNumber");
		circulationNameField.setPromptText("circulation");
		Pane hPane = new HBox();
		hPane.getChildren().addAll(bookNameField, athorNameField, tomesNameField, circulationNameField,
				addButton);
		table.setMaxHeight(800);
		pane.getChildren().addAll(table,hPane);
	}
}
