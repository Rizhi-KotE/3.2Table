package model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book implements NamedFields{
	public static final String TOME_NUMBER = "tomeNumber";
	public static final String FINAL_TOME_NUMBER = "finalTomeNumber";
	public static final String CIRCULATION = "circ";
	public static final String BOOK_NAME = "bookName";
	public static final String AUTHOR_NAME = "authorName";
	static Map<Field, String> fieldName = initMap();
	static Map<Field, String> initMap(){
		Map<Field, String> map = new HashMap<>();
		try {
			map.put(Book.class.getDeclaredField(AUTHOR_NAME), "Author");
			map.put(Book.class.getDeclaredField(BOOK_NAME), "Book's name");
			map.put(Book.class.getDeclaredField(CIRCULATION), "Circulation");
			map.put(Book.class.getDeclaredField(FINAL_TOME_NUMBER), "Final tomes number");
			map.put(Book.class.getDeclaredField(TOME_NUMBER), "Tome's number");
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	private StringProperty authorName = new SimpleStringProperty();
	private StringProperty bookName = new SimpleStringProperty();
	private IntegerProperty circ = new SimpleIntegerProperty();
	private IntegerProperty finalTomeNumber = new SimpleIntegerProperty();
	private IntegerProperty tomeNumber = new SimpleIntegerProperty();

	public Book(String a, String b, int c, int e) {
		bookName.set(a);
		authorName.set(b);
		circ.set(c);
		tomeNumber.set(e);
		finalTomeNumber.set(c * e);
	}

	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName.get();
	}

	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName.get();
	}

	/**
	 * @return the circ
	 */
	public int getCirculation() {
		return circ.get();
	}

	/**
	 * @return the finalTomeNumber
	 */
	public int getFinalTomeNumber() {
		return finalTomeNumber.get();
	}

	/**
	 * @return the tomeNumber
	 */
	public int getTomeNumber() {
		return tomeNumber.get();
	}

	/**
	 * @param authorName
	 *            the authorName to set
	 */
	public void setAuthorName(String author) {
		authorName.set(author);
	}

	/**
	 * @param bookName
	 *            the bookName to set
	 */
	public void setBookName(String name) {
		this.bookName.set(name);
	}

	/**
	 * @param circ
	 *            the circ to set
	 */
	public void setCirculation(int circulation) {
		this.circ.set(circulation);
	}

	/**
	 * @param finalTomeNumber
	 *            the finalTomeNumber to set
	 */
	public void setFinalTomeNumber(int calcTomes) {
		finalTomeNumber.set(calcTomes);
	}

	/**
	 * @param tomeNumber
	 *            the tomeNumber to set
	 */
	public void setTomeNumber(int tomes) {
		tomeNumber.set(tomes);
	}

	@Override
	public String getFieldName(Field filed) {
		return fieldName.get(filed);
	}

}
