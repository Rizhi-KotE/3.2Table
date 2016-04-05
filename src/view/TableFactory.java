package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Book;
import model.Library;

public class TableFactory {
	public enum Type {
		Main, Find
	}

	public static TablePane getTable(Library lib, Type type) {
		Pane pane = null;
		MainTable table = null;
		switch (type) {
		case Main: {
			table = new MainTable(lib);
			pane = createMainMenu(table);
			table.setItems(lib.addBookFilter(table, Library.booksType.ALL));
		}
			break;
		case Find: {
			table = new MainTable(lib);
			pane = createMainMenu(table);
			table.setItems(lib.addBookFilter(table, Library.booksType.FINDED));
		}
			break;
		}
		return new TablePane(table, pane);
	}

	private static Pane createMainMenu(MainTable main) {
		VBox pane = new VBox();
		TableView<Book> table = main.getTable();
		table.setMaxHeight(280);
		table.setMaxWidth(500);
		pane.getChildren().add(table);
		Button prev = new Button("previos");
		prev.setOnAction(e -> {
			main.previos();
		});
		Button first = new Button("first");
		first.setOnAction(e -> {
			main.first();
		});
		Button next = new Button("next");
		next.setOnAction(e -> {
			main.next();
		});
		Button end = new Button("end");
		end.setOnAction(e -> {
			main.end();
		});
		Label label = new Label(Integer.toString(main.getLibSize().get()));
		label.setMinSize(10, 0);
		main.getLibSize().addListener((e, oldValue, newValue) -> {
			label.setText(Integer.toString(main.getLibSize().get()));
		});

		HBox buttons = new HBox();
		buttons.setMaxWidth(500);
		buttons.setCenterShape(true);
		buttons.getChildren().addAll(first, prev, label, next, end); 
		/*buttons.setStyle(
				".outer{border: 1px solid blue;}.inner{  border: 1px solid red;    margin: auto;  }");*/
		pane.getChildren().add(buttons);
		return pane;
	}

	public static class TablePane {
		private MainTable table;
		private Pane pane;

		/**
		 * @return the table
		 */
		public MainTable getTable() {
			return table;
		}

		/**
		 * @return the pane
		 */
		public Pane getPane() {
			return pane;
		}

		public TablePane(MainTable table, Pane pane) {
			super();
			this.table = table;
			this.pane = pane;
		}
	}
}
