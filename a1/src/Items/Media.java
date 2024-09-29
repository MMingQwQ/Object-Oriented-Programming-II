// -------------------------------------------------------------------- 
// Assignment 1
// Question: Part II
// Written by: Mingming Zhang 
//
// For COMP 249 Section (S) – Winter 2024
// --------------------------------------------------------------------
package Items;

/**
 * This is Media class (child class of Items class)
 * 
 * @author Mingming Zhang
 * @version created on 2024/02/26
 */
public class Media extends Items {
	// Make attributes private to prevent privacy leak
	private String type;
	// Media IDs start with “M” (e.g., M1, M2...)
	private String mediaID; // can apply getter but not setter since it's unique

	/**
	 * Default constructor
	 */
	public Media() {
		super();
		type = null;
		mediaID = "M" + itemID;

	}

	/**
	 * Parameterized constructor
	 * 
	 * @param itemName        String
	 * @param author          String
	 * @param publicationYear int
	 * @param type            String
	 */
	public Media(String itemName, String author, int publicationYear, String type) {
		super(itemName, author, publicationYear);
		this.type = type;
		mediaID = "M" + itemID;
	}

	/**
	 * Copy constructor (with the exception of the ID)
	 * 
	 * @param anotherMedia Media
	 */
	public Media(Media anotherMedia) {
		super(anotherMedia);
		this.type = anotherMedia.type;
		mediaID = "M" + itemID;
	}

	// Accessors
	/**
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return String
	 */
	public String getMediaID() {
		return mediaID;
	}

	// Mutators
	/**
	 * @param type String
	 */
	public void setType(String type) {
		this.type = type;
	}

	// toString() method : return a clear description and information of each object
	@Override
	public String toString() {
		return super.toString() + "\nMedia's ID is: " + this.mediaID + "\nMedia's type is: " + this.type;
	}

	// equals() method
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			Media media = (Media) obj;// Casting obj into Media
			return this.type.equalsIgnoreCase(media.type);
		} else
			return false;
	}

}
