package view;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Book;
import model.Library;

public class TableFactory {
	public enum Type {
		Main, Find
	}

	public static Pane getTable(Library lib, Type type) {
		Pane pane = null;
		switch (type) {
		case Main: {
			pane = createMainMenu(lib);
		}
			break;
		case Find: {

		}
			break;
		}
		return pane;
	}

	private static Pane createMainMenu(Library lib) {
		Pane pane = new VBox();
		MainTable main = new MainTable(lib);
		TableView<Book> table = main.getTable();
		table.setMaxHeight(280);
		table.setMaxWidth(500);
		pane.getChildren().add(table);
		Button prev = new Button("previos");
		prev.setOnAction(e->{main.previos();});
		Button first = new Button("first");
		first.setOnAction(e->{main.first();});
		Button next = new Button("next");
		next.setOnAction(e->{main.next();});
		Button end = new Button("end");
		end.setOnAction(e->{main.end();});
		HBox buttons = new HBox();
		buttons.getChildren().addAll(first, prev, next, end);
		pane.getChildren().add(buttons);
		return pane;
	}

	private static Pane createMainMenu() {
		Library lib = new Library();
		return createMainMenu(lib);
	}
}
