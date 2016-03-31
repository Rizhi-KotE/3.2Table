package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import model.Book;
import model.Library;

public class AddForm {
	private Pane pane;
	private Library library;
	private TextField bookNameField = new TextField();
	private TextField athorNameField = new TextField();
	private TextField tomesNameField = new TextField();
	private TextField circulationNameField = new TextField();
	private Button addButton = new Button("add");
	
	public AddForm(Library lib) {
		library = lib;
		pane = new HBox();
		bookNameField.setPromptText("book's name");

		athorNameField.setPromptText("author's name");
		tomesNameField.setPromptText("tomes number");
		circulationNameField.setPromptText("book's circulation");
		addButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Book book = new Book(bookNameField.getText(),athorNameField.getText(),Integer.parseInt(tomesNameField.getText()),Integer.parseInt(circulationNameField.getText()));
				library.addBook(book);
			}
		});
				pane.getChildren().addAll(bookNameField, athorNameField, tomesNameField,circulationNameField,addButton);
	}
	
	public Pane getPane(){
		return pane;
	}
}
