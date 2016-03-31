package model;

import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Library {
	private List<Book> books;
	private ObservableList<Book> currentGroup;
	/**
	 * @return the books
	 */
	public ObservableList<Book> getBooks() {
		return currentGroup;
	}
	public Library() {
		books = new LinkedList<Book>();
		List list = new LinkedList<Book>();
		currentGroup = FXCollections.observableList(list);
	}
	public ObservableList<Book> getGroupOfBooks(int numOfGroup, int sizeOfGroup){
		return  currentGroup;
	}
	public void addBook(Book book) {
		books.add(book);
		if(currentGroup.size()<10){
			currentGroup.add(book);
		}
	}


	Class<?> getItemClass(){
		return Book.class;
	}
}
