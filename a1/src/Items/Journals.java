// -------------------------------------------------------------------- 
// Assignment 1
// Question: Part II
// Written by: Mingming Zhang 
//
// For COMP 249 Section (S) – Winter 2024
// --------------------------------------------------------------------

package Items;

/**
 * This is Journals class (child class of Items class)
 * 
 * @author Mingming Zhang
 * @version created on 2024/02/26
 */
public class Journals extends Items {
	// Make attributes private to prevent privacy leak
	private int volumeNumber;
	// Journals IDs start with “J” (e.g., J1, J2...)
	private String journalID; // can apply getter but not setter since it's unique

	/**
	 * Default constructor
	 */
	public Journals() {
		super();
		volumeNumber = 0;
		journalID = "J" + itemID;

	}

	/**
	 * Parameterized constructor
	 * 
	 * @param itemName        String
	 * @param author          String
	 * @param publicationYear String
	 * @param volumeNumber    int
	 */
	public Journals(String itemName, String author, int publicationYear, int volumeNumber) {
		super(itemName, author, publicationYear);
		this.volumeNumber = volumeNumber;
		journalID = "J" + itemID;
	}

	/**
	 * Copy constructor (with the exception of the ID)
	 * 
	 * @param anotherJournals Journals
	 */
	public Journals(Journals anotherJournals) {
		super(anotherJournals);
		this.volumeNumber = anotherJournals.volumeNumber;
		journalID = "J" + itemID;
	}

	// Accessors
	/**
	 * @return int
	 */
	public int getVolumeNumber() {
		return volumeNumber;
	}

	/**
	 * @return String
	 */
	public String getJournalID() {
		return journalID;
	}

	// Mutators
	/**
	 * @param volumeNumber int
	 */
	public void setVolumeNumber(int volumeNumber) {
		this.volumeNumber = volumeNumber;
	}

	// toString() method : return a clear description and information of each object
	@Override
	public String toString() {
		return super.toString() + "\nJournal's ID is: " + this.journalID + "\nJournal's volume number is: "
				+ this.volumeNumber;
	}

	// equals() method
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			Journals journal = (Journals) obj;// Casting obj into Journals
			return this.volumeNumber == journal.volumeNumber;
		} else
			return false;
	}

}
