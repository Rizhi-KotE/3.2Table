package view;

import java.util.ArrayList;
import java.util.Iterator;
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
	private IntegerProperty currentGroup;
	private int groupSize = 10;
	
	public PagedTable() {
		libSize = new SimpleIntegerProperty();
		table = new TableView<>();
		table = initTable(table);
		currentGroup = new SimpleIntegerProperty(0);
		setBooks(new ArrayList<>());
		table.setMaxHeight(280);
	}

	public void setBooks(List<Book> lib) {
		books = FXCollections.observableList(lib);
		libSize.set(books.size());
		ListChangeListener<Book> listener = e->{libSize.set(books.size());};
		books.addListener(listener);
		setCurrentGroup();
	}

	private void setCurrentGroup() {
		List<Book> subList = new ArrayList<Book>(groupSize);
		Iterator<Book> allBooksIt = books.listIterator(currentGroup.get()*groupSize);
		while(allBooksIt.hasNext()&&subList.size()<groupSize){
			subList.add(allBooksIt.next());
		}
		setItems(subList);
	}

	public void end() {
		if (libSize.get() > groupSize) {
			currentGroup.set(libSize.get()/groupSize);
			setCurrentGroup();
		}
	}

	public void first() {
		if (currentGroup.get() != 0) {
			currentGroup.set(0);
			setCurrentGroup();
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
			currentGroup.set(currentGroup.get()+1);
			setCurrentGroup();
		}
	}

	public void previos() {
		if (currentGroup.get() > 0) {
			currentGroup.set(currentGroup.get()-1);
			setCurrentGroup();
		}
	}

	private void setItems(List<Book> find) {
		table.setItems(FXCollections.observableList(find));
	}
}
