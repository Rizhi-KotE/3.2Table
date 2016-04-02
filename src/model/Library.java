package model;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Library {
	private List<Book> books;
	private ObservableList<Book> group;
	private IntegerProperty size = new SimpleIntegerProperty();

	/**
	 * @return the books
	 */
	public ObservableList<Book> getBooks() {
		return group;
	}

	public Library() {
		List<Book> list = new LinkedList<Book>();
		books = new LinkedList<>(list);
		group = FXCollections.observableList(list);
	}

	public void addBook(Book book) {
		books.add(book);
		size.set(books.size());
		if (group.size() < 10) {
			group.add(book);
		}
	}

	public IntegerProperty sizeProperty() {
		return size;
	}

	public ObservableList<Book> getGroup(int groupNumber, int groupSize) {
		ListIterator<Book> it = books.listIterator(groupNumber * groupSize + 1);
		List<Book> subList = new LinkedList<>();
		while (it.hasNext() && subList.size() < 10) {
			subList.add(it.next());
		}
		return group = FXCollections.observableList(subList);
	}
}
