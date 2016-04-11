package client.controler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import client.view.PagedTable;
import model.Book;
import model.Library;

public class TableControler {
	private Library library;
	private Map<PagedTable, Predicate<Book>> observer;

	private TableControler() {
		observer = new HashMap<>();
	}

	public TableControler(Library lib) {
		this();
		library = lib;
	}

	public boolean addBookToLibrary(Book book) {
		boolean isAdded = library.addBook(book);
		if(isAdded){
			fireModelAddBook(book);
		}
		return isAdded;
	}

	private void fireModelAddBook(Book book) {
		for(PagedTable table: observer.keySet()){
			if(observer.get(table).test(book)){
				table.addBook(book);
			}
		}
	}

	private void fireModelChange() {
		for(PagedTable table : observer.keySet()){
			table.setBooks(library.getBooks(observer.get(table)));
		}
	}
	
	public void openFile(List<Book> list){
		library.openFile(list);
		fireModelChange();
	}
	
	public List<Book> getBooks() {
		return library.getBooks();
	}

	public List<Book> getBooks(Predicate<Book> predicate) {
		return library.getBooks(predicate);
	}

	public void registerObserverTable(PagedTable table) {
		registerObserverTable(table, (book) -> {
			return true;
		});
	}

	public void registerObserverTable(PagedTable table, Predicate<Book> predicate) {
		observer.put(table, predicate);
	}

	public List<Book> removeBooks(Predicate<Book> predicate) {
		List<Book> out = new ArrayList<>(library.remove(predicate));
		fireModelChange();
		return out;
	}
}
