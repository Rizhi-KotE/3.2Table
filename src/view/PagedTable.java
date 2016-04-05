package view;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Book;

 public class PagedTable {
	private ObservableList<Book> books;
	
	private IntegerProperty libSize;
	private TableView<Book> table;

	PagedTable(List<Book> lib) {
		setBooks(lib);
		libSize = new SimpleIntegerProperty();
		table = new TableView<>();
		table = initTable(table);
		table.setMaxHeight(280);
		currentGroup = new SimpleIntegerProperty();
	}

	private void setBooks(List<Book> lib) {
		books = FXCollections.observableList(lib);
		libSize.set(books.size());
		ListChangeListener<Book> listener = e->{libSize.set(books.size());};
		
	}

	public void end() {
		if (libSize.get() > groupSize) {
			currentGroup.set((int) libSize.get() / groupSize);
			table.setItems(library.getGroup(this, currentGroup.get(), groupSize));
		}
	}

	public void first() {
		if (currentGroup.get() != 0) {
			currentGroup.set(0);
			table.setItems(library.getGroup(this, currentGroup.get(), groupSize));
		}
	}

	/**
	 * @return the libSize
	 */
	public IntegerProperty getLibSize() {
		return libSize;
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
		return table;
	}

	public void next() {
		if (libSize.get() > currentGroup.get() * groupSize + groupSize) {
			currentGroup.set(currentGroup.get() + 1);
			table.setItems(library.getGroup(this,currentGroup.get(), groupSize));
		}
	}

	public void previos() {
		if (currentGroup.get() > 0) {
			currentGroup.set(currentGroup.get() - 1);
			table.setItems(library.getGroup(this,currentGroup.get(), groupSize));
		}
	}

	public void setItems(ObservableList<Book> find) {
		table.setItems(find);
		libSize.set(find.size());
		ListChangeListener<Book> listener = (e)->{libSize.set(library.getAllBooks(this).size());};
		library.getAllBooks(this).addListener(listener);
	}
	
}
