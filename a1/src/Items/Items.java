// -------------------------------------------------------------------- 
// Assignment 1
// Question: Part II
// Written by: Mingming Zhang 
//
// For COMP 249 Section (S) – Winter 2024
// --------------------------------------------------------------------

package Items;

/**
 * This is Items class (abstract parent class)
 * 
 * @author Mingming Zhang
 * @version created on 2024/02/26
 */
public abstract class Items {
	// Make Items class abstract to make sure it cannot instantiate
	// Make parent attributes protected to make most ease of use/access
	protected int itemID; // can apply getter but not setter since it's unique
	protected String itemName;
	protected String author;
	protected int publicationYear;
	protected boolean isLeased;

	// Not necessary for child class so make it private to prevent privacy leak
	private static int itemCount = 0;

	/**
	 * Default constructor
	 */
	public Items() {
		this.isLeased = false;
		itemCount++;
		itemID = itemCount;
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param itemName        String
	 * @param author          String
	 * @param publicationYear int
	 */
	public Items(String itemName, String author, int publicationYear) {
		this.itemName = itemName;
		this.author = author;
		this.publicationYear = publicationYear;
		this.isLeased = false;
		itemCount++;
		itemID = itemCount;
	}

	/**
	 * Copy constructor (with the exception of the ID)
	 * 
	 * @param anotherItem Items
	 */
	public Items(Items anotherItem) {
		this.itemName = anotherItem.itemName;
		this.author = anotherItem.author;
		this.publicationYear = anotherItem.publicationYear;
		this.isLeased = false;
		itemCount++;
		itemID = itemCount;
	}

	// Accessors
	/**
	 * @return int
	 */
	public int getItemID() {
		return itemID;
	}

	/**
	 * @return String
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @return String
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return int
	 */
	public int getPublicationYear() {
		return publicationYear;
	}

	/**
	 * @return boolean
	 */
	public boolean getIsLeased() {
		return this.isLeased;
	}

	/**
	 * @return int
	 */
	public static int getItemCount() {
		return itemCount;
	}

	// Mutators
	/**
	 * @param itemName String
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * @param author String
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @param publicationYear int
	 */
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	/**
	 * @param status boolean
	 */
	public void setIsLeased(boolean status) {
		this.isLeased = status;
	}

	/**
	 * To increase itemCount by 1
	 */
	public static void increaseItemCount() {
		Items.itemCount++;
	}

	/**
	 * To decrease itemCount by 1
	 */
	public static void decreaseItemCount() {
		Items.itemCount--;
	}

	/**
	 * Reset Item Array since when creating the child object, Item count will
	 * increase
	 */
	public static void resetItemCount() {
		Items.itemCount = 0;
	}

	// toString() method : return a clear description and information of each object
	@Override
	public String toString() {
		return "\nThe Item's ID is: " + this.itemID + "\nItem's name is: " + this.itemName
				+ "\nItem's author is: " + this.author + "\nItem's publication year is: "
				+ this.publicationYear;
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
			//System.out.println("Not the same class");
			return false;
		} else {
			Items item = (Items) obj; // Casting obj into Items
			return this.itemName.equalsIgnoreCase(item.itemName) && this.author.equalsIgnoreCase(item.author)
					&& this.publicationYear == item.publicationYear;
		}
	}
}
