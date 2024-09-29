// -------------------------------------------------------------------- 
// Assignment 1
// Question: Part II
// Written by: Mingming Zhang
//
// For COMP 249 Section (S) – Winter 2024
// --------------------------------------------------------------------
package Items;

import java.util.Scanner;
import Client.*;

/**
 * This is Library class implementing most methods related to library
 * management. It declares Clients Array and Item Array and Methods including:
 * 1)Display a welcome message 2)Display the menu 3)Display the closing message
 * 4)Check for valid integer input to prevent program crush 5) Methods used for
 * menu: 1. Add an Item (check if the Item is empty + Check if the entered Item
 * ID could be found) 2. Delete an Item 3. Change information of an Item 4. List
 * all items in a specific category (book,journal, or media) 5. Print all items
 * (from all categories)
 * 
 * @author Mingming Zhang
 * @version created on 2024/02/26
 */
public class Library {
	/**
	 * Declare Clients Array assuming max 20 clients
	 */
	public static Clients[] clientsArr = new Clients[20];
	/**
	 * Declare Items Array assuming max 100 clients
	 */
	public static Items[] itemsArr = new Items[100];

	/**
	 * Display a welcome message which includes my name(s).
	 */
	public static void welcome() {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+ Welcome to MINGMING's FunReadings Library +");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println();
	}

	/**
	 * Display the menu
	 */
	public static void displayMenu() {
		System.out.println("What do you want to do?");
		System.out.println("	1.	Add an Item");
		System.out.println("	2.	Delete an Item");
		System.out.println(" 	3.	Change information of an Item");
		System.out.println("	4.	List all items in a specific category (book, journal, or media)");
		System.out.println("	5.	Print all items (from all categories)");
		System.out.println("	6.	Add a client");
		System.out.println("	7.	Edit a client");
		System.out.println(" 	8.	Delete a client");
		System.out.println(" 	9.	Lease an item to a client ");
		System.out.println("	10.	Return an item from a client");
		System.out.println("	11.	Show all items leased by a client");
		System.out.println("	12.	Show all leased items (by all clients)");
		System.out.println("	13.	Display the biggest book");
		System.out.println("	14.	Make a copy of the books array");
		System.out.println("	15.	Quit");
		System.out.print("Please enter your choice> ");
	}

	/**
	 * Display the closing message
	 */
	public static void closing() {
		System.out.println(">>> Exiting... ");
		System.out.println("Thank you for choosing MINGMING's FunReadings Library :)");
	}

	/**
	 * Check for valid integer input to prevent program crush
	 * 
	 * @param keyin Scanner
	 */
	public static void checkValidInt(Scanner keyin) {
		while (!keyin.hasNextInt()) {
			String badInput = keyin.next();
			System.out.println("Error," + badInput + " is not a valid integer.Please try again:");
		}
	}

	/**
	 * 1.Add an Item to Item array
	 * 
	 * @param keyin Scanner
	 */
	public static void addItem(Scanner keyin) {
		if (itemsArr[itemsArr.length - 1] != null) {
			System.out.println("Sorry, the Itemdatabase is full");
		} else {
			// Prompt and record the item general info
			Items item;
			System.out.println("Please enter the Item's Name: ");
			String itemName = keyin.next();

			System.out.println("Please enter the Item's Author: ");
			String itemAuthor = keyin.next();

			System.out.println("Please enter the Item's Publication Year (Integer): ");
			checkValidInt(keyin);
			int year = keyin.nextInt();

			// Prompt for the category
			System.out.println(
					"Please enter the category " + "(one of the following categories: Journals, Books, or Media):");
			String category = keyin.next();
			boolean incorrectType = true;
			while (incorrectType) {
				if (category.equalsIgnoreCase("Journals") || category.equalsIgnoreCase("Books")
						|| category.equalsIgnoreCase("Media")) {
					incorrectType = false;
				} else {
					System.out.println("Please enter the correct Type (Journals or Books, or Media): ");
					category = keyin.next();
				}
			}
			if (category.equalsIgnoreCase("Journals")) {
				System.out.println("Please enter the Volume Number(Integer): ");
				checkValidInt(keyin);
				int volumeNum = keyin.nextInt();
				item = new Journals(itemName, itemAuthor, year, volumeNum);
			} else if (category.equalsIgnoreCase("Books")) {
				System.out.println("Please enter the Number of Pages: ");
				checkValidInt(keyin);
				int pageNum = keyin.nextInt();
				item = new Books(itemName, itemAuthor, year, pageNum);
			} else {
				System.out.println("Please enter the Media Type(audio/video/interactive): ");
				String mediaType = keyin.next();
				item = new Media(itemName, itemAuthor, year, mediaType);
			}
			/*
			 * Decrease itemCount by 1 since when call constructor intemCount++, then the
			 * first element of array will always be null. In order to fully use the array,
			 * we start with itemCount-1 which begin at index 0
			 */
			itemsArr[Items.getItemCount() - 1] = item;
			System.out.println("The item is added successfully.");
		}
	}

	/**
	 * check if the Item is empty
	 * 
	 * @return boolean
	 */
	public static boolean checkItemArrEmpty() {
		boolean isEmpty = true;
		for (Items item : itemsArr) {
			if (item != null) {
				isEmpty = false;
				break;
			}
		}
		return isEmpty;
	}

	/**
	 * Check if the entered Item ID could be found
	 * 
	 * @param inID int (ID we want to find)
	 * @return boolean
	 */
	public static boolean findItemID(int inID) {
		boolean findItem = false;
		for (Items item : itemsArr) {
			if (item != null) {
				if (item.getItemID() == inID) {
					findItem = true;
					break;
				}
			}
		}
		return findItem;
	}

	/**
	 * 2.Delete an Item
	 * 
	 * @param keyin Scanner
	 */
	public static void deleteItem(Scanner keyin) {
		System.out.println("Please enter the item ID that you want to delete:");
		checkValidInt(keyin);
		int inID = keyin.nextInt();
		// Check if the array is empty
		if (checkItemArrEmpty()) {
			System.out.println("You cannot delete any entity from an EMPTY array");
		} else if (findItemID(inID)) {
			for (int i = 0; i < Items.getItemCount(); i++) {
				if (itemsArr[i] == null) {
					continue;
				} else if (itemsArr[i].getItemID() == inID) {
					// Shift all subsequence element one position to the left
					for (int j = i; j < Items.getItemCount() - 1; j++) {
						itemsArr[j] = itemsArr[j + 1];
					}
					// Set the last element to null since it's now duplicated
					itemsArr[Items.getItemCount() - 1] = null;
					System.out.println("The Item with ID " + inID + " has been successfully removed from item list.");
					break;
				}
			}
		} else
			System.out.println("Item with ID " + inID + " is not found");
	}

	/**
	 * 3.Change information of an Item
	 * 
	 * @param keyin Scanner
	 */
	public static void editItem(Scanner keyin) {
		System.out.println("Please enter the item ID that you want to change:");
		checkValidInt(keyin);
		int inID = keyin.nextInt();
		if (checkItemArrEmpty()) {
			System.out.println("You cannot delete any entity from an EMPTY array");
		} else if (findItemID(inID)) {
			for (Items item : itemsArr) {
				if (item == null)
					continue;
				else {
					if (item.getItemID() == inID) {
						int opt = 0;
						System.out.println("Please enter ONE option number that you want to change: ");
						System.out.println("1.ItemName");
						System.out.println("2.Author");
						System.out.println("3.PublicationYear");
						if (item instanceof Books) {
							System.out.println("4.NumbersOfPage");
							checkValidInt(keyin);
							opt = keyin.nextInt();
						}
						if (item instanceof Journals) {
							System.out.println("5.VolumeNumber");
							checkValidInt(keyin);
							opt = keyin.nextInt();
						}
						if (item instanceof Media) {
							System.out.println("6.Type");
							checkValidInt(keyin);
							opt = keyin.nextInt();
						}
						// Then, match to the corresponding options to change
						switch (opt) {
						case 1: {
							System.out.println("Please enter the new ItemName:");
							String newName = keyin.next();
							item.setItemName(newName);
							break;
						}
						case 2: {
							System.out.println("Please enter the new Author:");
							String newAuthor = keyin.next();
							item.setItemName(newAuthor);
							break;
						}
						case 3: {
							System.out.println("Please enter the new Publication Year:");
							checkValidInt(keyin);
							int newPubYear = keyin.nextInt();
							item.setPublicationYear(newPubYear);
							break;
						}
						case 4: {
							System.out.println("Please enter the new Numbers of Page:");
							checkValidInt(keyin);
							int newNumOfPage = keyin.nextInt();
							((Books) item).setNumOfPages(newNumOfPage);
							break;
						}
						case 5: {
							System.out.println("Please enter the new Volume Number:");
							checkValidInt(keyin);
							int newVolumeNum = keyin.nextInt();
							((Journals) item).setVolumeNumber(newVolumeNum);
							break;

						}
						case 6: {
							System.out.println("Please enter the new Media Type(audio/video/interactive):");
							String newMediaType = keyin.next();
							((Media) item).setType(newMediaType);
							break;
						}
						}
					}
				}
			}
		} else
			System.out.println("Item with ID " + inID + " is not found");
	}

	/**
	 * 4.List all items in a specific category (book, journal, or media)
	 * 
	 * @param keyin Scanner
	 */
	public static void displayByCategory(Scanner keyin) {
		System.out.println("Please enter the category list you want to display "
				+ "(one of the following categories: Journals, Books, or Media):");
		String category = keyin.next();
		boolean incorrectType = true;
		while (incorrectType) {
			if (category.equalsIgnoreCase("Journals") || category.equalsIgnoreCase("Books")
					|| category.equalsIgnoreCase("Media")) {
				incorrectType = false;
			} else {
				System.out.println("Please enter the correct Category (Journals or Books, or Media): ");
				category = keyin.next();
				continue;
			}
		}
		if (checkItemArrEmpty()) {
			System.out.println("The list is empty.");
		} else {
			if (category.equalsIgnoreCase("Journals")) {
				for (Items item : itemsArr) {
					if (item != null)
						if (item instanceof Journals)
							System.out.println(item);
				}
			} else if (category.equalsIgnoreCase("Books")) {
				for (Items item : itemsArr) {
					if (item != null)
						if (item instanceof Books)
							System.out.println(item);
				}
			} else {
				for (Items item : itemsArr) {
					if (item != null)
						if (item instanceof Media)
							System.out.println(item);
				}
			}
		}
	}

	/**
	 * 5.Print all items (from all categories)
	 * 
	 * @param inArr Items[]
	 */
	public static void displayAllItems(Items[] inArr) {
		boolean itemArrIsEmpty = true;
		for (Items item : inArr) {
			if (item != null) {
				System.out.println(item);
				System.out.println();
				itemArrIsEmpty = false;
			}
		}
		if (itemArrIsEmpty) {
			System.out.println("The Item List is empty");
		}
	}

}
