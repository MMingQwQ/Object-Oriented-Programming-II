// -------------------------------------------------------------------- 
// Assignment 1
// Question: Part II
// Written by: Mingming Zhang 
//
// For COMP 249 Section (S) – Winter 2024
// --------------------------------------------------------------------

package Driver;

import java.util.Scanner;
import Client.*;
import Items.*;

/**
 * This is Driver class implementing main method. There are some methods
 * requested in assignment syllabus: 1)A method called getBiggestBook() to
 * display the biggest book 2)A method called copyBooks() to make a deep copy of
 * the array of books passed as parameter
 * 
 * @author Mingming Zhang
 * @version created on 2024/02/26
 */

public class Driver {
	// Make attribute private to prevent privacy leak
	private static Scanner keyin = new Scanner(System.in);

	/**
	 * Our main method - prompt user to make a choice: menu or predefined scenario;
	 * 
	 * @param args String[] (The command line arguments)
	 */
	public static void main(String[] args) {
		Library.welcome();

		// Prompt user to choice menu or predefined scenario
		boolean goodopt = true;
		while (goodopt) {
			System.out.println("Please select the following option:");
			System.out.println("	1.	Get the menu");
			System.out.println("	2.	Run a predefined/hard-coded scenario");
			Library.checkValidInt(keyin);
			int opt = keyin.nextInt();
			if (opt == 1) {
				goodopt = false; // valid opt input to terminate the outer while loop

				int option;
				do {
					Library.displayMenu();
					Library.checkValidInt(keyin);
					option = keyin.nextInt();
					switch (option) {
					case 1: {
						// 1.Add an Item
						Library.addItem(keyin);
					}
						break;
					case 2: {
						// 2.Delete an Item
						Library.deleteItem(keyin);
						break;
					}
					case 3: {
						// 3.Change information of an Item
						Library.editItem(keyin);
						break;
					}
					case 4: {
						// 4.List all items in a specific category (book, journal, or media)
						Library.displayByCategory(keyin);
						break;
					}
					case 5: {
						// 5.Print all items (from all categories)
						Library.displayAllItems(Library.itemsArr);
						break;
					}
					case 6: {
						// 6.Add a client
						Clients.addClient(keyin);
						break;
					}
					case 7: {
						// 7.Edit a client
						Clients.editClient(keyin);
						break;
					}
					case 8: {
						// 8.Delete a client
						Clients.deleteClient(keyin);
						break;
					}
					case 9: {
						// 9. Lease an item to a client
						Clients.leaseItemToClient(keyin);
						break;

					}
					case 10: {
						// 10. Return an item from a client
						Clients.returnAnItemFromCLient(keyin);
						break;

					}
					case 11: {
						// 11. Show all items leased by a client
						Clients.showAllLeasedItemByAClient(keyin);
						break;
					}
					case 12: {
						// 12. Show all leased items (by all clients)
						Clients.showAllLeasedItemsByAllClients();
						break;
					}
					case 13: {
						// 13. Display the biggest book
						getBiggestBook(Library.itemsArr);
						break;

					}
					case 14: {
						// 14. Make a copy of the books array
						copyBooks(Library.itemsArr);
						break;
					}
					}
					// 15. Quit

				} while (option != 15);

			}
			if (opt == 2) {
				goodopt = false; // valid opt input to terminate the outer while loop
				// Run a predefined/hard-coded scenario
				// 1)create 5 objects of each class and display their information.
				Clients client1 = new Clients("Alex", "514-111-1111", "Alex111@gmail.com");
				Clients client2 = new Clients("Anna", "514-111-1112", "Anna222@gmail.com");
				Clients client3 = new Clients();
				Clients client4 = new Clients(client2);
				Clients client5 = new Clients("Alex", "514-111-1111", "Alex11@gmail.com");

				Journals journal1 = new Journals("Journal1", "Author1", 1998, 1);
				Journals journal2 = new Journals("Journal2", "Author2", 1999, 2);
				Journals journal3 = new Journals();
				Journals journal4 = new Journals(journal2);
				Journals journal5 = new Journals("Journal1", "Author1", 1999, 1);

				Books book1 = new Books("Book1", "Alice", 2001, 300);
				Books book2 = new Books("Book2", "Eva", 2002, 200);
				Books book3 = new Books();
				Books book4 = new Books(book2);
				Books book5 = new Books("Book1", "Alice", 2001, 310);

				Media media1 = new Media("Media1", "Claire", 2004, "video");
				Media media2 = new Media("Media2", "David", 2005, "audio");
				Media media3 = new Media();
				Media media4 = new Media(media2);
				Media media5 = new Media("Media1", "Claire", 2004, "vide0");

				// 2)create an array for each one of the types of items.
				// and create another array for all items.
				Clients[] clientTest = new Clients[10];
				Journals[] journalTest = new Journals[10];
				Books[] booksTest = new Books[10];
				Media[] mediaTest = new Media[10];
				Items[] itemTest = new Items[30];

				clientTest[0] = client1;
				clientTest[1] = client2;
				clientTest[2] = client3;
				clientTest[3] = client4;
				clientTest[4] = client5;

				journalTest[0] = journal1;
				journalTest[1] = journal2;
				journalTest[2] = journal3;
				journalTest[3] = journal4;
				journalTest[4] = journal5;

				booksTest[0] = book1;
				booksTest[1] = book2;
				booksTest[2] = book3;
				booksTest[3] = book4;
				booksTest[4] = book5;

				mediaTest[0] = media1;
				mediaTest[1] = media2;
				mediaTest[2] = media3;
				mediaTest[3] = media4;
				mediaTest[4] = media5;

				Items.resetItemCount();
				copySubArrToParentArr(itemTest, journalTest);
				copySubArrToParentArr(itemTest, booksTest);
				copySubArrToParentArr(itemTest, mediaTest);

				System.out.println("\n*Display all clients in the client array:");
				Clients.displayAllClients(clientTest);
				System.out.println("*Display all items in the item array:");
				Library.displayAllItems(itemTest);
				System.out.println("*Display all items in the journal array:");
				Library.displayAllItems(journalTest);
				System.out.println("*Display all items in the book array:");
				Library.displayAllItems(booksTest);
				System.out.println("*Display all items in the media array:");
				Library.displayAllItems(mediaTest);

				// 3)test the equality of some objects.
				System.out.println("Is client1 equals to client2? " + client1.equals(client2)); // Output: false
				System.out.println("Is client1 equals to client3? " + client1.equals(client3)); // Output: false
				System.out.println("Is client1 equals to client5? " + client1.equals(client5)); // Output: false
				System.out.println("Is client4 equals to client2? " + client4.equals(client2)); // Output: True
				System.out.println("Is client4 equals to Book1? " + client4.equals(book1)); // Output: false
				System.out.println();
				System.out.println("Is Journal1 equals to Journal2? " + journal1.equals(journal2)); // Output: false
				System.out.println("Is Journal1 equals to Journal3? " + journal1.equals(journal3)); // Output: false
				System.out.println("Is Journal1 equals to Journal5? " + journal1.equals(journal5)); // Output: false
				System.out.println("Is Journal4 equals to Journal2? " + journal4.equals(journal2)); // Output: True
				System.out.println("Is Journal4 equals to Book1? " + journal4.equals(book1)); // Output: false
				System.out.println();
				System.out.println("Is Book1 equals to Book2? " + book1.equals(book2)); // Output: false
				System.out.println("Is Book1 equals to Book3? " + book1.equals(book3)); // Output: false
				System.out.println("Is Book1 equals to Book5? " + book1.equals(book5)); // Output: false
				System.out.println("Is Book4 equals to Book2? " + book4.equals(book2)); // Output: True
				System.out.println("Is Book4 equals to Media1? " + book4.equals(media1)); // Output: false
				System.out.println();
				System.out.println("Is Media1 equals to Media2? " + media1.equals(media2)); // Output: false
				System.out.println("Is Media1 equals to Media3? " + media1.equals(media3)); // Output: false
				System.out.println("Is Media1 equals to Media5? " + media1.equals(media5)); // Output: false
				System.out.println("Is Media4 equals to Media2? " + media4.equals(media2)); // Output: True
				System.out.println("Is Media4 equals to Journal1? " + media4.equals(journal1)); // Output: false

				// 4)call the getBiggestBook() with the array of books and display the result.
				getBiggestBook(booksTest);

				// 5)Finally, call the method copyBooks() on the array of Media.
				copyBooks(mediaTest);
//				copyBooks(booksTest);
//				copyBooks(itemTest);
			}
		}
		// Display a closing message and close the Scanner
		Library.closing();
		keyin.close();
	}

	/**
	 * 13.Display the biggest book with the biggest number of pages
	 * 
	 * @param inArr Items[]
	 */
	public static void getBiggestBook(Items[] inArr) {
		boolean findBook = false;
		// Check if the input array is empty
		if (inArr[0] == null) {
			System.out.println("The list is empty");
		} else {
			// Declare a variable to store the biggest value
			Books biggestBook = new Books();
			// When call constructor it increased by 1, not necessary so decrease it
			Items.decreaseItemCount();
			for (Items item : inArr) {
				if (item != null) {
					if (item instanceof Books) {
						if (((Books) item).getNumOfPages() > biggestBook.getNumOfPages()) {
							biggestBook = (Books) item; // Casting obj into Books
							findBook = true;
						}
					}
				}
			}
			if (findBook)
				System.out.println("\nThe biggest Book is :" + "\n" + biggestBook);
			if (!findBook)
				System.out.println("The list has no Book.");
		}
	}

	/**
	 * 14.Make a deep copy of the array of books passed as parameter
	 * 
	 * @param bookArr Books[]
	 */
	public static void copyBooks(Books[] bookArr) {
		if (bookArr == null || bookArr.length == 0) {
			System.out.println("\nYou cannot copy from an empty list.");
			return;
		}

		// Count books amount in order to decide copy array length
		int count = 0;
		for (Books book : bookArr) {
			if (book != null)
				count++;
		}

		// Allocate the copyBookArr with needed capacity
		Books[] copyBookArr = new Books[count];
		int index = 0;
		for (Books book : bookArr) {
			if (book != null) {
				// Use copy constructor to make a deep copy
				copyBookArr[index++] = new Books(book);
			}
		}
		// Display the deep copy book array
		System.out.println("\nThe new copied books info are as follows:");
		for (Books book : copyBookArr) {
			if (book != null) {
				System.out.println(book);
			}
		}
	}

	/**
	 * 14.1)If the passed parameter is not Book array (Overloading)
	 * 
	 * @param inArr Items[]
	 */
	public static void copyBooks(Items[] inArr) {
		if (inArr == null || inArr.length == 0) {
			System.out.println("\nYou cannot copy from an empty list.");
			return;
		}

		// Count the number of books in the input array
		int bookCount = 0;
		for (Items item : inArr) {
			if (item instanceof Books) {
				bookCount++;
			}
		}
		// Check if the input array has book
		if (bookCount == 0) {
			System.out.println("\nYou cannot copy anything other than books.");
			return;
		}
		Books[] copyBookArr = new Books[bookCount];

		// Copy books into the new array
		int index = 0;
		for (Items item : inArr) {
			if (item != null) {
				if (item instanceof Books) {
					// Use copy constructor to make a deep copy
					copyBookArr[index++] = new Books((Books) item);
				}
			}
		}
		// Display the deep copy book array
		System.out.println("\nThe deep copy books info are as follows:");
		for (Books book : copyBookArr) {
			if (book != null) {
				System.out.println(book);
			}
		}
	}

	/**
	 * copy child array object to parent array
	 * 
	 * @param parentArr Items[]
	 * @param childArr  Items[]
	 */
	public static void copySubArrToParentArr(Items[] parentArr, Items[] childArr) {
		int copyCount = 0;
		for (Items item : childArr) {
			if (item != null) {
				copyCount++;
			}
		}
		for (int i = 0; i < copyCount; i++) {
			if (Items.getItemCount() < parentArr.length) {
				if (parentArr[Items.getItemCount()] == null) {
					parentArr[Items.getItemCount()] = childArr[i];
					Items.increaseItemCount();
				}
			} else {
				System.out.println("Not enough space to copy all items.");
				break;
			}
		}
	}
}
