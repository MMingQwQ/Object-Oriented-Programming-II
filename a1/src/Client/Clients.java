// -------------------------------------------------------------------- 
// Assignment 1
// Question: Part II
// Written by: Mingming Zhang 
//
// For COMP 249 Section (S) – Winter 2024
// --------------------------------------------------------------------

package Client;

import java.util.Scanner;
import Items.*;

/**
 * This is Clients class mainly implementing methods related to client objects
 * 
 * @author Mingming Zhang
 * @version created on 2024/02/26
 */
public class Clients {
	// Make attributes private to prevent privacy leak
	private int clientID; // can apply getter but not setter since it's unique
	private String clientName;
	private String phoneNumber;
	private String email;

	// assume each client can lease max 10 items
	private Items[] leasedItem = new Items[10];
	private int leaseCount = 0;
	private static int clientCount = 0;

	/**
	 * Default constructor
	 */
	public Clients() {
		clientCount++;
		clientID = clientCount;
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param clientName  String
	 * @param phoneNumber String
	 * @param email       String
	 */
	public Clients(String clientName, String phoneNumber, String email) {
		this.clientName = clientName;
		this.phoneNumber = phoneNumber;
		this.email = email;

		clientCount++;
		clientID = clientCount;
	}

	/**
	 * Copy constructor (with the exception of the ID)
	 * 
	 * @param anotherClient Clients
	 */
	public Clients(Clients anotherClient) {
		this.clientName = anotherClient.clientName;
		this.phoneNumber = anotherClient.phoneNumber;
		this.email = anotherClient.email;

		clientCount++;
		clientID = clientCount;
	}

	// Accessors
	/**
	 * @return int (clientID)
	 */
	public int getClientID() {
		return clientID;
	}

	/**
	 * @return String (clientName)
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @return String (phoneNumber)
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return String (email)
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return int (clientCount)
	 */
	public static int getClientCount() {
		return clientCount;
	}

	/**
	 * @return int (leaseCount)
	 */
	public int getLeaseCount() {
		return leaseCount;
	}

	/**
	 * @return Items[] (leasedItem)
	 */
	public Items[] getLeasedItem() {
		return leasedItem;
	}

	// Mutators
	/**
	 * @param clientName String
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * @param phoneNumber String
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @param email String
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * To increase clientCount by 1
	 */
	public static void inceaseClientCount() {
		Clients.clientCount++;
	}

	/**
	 * To decrease clientCount by 1
	 */
	public static void decreaseClientCount() {
		Clients.clientCount--;
	}

	// toString() method : return a clear description and information of each object
	@Override
	public String toString() {
		return "\nThe client's ID is: " + this.clientID + "\nThe client's name is: " + this.clientName
				+ "\nThe client's phone number is: " + this.phoneNumber + "\nThe client's email is: "
				+ this.email;
	}

	// equals() method
	@Override
	public boolean equals(Object obj) {
		// verify if the passed object (to compare to) is null
		if (obj == null) {
			return false;
		}
		// verify if it is of a different type than the calling object
		else if (obj.getClass() != this.getClass()) {
			// System.out.println("Not the same class");
			return false;
		} else {
			Clients client = (Clients) obj; // Casting obj into Clients
			return this.clientName.equalsIgnoreCase(client.clientName)
					&& this.phoneNumber.equalsIgnoreCase(client.phoneNumber)
					&& this.email.equalsIgnoreCase(client.email);
		}
	}

	/**
	 * Leasing function : useful for (9)
	 * 
	 * @param item Items that the user want to lease
	 */
	public void leasing(Items item) {
		boolean display = true;
		if (!item.getIsLeased()) {
			// Each client max lease 10 items
			if (this.getLeaseCount() < 10) {
				leasedItem[this.leaseCount] = item;
				item.setIsLeased(true);
				this.leaseCount++;
			} else {
				System.out.println("You have exceeded the maximum item lease amount");
				display = false;
			}
		} else {
			System.out.println("The Item has been leased, please select another one");
			display = false;
		}
		if (display) {
			System.out.println("The items that you leased is as follows: ");
			for (Items leased : this.getLeasedItem()) {
				if (leased == null)
					continue;
				else {
					System.out.println(leased);
					System.out.println();
				}
			}
		}
	}

	/**
	 * Return function : useful for (10)
	 * 
	 * @param item Items that user want to return
	 */
	public void returning(Items item) {
		boolean validReturnID = false;
		if (this.getLeaseCount() == 0) {
			System.out.println("You cannot return anything from an Empty leased list");
		} else if (item.getIsLeased()) {
			validReturnID = true;
			for (int i = 0; i < this.getLeaseCount(); i++) {
				if (leasedItem[i].getItemID() == item.getItemID()) {
					// Shift all subsequence element one position to the left
					for (int j = i; j < this.getLeaseCount() - 1; j++) {
						leasedItem[j] = leasedItem[j + 1];
					}
					// Set the last element to null since it's now duplicated
					leasedItem[this.leaseCount - 1] = null;
					// Decrease ItemCount by 1 since we remove 1 item
					this.leaseCount--;
					item.setIsLeased(false);
					System.out.println("The Item with ID " + item.getItemID()
							+ " has been successfully removed from leased list.");
					break;
				}
			}
		}
		if (!validReturnID)
			System.out.println("Please enter a valid leased Item ID");
	}

	// -------------------Methods used for Menu----------------------//
	/**
	 * Display all clients info in the Client array
	 * 
	 * @param clientsArr Clients[]
	 */
	public static void displayAllClients(Clients[] clientsArr) {
		boolean clientArrIsEmpty = true;
		for (Clients client : clientsArr) {
			if (client != null) {
				System.out.println(client);
				System.out.println();
				clientArrIsEmpty = false;
			}
		}
		if (clientArrIsEmpty) {
			System.out.println("The Item List is empty");
		}
	}

	/**
	 * Check if Client Array is empty
	 * 
	 * @return boolean
	 */
	public static boolean checkClientArrEmpty() {
		boolean isEmpty = true;
		for (Clients client : Library.clientsArr) {
			if (client != null) {
				isEmpty = false;
				break;
			}
		}
		return isEmpty;
	}

	/**
	 * Check if the entered clientID could be found
	 * 
	 * @param inID int (the ID we want to search)
	 * @return boolean
	 */
	public static boolean findClientID(int inID) {
		boolean findItem = false;
		for (Clients client : Library.clientsArr) {
			if (client != null) {
				if (client.getClientID() == inID) {
					findItem = true;
					break;
				}
			}
		}
		return findItem;
	}

	/**
	 * 6.Add a client in the client array
	 * 
	 * @param keyin Scanner
	 */
	public static void addClient(Scanner keyin) {
		if (Library.clientsArr[Library.clientsArr.length - 1] != null) {
			System.out.println("Sorry, the Client database is full");
		} else {
			System.out.println("Please enter the Client's Name: ");
			String clientName = keyin.next();

			System.out.println("Please enter the Client's Phone Number: ");
			String clientPhoneNum = keyin.next();

			System.out.println("Please enter the Client's Email: ");
			String clientEmail = keyin.next();

			Library.clientsArr[Clients.getClientCount()] = new Clients(clientName, clientPhoneNum, clientEmail);

		}
		System.out.println("The Client list is displayed as follows: ");
		displayAllClients(Library.clientsArr);
	}

	/**
	 * 7.Edit a client
	 * 
	 * @param keyin Scanner
	 */
	public static void editClient(Scanner keyin) {
		System.out.println("Please enter the client ID that you want to change:");
		Library.checkValidInt(keyin);
		int inID = keyin.nextInt();
		// 7.1: Search client array to check if valid ID entered
		boolean findclientID = false;
		boolean isEmpty = true;
		for (Clients client : Library.clientsArr) {
			if (client != null) {
				isEmpty = false;
				if (client.getClientID() == inID) {
					findclientID = true;
					break;
				}
			}
		}
		if (isEmpty) {
			System.out.println("The Client list is Empty");
		} else if (findclientID == false) {
			System.out.println("Client with ID " + inID + " is not found");
		} else {
			// 7.3: Prompt user which aspect to be edited
			boolean collecting = true;
			while (collecting) {
				System.out.println(
						"Please enter ONE option number that you want to change (1.ClientName, 2.PhoneNumber, or 3.Email): ");
				Library.checkValidInt(keyin);
				int opt = keyin.nextInt();
				if (opt < 1 || opt > 3) {
					continue;
				} else if (opt == 1) {
					System.out.println("Please enter the new ClientName:");
					String newName = keyin.next();
					for (Clients client : Library.clientsArr) {
						if (client == null)
							continue;
						if (client.getClientID() == inID)
							client.setClientName(newName);
					}
				} else if (opt == 2) {
					System.out.println("Please enter the new PhoneNumber:");
					String newPhoneNumber = keyin.next();
					for (Clients client : Library.clientsArr) {
						if (client == null)
							continue;
						if (client.getClientID() == inID)
							client.setPhoneNumber(newPhoneNumber);
					}
				} else if (opt == 3) {
					System.out.println("Please enter the new Email:");
					String newEmail = keyin.next();
					for (Clients client : Library.clientsArr) {
						if (client == null)
							continue;
						if (client.getClientID() == inID)
							client.setEmail(newEmail);
					}
				}
				System.out.println("Do you want to continue changing the info of client " + inID
						+ "? (enter YES to continue OR NO to quit)");
				String str = keyin.next();
				if (str.equalsIgnoreCase("YES")) {
					continue;
				} else {
					collecting = false;
				}
			}
		}
		System.out.println("The Client list is displayed as follows: ");
		displayAllClients(Library.clientsArr);
	}

	/**
	 * 8.Delete a client
	 * 
	 * @param keyin Scanner
	 */
	public static void deleteClient(Scanner keyin) {
		System.out.println("Please enter the client ID that you want to delete:");
		Library.checkValidInt(keyin);
		int inID = keyin.nextInt();
		if (checkClientArrEmpty()) {
			System.out.println("You cannot delete any entity from an EMPTY array");
		} else if (findClientID(inID)) {
			for (int i = 0; i < Clients.getClientCount(); i++) {
				if (Library.clientsArr[i].getClientID() == inID) {
					// Shift all subsequence client one position to the left
					for (int j = i; j < Clients.getClientCount() - 1; j++) {
						Library.clientsArr[j] = Library.clientsArr[j + 1];
					}
					// Set the last element to null since it's now duplicated
					Library.clientsArr[Clients.getClientCount() - 1] = null;
					System.out
							.println("The client with ID " + inID + " has been successfully removed from client list.");
					break;
				}
			}
		} else {
			System.out.println("Client with ID " + inID + " is not found");
		}

		/*
		 * Once the client is removed, the items he/she leased cannot be returned since
		 * when return an item clientID is needed.
		 */
		System.out.println("The Client list is displayed as follows: ");
		displayAllClients(Library.clientsArr);
	}

	/**
	 * 9.Lease an item to a client
	 * 
	 * @param keyin Scanner
	 */
	public static void leaseItemToClient(Scanner keyin) {
		System.out.println("Please enter the Client ID");
		Library.checkValidInt(keyin);
		int inID = keyin.nextInt();
		boolean clientFound = false, itemFound = false;
		for (Clients client : Library.clientsArr) {
			if (client == null) {
				continue;
			} else if (client.getClientID() == inID) {
				clientFound = true;
				System.out.println("Please enter the Item ID that you want to lease:");
				Library.checkValidInt(keyin);
				int inItemID = keyin.nextInt();
				for (Items item : Library.itemsArr) {
					if (item == null) {
						continue;
					} else if (item.getItemID() == inItemID) {
						itemFound = true;
						client.leasing(item);
					}
				}
				if (!itemFound)
					System.out.println("Item with ID " + inItemID + " is not found");
			}
		}
		if (!clientFound)
			System.out.println("Client with ID " + inID + " is not found");
	}

	/**
	 * 10.Return an item from a client
	 * 
	 * @param keyin Scanner
	 */
	public static void returnAnItemFromCLient(Scanner keyin) {
		System.out.println("Please enter the Client ID");
		Library.checkValidInt(keyin);
		int inID = keyin.nextInt();
		boolean clientFound = false, itemFound = false;
		for (Clients client : Library.clientsArr) {
			if (client == null) {
				continue;
			} else if (client.getClientID() == inID) {
				clientFound = true;
				System.out.println("Please enter the Item ID that you want to return:");
				Library.checkValidInt(keyin);
				int inItemID = keyin.nextInt();
				for (Items item : Library.itemsArr) {
					if (item == null) {
						continue;
					} else if (item.getItemID() == inItemID) {
						itemFound = true;
						client.returning(item);
						break;
					}
				}
				if (!itemFound)
					System.out.println("Item with ID " + inItemID + " is not found");
			}
		}
		if (!clientFound)
			System.out.println("Client with ID " + inID + " is not found");
	}

	/**
	 * 11.Show all items leased by a client
	 * 
	 * @param keyin Scanner
	 */
	public static void showAllLeasedItemByAClient(Scanner keyin) {
		System.out.println("Please enter the clientID that you want to display his/her Leased Item List");
		Library.checkValidInt(keyin);
		int inID = keyin.nextInt();
		boolean validID = false;
		for (Clients client : Library.clientsArr) {
			if (client == null) {
				continue;
			} else if (client.getClientID() == inID) {
				validID = true;
				System.out.println("Client with ID " + inID + " Leased Item List is dispayed as follows:");
				boolean isEmpty = true;
				for (Items leased : client.getLeasedItem()) {
					if (leased == null)
						continue;
					else {
						System.out.println(leased);
						isEmpty = false;
						System.out.println();
					}
				}
				if (isEmpty)
					System.out.println("Sorry, the leased list is empty");
			}
		}
		if (!validID)
			System.out.println("Please enter a Valid client ID");
	}

	/**
	 * 12.Show all leased items (by all clients)
	 */
	public static void showAllLeasedItemsByAllClients() {
		for (Clients client : Library.clientsArr) {
			if (client == null) {
				continue;
			} else {
				boolean isEmpty = true;
				System.out.println("The client with ID " + client.getClientID() + " 's Leased Items are as follows: ");
				for (Items leased : client.getLeasedItem()) {
					if (leased == null)
						continue;
					else {
						System.out.println(leased);
						isEmpty = false;
						System.out.println();
					}
				}
				if (isEmpty)
					System.out.println(
							"Sorry,The client with ID " + client.getClientID() + " 's Leased Item List is EMPTY");
			}
		}
	}
}
