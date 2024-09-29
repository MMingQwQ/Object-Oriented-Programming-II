// -------------------------------------------------------------------- 
// Assignment 0
// Question: Part II
// Written by: Mingming Zhang
//
// For COMP 249 Section (S) – Winter 2024
// Created: Wed Jan 24 2024
// --------------------------------------------------------------------

import java.util.Scanner;

public class BookStore {
	// Constant variable declarations
	private static String password = "249";
	private static final int MAX_ATTEMPTS = 3;
	private static Book[] inventory;

	public static void main(String[] args) {
		// Variables declarations
		Scanner keyin = new Scanner(System.in);
		int maxBooks = 0; // to store the maximum number of books that inventory can store

		int choice = 0; // to store choice
		String str; // to store user's input
		boolean running = true; // to control main menu loop
		int check1 = 0; // every time 3_fail_attempts for password, check1 will increased by 1
		int index = 0; // index of inventory

		// Display the welcome message
		Book.displayWelcome();

		// Prompt for the maximum numbers of books
		System.out.println("Please enter the maximum number of books your bookstore can contain: ");
		maxBooks = keyin.nextInt();
		inventory = new Book[maxBooks];

		// Main menu with while-loop
		while (running) {
			// Display the main menu
			Book.displayMainMenu();
			choice = keyin.nextInt();

			// Validate the menu option
			if (choice < 1 || choice > 5) {
				continue;
			}

			// Option 1: Check Password and Add new books to inventory
			if (choice == 1) {
				boolean op1 = true; // to control option1 while-loop
				while (op1) {
					if (promptForPassword(keyin)) {
						// prompt users for the number of books and check if there is enough space
						System.out.println("Please enter the number of books you want to entry:  ");
						int numOfBook = keyin.nextInt();

						// Validate the number entered
						if (numOfBook < 0 || numOfBook > maxBooks) {
							System.out.println("The maximum amount of book you can enter is " + maxBooks
									+ ". Please try again :)");
						} else {
							// Collecting Books info and add to inventory
							for (int i = 0; i < numOfBook; i++, index++) {
								System.out.println("Please enter the Author of the book" + index + ": ");
								String inAuthor = keyin.next();

								System.out.println("Please enter the Title of the book" + index + ": ");
								String inTitle = keyin.next();

								System.out.println("Please enter the ISBN of the book" + index + ": ");
								long inISBN = keyin.nextLong();

								System.out.println("Please enter the Price of the book" + index + ": ");
								double inPrice = keyin.nextDouble();

								addBook(new Book(inTitle, inAuthor, inISBN, inPrice));
								System.out.println("Book # " + index);
								System.out.println(inventory[index] + "\n");
							}

							System.out.println("\nBooks are added successfully. You will return to the main menu. \n");

							index--;
						}
						op1 = false;
					} else {
						// if password attempt fails for 3 times, check1 will be increased by 1
						check1++;
						if (check1 == 4) {
							// which means total attempt failure = 12
							System.out.println(
									"Program detected suspicious activities " + "and will terminate immediately!");
							op1 = false; // exit option1
							running = false; // exit main menu
						} else {
							op1 = false; // exit option1
						}
					}
				}
			}
			// Option 2: Check Password and Update the inventory
			if (choice == 2) {
				boolean op2 = true;
				while (op2) {
					if (promptForPassword(keyin)) {
						System.out.println("Which book you want to update, please enter the book number:");
						int numOfbook = keyin.nextInt();

						System.out.println();

						// Validate the number entered
						boolean check2 = true;
						boolean add = false; // Add is initialized to be false
						while (check2) {
							if (numOfbook < 0 || numOfbook > index || inventory[numOfbook] == null) {
								System.out.println("There is no Book object at the specified index location. "
										+ "\nDo you want to re-enter another book or quit this operation? "
										+ "\n(Enter the index number agian OR Press * to return to main menu)");
								str = keyin.next();
								if (str.equalsIgnoreCase("*")) {
									check2 = false;
									op2 = false;
								} else {
									numOfbook = Integer.valueOf(str); // convert String to int
									continue;
								}
							} else {
								check2 = false;
								add = true;
							}
						}
						// after validation, the program carry out "Add"
						if (add) {
							System.out.println("The current information of the book is:");
							System.out.println("Book # " + (numOfbook));
							System.out.println(inventory[numOfbook]);
							System.out.println();

							// Display the updated menu
							Book.displayUpdateMenu();
							int option = keyin.nextInt();

							// Validate the menu option and update accordingly
							boolean update = true;
							while (update) {
								if (option < 1 || option > 5) {
									continue;
								} else if (option == 1) {
									System.out.println("Please enter the new author's name: ");
									String inAuthor = keyin.next();
									inventory[numOfbook].setAuthor(inAuthor);
									System.out.println();
									System.out.println("The updated information of book is as follows:");
									System.out.println("Book # " + numOfbook);
									System.out.println(inventory[numOfbook]);

									System.out.println(
											"\nBook is updated successfully. You will return to the main menu. \n");
									update = false;
									op2 = false;
								} else if (option == 2) {
									System.out.println("Please enter the new Title: ");
									String inTitle = keyin.next();
									inventory[numOfbook].setTitle(inTitle);
									System.out.println();
									System.out.println("The updated information of book is as follows:");
									System.out.println("Book # " + numOfbook);
									System.out.println(inventory[numOfbook]);

									System.out.println(
											"\nBook is updated successfully. You will return to the main menu. \n");
									update = false;
									op2 = false;
								} else if (option == 3) {
									System.out.println("Please enter the new ISBN: ");
									long inISBN = keyin.nextLong();
									inventory[numOfbook].setISBN(inISBN);
									System.out.println();
									System.out.println("The updated information of book is as follows:");
									System.out.println("Book # " + numOfbook);
									System.out.println(inventory[numOfbook]);

									System.out.println(
											"\nBook is updated successfully. You will return to the main menu. \n");
									update = false;
									op2 = false;
								} else if (option == 4) {
									System.out.println("Please enter the new price:");
									double inPrice = keyin.nextDouble();
									inventory[numOfbook].setPrice(inPrice);
									System.out.println();
									System.out.println("The updated information of book is as follows:");
									System.out.println("Book # " + numOfbook);
									System.out.println(inventory[numOfbook]);

									System.out.println(
											"\nBook is updated successfully. You will return to the main menu. \n");
									update = false;
									op2 = false;
								} else if (option == 5) {
									update = false;
									op2 = false;
								}
							}
						}
					} else
						op2 = false;
				}
			}

			// Option 3: Display the books by the requested author
			if (choice == 3) {
				System.out.println("Please enter the author's name:");
				str = keyin.next();
				for (int i = 0; i < inventory.length; i++) {
					if (inventory[i] == null) {
						break;
					} else if (inventory[i].getAuthor().equalsIgnoreCase(str)) {
						System.out.println();
						System.out.println("Book # " + (i));
						System.out.println(inventory[i]);
						System.out.println();
					}
				}
			}

			// Option 4: Display the books that have a value smaller than that user entered
			// value
			if (choice == 4) {
				System.out.println("Please enter a value (representing a price): ");
				int inPrice = keyin.nextInt();
				for (int i = 0; i < inventory.length; i++) {
					if (inventory[i] == null) {
						break;
					} else if (inPrice > inventory[i].getPrice()) {
						System.out.println();
						System.out.println("Book # " + (i));
						System.out.println(inventory[i]);
						System.out.println();
					}
				}

			}

			// Option 5 : Quit and Display a closing message

			if (choice == 5) {
				running = false;
				System.out.println(">>> Exiting... ");

				// Display a closing banner
				System.out.println("Thank you for choosing our BookStore :)");

			}
		}
		// Close the Scanner
		keyin.close();
	}

	// Check the password for 3 times
	private static boolean promptForPassword(Scanner keyin) {
		for (int i = 0; i < MAX_ATTEMPTS; i++) {
			System.out.println("Please enter the correct password as a single-line entry:  ");
			String str = keyin.next();
			if (str.equalsIgnoreCase(password)) {
				return true;
			} else
				continue;
		}
		return false;
	}

	// Add New books to the array
	private static void addBook(Book book) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] == null) {
				inventory[i] = book;
				return;
			}
		}
	}

}
