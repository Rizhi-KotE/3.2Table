package view;

import java.lang.reflect.Field;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.Library;
import model.NamedFields;

public class MainTable<T> {
	private TableView<T> table;
	private Library library;
	/**
	 * @return the table
	 */
	public TableView<T> getTable() {
		return table;
	}

	public MainTable(Library lib) {
		library = lib;
		table = new TableView<>();
		table = initTable(table);
		table.setEditable(true);
	}

	private TableView<T extends NamedFields> initTable(TableView<T> table) {
		Field[] fields = 
		
		TableColumn<T, String> bookNameCol = new TableColumn<>("Book's name");
		bookNameCol.setMinWidth(100);
		bookNameCol.setCellValueFactory(new PropertyValueFactory<Book, String>("bookName"));
		
		
		TableColumn<T, String> authorCol = new TableColumn<Book, String>("author");
		authorCol.setMinWidth(100);
		authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("authorName"));

		TableColumn<T, String> circulationCol = new TableColumn<Book, String>("circulation");
		circulationCol.setMinWidth(100);
		circulationCol.setCellValueFactory(new PropertyValueFactory<Book, String>("circulation"));

		TableColumn<T, String> tomesNumCol = new TableColumn<Book, String>("amount of tomes");
		tomesNumCol.setMinWidth(100);
		tomesNumCol.setCellValueFactory(new PropertyValueFactory<Book, String>("tomeNumber"));

		TableColumn<T, String> finalTomesNumCol = new TableColumn<Book, String>("final amount of tomes");
		finalTomesNumCol.setMinWidth(100);
		finalTomesNumCol.setCellValueFactory(new PropertyValueFactory<Book, String>("finalTomeNumber"));
		
		table.getColumns().addAll(bookNameCol, authorCol, circulationCol, tomesNumCol, finalTomesNumCol);
		table.setItems(library.getBooks());
		return table;
	}
}
