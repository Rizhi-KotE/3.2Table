package controler;

import java.util.function.Predicate;

import com.sun.javafx.tk.Toolkit.PaintAccessor;

import application.LocalizedScene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import model.Library;
import view.MainTable;
import view.TableFactory;
import view.TableFactory.TablePane;

public class FindDialog {
	private Stage stage;

	/**
	 * @return the stage
	 */
	public void show(Type e) {
		Pane main = new VBox();
		switch (e) {
		case FIND: {
			TablePane tablePain = TableFactory.getTable(library, TableFactory.Type.Find);
			table = tablePain.getTable();
			Pane controls = findElements();
			main.getChildren().addAll(tablePain.getPane(), controls);
		}
			break;
		case DELETE: {
			TablePane tablePain = TableFactory.getTable(library, TableFactory.Type.Find);
			table = tablePain.getTable();
			Pane controls = removeElements();
			main.getChildren().addAll(tablePain.getPane(), controls);
		}
			break;
		}
		stage.setScene(new LocalizedScene(main));
		stage.show();
	}

	private Library library;

	private Pane functionalPane;
	private TextField bookNameField = new TextField();
	private TextField athorNameField = new TextField();
	private TextField tomesNumberField = new TextField();
	private TextField circulationField = new TextField();
	private TextField finalTomesNumberField = new TextField();

	Predicate<String> bookNamePred = (name) -> {
		return true;
	};
	Predicate<String> authorNamePred = (name) -> {
		return true;
	};
	Predicate<Integer> circulationPred = (name) -> {
		return true;
	};
	Predicate<Integer> tomesNumbPred = (name) -> {
		return true;
	};
	Predicate<Integer> finalTomesNumbPred = (name) -> {
		return true;
	};

	private ToggleGroup finalTomesNumberToogle;

	private ToggleGroup circulationToogle;

	private MainTable table;

	enum Type {
		FIND, DELETE
	};

	public FindDialog(Library lib) {
		stage = new Stage();
		library = lib;
		stage.setOnCloseRequest((e) -> {
			stage.close();
		});
	}

	private Pane findElements() {
		Pane box = new VBox();
		bookNameField.setPromptText("bookName");
		athorNameField.setPromptText("authorName");
		tomesNumberField.setPromptText("tomeNumber");
		circulationField.setPromptText("circulation");
		finalTomesNumberField.setPromptText("finalTomeNumber");
		Button findButton = new Button("find");
		findButton.setOnAction((e) -> {
			calcPredicate();
			table.setItems(library.find(table, calcResultPredicate()));
		});
		box.getChildren().addAll(bookNameField, athorNameField, circulationCheckBox(), tomesNumberField,
				finalNumberOfTomesCheckBox(), findButton);
		return box;
	}

	private Pane removeElements() {
		Pane box = new VBox();
		bookNameField.setPromptText("bookName");
		athorNameField.setPromptText("authorName");
		tomesNumberField.setPromptText("tomeNumber");
		circulationField.setPromptText("circulation");
		finalTomesNumberField.setPromptText("finalTomeNumber");
		Button findButton = new Button("find");
		findButton.setOnAction((e) -> {
			calcPredicate();
			table.setItems(library.find(table, calcResultPredicate()));
		});
		Button removeButton = new Button("remove");
		removeButton.setOnAction((e) -> {
			calcPredicate();
			table.setItems(library.find(table, calcResultPredicate()));
			library.remove(table, calcResultPredicate());
		});
		box.getChildren().addAll(bookNameField, athorNameField, circulationCheckBox(), tomesNumberField,
				finalNumberOfTomesCheckBox(), findButton, removeButton);
		return box;
	}

	private Predicate<Book> calcResultPredicate() {
		return (Book e) -> {
			return bookNamePred.test(e.getBookName()) && authorNamePred.test(e.getAuthorName())
					&& circulationPred.test(e.getCirculation()) && tomesNumbPred.test(e.getTomeNumber())
					&& finalTomesNumbPred.test(e.getFinalTomeNumber());
		};
	}

	private void calcPredicate() throws NumberFormatException {
		String bookName = bookNameField.getText();
		if (!"".equals(bookName)) {
			bookNamePred = (name) -> {
				return name.equals(bookName);
			};
		} else {
			bookNamePred = (e) -> {
				return true;
			};
		}
		String authorName = athorNameField.getText();
		if (!"".equals(authorName)) {
			authorNamePred = (name) -> {
				return name.equals(authorName);
			};
		} else {
			authorNamePred = (e) -> {
				return true;
			};
		}
		try {
			int circulation = Integer.parseInt(circulationField.getText());
			switch ((String) circulationToogle.getSelectedToggle().getUserData()) {
			case "less":
				circulationPred = (circ) -> {
					return circ <= circulation;
				};
				break;
			case "more":
				circulationPred = (circ) -> {
					return circ >= circulation;
				};
				break;
			}
		} catch (Exception e1) {
			circulationPred = (e) -> {
				return true;
			};
		}
		try {
			int finalTomesNumber = Integer.parseInt(finalTomesNumberField.getText());
			switch ((String) finalTomesNumberToogle.getSelectedToggle().getUserData()) {
			case "less":
				finalTomesNumbPred = (numb) -> {
					return numb <= finalTomesNumber;
				};
				break;
			case "more":
				finalTomesNumbPred = (numb) -> {
					return numb >= finalTomesNumber;
				};
				break;
			}
		} catch (Exception e) {
			finalTomesNumbPred = (numb) -> {
				return true;
			};
		}
	}

	private Pane circulationCheckBox() {
		Pane pane = new HBox();
		pane.getChildren().add(circulationField);
		circulationToogle = new ToggleGroup();

		RadioButton less = new RadioButton("less");
		less.setUserData("less");
		less.setSelected(true);
		less.setToggleGroup(circulationToogle);

		RadioButton more = new RadioButton("more");
		more.setUserData("more");
		more.setToggleGroup(circulationToogle);

		pane.getChildren().addAll(more, less);
		return pane;
	}

	private Pane finalNumberOfTomesCheckBox() {
		Pane pane = new HBox();
		pane.getChildren().add(finalTomesNumberField);
		finalTomesNumberToogle = new ToggleGroup();

		RadioButton less = new RadioButton("less");
		less.setUserData("less");
		less.setSelected(true);
		less.setToggleGroup(finalTomesNumberToogle);

		RadioButton more = new RadioButton("more");
		more.setUserData("more");
		more.setToggleGroup(finalTomesNumberToogle);

		pane.getChildren().addAll(more, less);
		return pane;
	}
}
