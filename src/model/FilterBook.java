package model;

import java.util.function.Predicate;

public class FilterBook {
	private Predicate<String> compareBookNames;
	private Predicate<String> compareAuthorName;
	private Predicate<Integer> compareCirculation;
	private Predicate<Integer> compareTomesNumber;
	private Predicate<Integer> compareFinalTomesNumber;

	public FilterBook(Predicate<String> compareBookNames, Predicate<String> compareAuthorName,
			Predicate<Integer> compareCirculation, Predicate<Integer> compareTomesNumber,
			Predicate<Integer> compareFinalTomesNumber) {
		super();
		this.compareBookNames = compareBookNames;
		this.compareAuthorName = compareAuthorName;
		this.compareCirculation = compareCirculation;
		this.compareTomesNumber = compareTomesNumber;
		this.compareFinalTomesNumber = compareFinalTomesNumber;
	}

	/**
	 * @return the compareBookNames
	 */
	public Predicate<String> getCompareBookNames() {
		return compareBookNames;
	}

	/**
	 * @return the compareAuthorName
	 */
	public Predicate<String> getCompareAuthorName() {
		return compareAuthorName;
	}

	/**
	 * @return the compareCirculation
	 */
	public Predicate<Integer> getCompareCirculation() {
		return compareCirculation;
	}

	/**
	 * @return the compareTomesNumber
	 */
	public Predicate<Integer> getCompareTomesNumber() {
		return compareTomesNumber;
	}

	/**
	 * @return the compareFinalTomesNumber
	 */
	public Predicate<Integer> getCompareFinalTomesNumber() {
		return compareFinalTomesNumber;
	}

}
