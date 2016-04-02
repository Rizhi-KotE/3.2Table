package view;

import com.sun.javafx.tk.Toolkit;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.Book;
import model.Library;

public class MainTable {
	private Library library;
	private Pane pane;
	private TableView<Book> table;

	public MainTable(Library lib) {
		library = lib;
		libSize = lib.sizeProperty();
		table = new TableView<>();
		table = initTable(table);
		initNavigationButtons(table);
		table.setMaxHeight(280);
	}

	/**
	 * @return the pane
	 */
	public Pane getPane() {
		return pane;
	}

	/**
	 * @return the table
	 */
	public TableView<Book> getTable() {
		return table;
	}

	private void initNavigationButtons(TableView<Book> table) {

	}

	private TableView<Book> initTable(TableView<Book> table) {
		TableColumn<Book, String> bookNameCol = new TableColumn<>("bookName");
		bookNameCol.setMinWidth(100);
		bookNameCol.setCellValueFactory(new PropertyValueFactory<Book, String>("bookName"));

		TableColumn<Book, String> authorCol = new TableColumn<Book, String>("authorName");
		authorCol.setMinWidth(100);
		authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("authorName"));

		TableColumn<Book, String> circulationCol = new TableColumn<Book, String>("circulation");
		circulationCol.setMinWidth(100);
		circulationCol.setCellValueFactory(new PropertyValueFactory<Book, String>("circulation"));

		TableColumn<Book, String> tomesNumCol = new TableColumn<Book, String>("tomeNumber");
		tomesNumCol.setMinWidth(100);
		tomesNumCol.setCellValueFactory(new PropertyValueFactory<Book, String>("tomeNumber"));

		TableColumn<Book, String> finalTomesNumCol = new TableColumn<Book, String>("finalTomeNumber");
		finalTomesNumCol.setMinWidth(100);
		finalTomesNumCol.setCellValueFactory(new PropertyValueFactory<Book, String>("finalTomeNumber"));

		table.getColumns().addAll(bookNameCol, authorCol, circulationCol, tomesNumCol, finalTomesNumCol);
		table.getVisibleLeafColumns();
		table.setItems(library.getBooks());
		return table;
	}

	private IntegerProperty libSize;
	/**
	 * @return the libSize
	 */
	public IntegerProperty getLibSize() {
		return libSize;
	}

	private int groupSize = 10;
	private int currentGroup = 0;
	
	public void end() {
		// TODO Auto-generated method stub

	}

	public void next() {
		if(libSize.get()>currentGroup*groupSize+groupSize){
			table.setItems(library.getGroup(++currentGroup, groupSize));
		}
	}

	public void first() {
		// TODO Auto-generated method stub

	}

	public void previos() {
		// TODO Auto-generated method stub

	}
}
