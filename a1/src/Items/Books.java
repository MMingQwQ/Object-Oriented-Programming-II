// -------------------------------------------------------------------- 
// Assignment 1
// Question: Part II
// Written by: Mingming Zhang 
//
// For COMP 249 Section (S) – Winter 2024
// --------------------------------------------------------------------

package Items;

/**
 * This is Books class (child class of Items class)
 * 
 * @author Mingming Zhang
 * @version created on 2024/02/26
 */
public class Books extends Items {
	// Make attributes private to prevent privacy leak
	private int numOfPages;
	// Books IDs start with “B” (e.g. B1, B2, ...)
	private String bookID; // can apply getter but not setter since it's unique

	/**
	 * Default constructor
	 */
	public Books() {
		super();
		numOfPages = 0;
		bookID = "B" + itemID;

	}

	/**
	 * Parameterized constructor
	 * 
	 * @param itemName        String
	 * @param author          String
	 * @param publicationYear int
	 * @param numOfBook       int
	 */
	public Books(String itemName, String author, int publicationYear, int numOfBook) {
		super(itemName, author, publicationYear);
		this.numOfPages = numOfBook;
		bookID = "B" + itemID;
	}

	/**
	 * Copy constructor (with the exception of the ID)
	 * 
	 * @param anotherBooks Books
	 */
	public Books(Books anotherBooks) {
		super(anotherBooks);
		this.numOfPages = anotherBooks.numOfPages;
		bookID = "B" + itemID;
	}

	// Accessors
	/**
	 * @return int
	 */
	public int getNumOfPages() {
		return numOfPages;
	}

	/**
	 * @return String
	 */
	public String getBookID() {
		return bookID;
	}

	// Mutators
	/**
	 * @param numOfPages int
	 */
	public void setNumOfPages(int numOfPages) {
		this.numOfPages = numOfPages;
	}

	// toString() method : return a clear description and information of each object
	@Override
	public String toString() {
		return super.toString() + "\nBook's ID is: " + this.bookID + "\nBook's number of pages is: "
				+ this.numOfPages;
	}

	// equals() method
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			Books book = (Books) obj;// Casting obj into Books
			return this.numOfPages == book.numOfPages;
		} else
			return false;
	}
}
