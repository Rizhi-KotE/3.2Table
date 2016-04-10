package controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import model.Book;
import model.Library;
import view.PagedTable;

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
		fireModelChange();
		return isAdded;
	}

	private void fireModelChange() {
		for(PagedTable table : observer.keySet()){
			table.setBooks(library.getBooks(observer.get(table)));
		}
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
		List<Book> out = library.remove(predicate);
		fireModelChange();
		return out;
	}
}
