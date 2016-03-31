package view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.Library;

public class MainTable {
	private TableView table;
	private Library library;
	/**
	 * @return the table
	 */
	public TableView getTable() {
		return table;
	}

	public MainTable(Library lib) {
		library = lib;
		table = new TableView<>();
		table = initTable(table);
		table.setItems(library.getGroupOfBooks(0, 5));
		table.setTableMenuButtonVisible(true);
		table.setEditable(true);
	}

	private TableView<Book> initTable(TableView<Book> table) {
		TableColumn<Book, String> bookNameCol = new TableColumn<>("Book's name");
		bookNameCol.setMinWidth(100);
		bookNameCol.setCellValueFactory(new PropertyValueFactory<Book, String>("bookName"));
		
		
		TableColumn<Book, String> authorCol = new TableColumn<Book, String>("author");
		authorCol.setMinWidth(100);
		authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("authorName"));

		TableColumn<Book, String> circulationCol = new TableColumn<Book, String>("circulation");
		circulationCol.setMinWidth(100);
		circulationCol.setCellValueFactory(new PropertyValueFactory<Book, String>("circulation"));

		TableColumn<Book, String> tomesNumCol = new TableColumn<Book, String>("amount of tomes");
		tomesNumCol.setMinWidth(100);
		tomesNumCol.setCellValueFactory(new PropertyValueFactory<Book, String>("tomeNumber"));

		TableColumn<Book, String> finalTomesNumCol = new TableColumn<Book, String>("final amount of tomes");
		finalTomesNumCol.setMinWidth(100);
		finalTomesNumCol.setCellValueFactory(new PropertyValueFactory<Book, String>("finalTomeNumber"));
		
		table.getColumns().addAll(bookNameCol, authorCol, circulationCol, tomesNumCol, finalTomesNumCol);
		return table;
	}
}
