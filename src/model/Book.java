package model;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book implements NamedFields{
	static Map<Field, String> fieldName = initMap();
	static Map<Field, String> initMap(){
		Map<Field, String> map = new HashMap<>();
		try {
			map.put(Book.class.getDeclaredField("authorName"), "Author");
			map.put(Book.class.getDeclaredField("bookName"), "Book's name");
			map.put(Book.class.getDeclaredField("circulate"), "circulate");
			map.put(Book.class.getDeclaredField("finalTomeNumber"), "Final tomes number");
			map.put(Book.class.getDeclaredField("tomeNumber"), "Tome's number");
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
	private IntegerProperty circulation = new SimpleIntegerProperty();
	private IntegerProperty finalTomeNumber = new SimpleIntegerProperty();
	private IntegerProperty tomeNumber = new SimpleIntegerProperty();

	public Book(String a, String b, int c, int e) {
		bookName.set(a);
		authorName.set(b);
		circulation.set(c);
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
	 * @return the circulation
	 */
	public int getCirculation() {
		return circulation.get();
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
	 * @param circulation
	 *            the circulation to set
	 */
	public void setCirculation(int circulation) {
		this.circulation.set(circulation);
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
