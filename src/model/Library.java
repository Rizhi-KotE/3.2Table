package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Predicate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import view.MainTable;

public class Library {
	private ObservableList<Book> books;
	private IntegerProperty size = new SimpleIntegerProperty();
	private Map<MainTable, ObservableList<Book>> tableListMap = new HashMap<>();
	private Map<MainTable, ListChangeListener<Book>> listenerMap = new HashMap<>();
	private Map<MainTable, ObservableList<Book>> booksFilter = new HashMap<>();
	private int groupSize = 10;

	private final class SubListListener implements ListChangeListener<Book> {
		ObservableList<Book> target;

		public SubListListener(ObservableList<Book> list) {
			target = list;
		}

		@Override
		public void onChanged(ListChangeListener.Change<? extends Book> e) {
			while (e.next()) {
				if (e.wasAdded()) {
					for (Book book : e.getAddedSubList()) {
						if (target.size() < groupSize)
							target.add(book);
					}
				}
			}
		}
	}

	public enum booksType {
		ALL, FINDED
	};

	/**
	 * @return the books
	 */
	public ObservableList<Book> addBookFilter(MainTable table, booksType type) {
		switch (type) {
		case ALL: {
			if (booksFilter.put(table, books) == null) {
				tableListMap.put(table, getFirstGroup(table, books));
			}
			break;
		}
		case FINDED: {
			tableListMap.put(table, FXCollections.emptyObservableList());
			break;
		}
		}
		return tableListMap.get(table);
	}

	private ObservableList<Book> getFirstGroup(MainTable table, ObservableList<Book> list) {
		if (list.size() < groupSize) {
			ListIterator<Book> it = list.listIterator();
			List<Book> subList = new ArrayList<>(groupSize);
			while (it.hasNext()) {
				subList.add(it.next());
			}
			ObservableList<Book> outList = FXCollections.observableList(subList);
			ListChangeListener<Book> listener = new SubListListener(outList);
			listenerMap.put(table, listener);
			list.addListener(listener);
			return outList;
		} else {
			return FXCollections.observableList(list.subList(0, groupSize));
		}
	}

	public Library() {
		books = FXCollections.observableArrayList();
		//books.addAll(new Book("asd", "qwe", 1, 2));
		ListChangeListener<Book> listener = (e) -> {
			size.set(books.size());
			while (e.next()) {
				if (e.wasRemoved())
					for (MainTable table : tableListMap.keySet())
						table.refresh();
				break;
			}
		};
		books.addListener(listener);
		size.set(books.size());
	}

	public void addBook(Book book) {
		books.add(book);
	}

	public IntegerProperty sizeProperty() {
		return size;
	}

	public ObservableList<Book> getGroup(MainTable table, int groupNumber, int groupSize) {
		ObservableList<Book> subList = FXCollections.observableArrayList();
		ObservableList<Book> books = booksFilter.get(table);
		ListIterator<Book> it = books.listIterator(groupNumber * groupSize);
		while (it.hasNext() && (subList.size() < groupSize)) {
			subList.add(it.next());
		}
		ListChangeListener<Book> listener = new SubListListener(subList);
		ObservableList<Book> oldList = tableListMap.get(table);
		ListChangeListener<Book> oldListener = listenerMap.get(table);
		if (oldListener != null)
			books.removeListener(oldListener);
		books.addListener(listener);
		tableListMap.put(table, subList);
		listenerMap.put(table, listener);
		return tableListMap.get(table);
	}

	public ObservableList<Book> find(MainTable table, Predicate<Book> calcResultPredicate) {
		booksFilter.put(table, FXCollections.observableArrayList(books.filtered(calcResultPredicate)));
		tableListMap.put(table, getFirstGroup(table, booksFilter.get(table)));
		return tableListMap.get(table);
	}

	public Collection<Book> getBooks() {
		return books;
	}

	public void openFile(Collection<Book> elements) {
		books.clear();
		books.addAll(elements);
	}

	public ObservableList<Book> getAllBooks(MainTable table){
		if(booksFilter.get(table)==null){
			booksFilter.put(table, FXCollections.observableList(new ArrayList<>()));
		}
		return booksFilter.get(table);
	}
	public void remove(MainTable table, Predicate<Book> calcResultPredicate) {
		books.removeIf(calcResultPredicate);
		
	}
}
