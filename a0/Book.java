// -------------------------------------------------------------------- 
// Assignment 0
// Question: Part I
// Written by: Mingming Zhang 
//
// For COMP 249 Section (S) – Winter 2024
// Created: Wed Jan 24 2024
// --------------------------------------------------------------------

class Book {
	private String title;
	private String author;
	private long ISBN;
	private double price;
	private int numOfBooks = 0;

	// 1)The default constructor:
	public Book() {
		title = "";
		author = "";
		ISBN = 0;
		price = 0;
		this.numOfBooks++;
	}

	// 2) custom constructor:
	public Book(String inTitle, String inAuthor, long inISBN, double inPrice) {
		this.title = inTitle;
		this.author = inAuthor;
		this.ISBN = inISBN;
		this.price = inPrice;
		this.numOfBooks++;
	}

	// 3) The copy constructor
	public Book(Book objBook) {
		if (objBook == null)
			System.exit(0);
		title = objBook.title;
		author = objBook.author;
		ISBN = objBook.ISBN;
		price = objBook.price;
	}

	// Accessor
	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public long getISBN() {
		return ISBN;
	}

	public double getPrice() {
		return price;
	}

	// Mutator
	public void setTitle(String inTitle) {
		this.title = inTitle;
	}

	public void setAuthor(String inAuthor) {
		this.author = inAuthor;
	}

	public void setISBN(long inISBN) {
		this.ISBN = inISBN;
	}

	public void setPrice(double inPrice) {
		this.price = inPrice;
	}

	// Overload Method()
	// The overload method, equals(Book), returns true only if
	// this instance Book object and the corresponding (passed as an argument)
	// Book object have same ISBN and price.
	public boolean equals(Book anotherBook) {
		if (this.ISBN - anotherBook.ISBN == 0 & this.price - anotherBook.price == 0)
			return true;
		else
			return false;
	}

	// Display the Book object
	public String toString() {
		String Bookinfo = "Author: " + author + "\n" + "Title: " + title + "\n" + "ISBN: " + ISBN + "#\n" + "Price: $"
				+ price;
		return Bookinfo;
	}

	// findNumberOfCreatedBooks()
	public int findNumberOfCreatedBooks() {
		return numOfBooks;
	}

	// Display the welcome message
	public static void displayWelcome() {
		// Display a welcome banner
		System.out.println("+++++++++++++++++++++++++++++++++++++++");
		System.out.println("+ Welcome to the Concordia Book Store +");
		System.out.println("+++++++++++++++++++++++++++++++++++++++");
		System.out.println();

	}

	// Display the main menu
	public static void displayMainMenu() {
		// Display the main menu
		System.out.println("What do you want to do?");
		System.out.println("	1.	Enter new books (password required)");
		System.out.println("	2.	Change information of a book(password required)");
		System.out.println(" 	3.	Display all books by a specific author");
		System.out.println("	4.	Display all books under a certain a price.");
		System.out.println("	5.	Quit");
		System.out.print("Please enter your choice> ");
	}

	// Display the update menu
	public static void displayUpdateMenu() {
		System.out.println("What information would you like to change? ");
		System.out.println("	1.	author ");
		System.out.println("	2.	title ");
		System.out.println("	3.	ISBN ");
		System.out.println("	4.	price ");
		System.out.println("	5.	Quit ");
		System.out.print("Enter your choice> ");
	}
}