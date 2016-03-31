package model;

import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Library {
	private ObservableList<Book> books;
	/**
	 * @return the books
	 */
	public ObservableList<Book> getBooks() {
		return books;
	}
	public Library() {
		List<Book> list = new LinkedList<Book>();
		books = FXCollections.observableList(list);
	}
	public void addBook(Book book){
		books.add(book);
	}
}
