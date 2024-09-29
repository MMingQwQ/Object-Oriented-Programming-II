import java.io.PrintWriter;

//-------------------------------------------------------------------- 
//Assignment 3
//Written by: Mingming Zhang 
//
//For COMP 249 Section (S) – Winter 2024
//--------------------------------------------------------------------

/**
 * Name: Mingming Zhang
 * 
 * ID: 40258080
 * 
 * COMP249 Section (S)
 * 
 * Assignment # 3
 * 
 * Due Date: 04/15/2024
 * 
 * @author Mingming Zhang
 * @version 04/09/2024
 */
public class Vocab {
	private String topic;
	private SinglyLinkedList<String> words;

	// ----------- constructor-------------//
	/**
	 * Default constructor
	 */
	public Vocab() {
		super();
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param topic String
	 * @param words String
	 */
	public Vocab(String topic, SinglyLinkedList<String> words) {
		super();
		this.topic = topic;
		this.words = words;
	}

	/**
	 * Delegating constructor
	 * 
	 * @param topic String
	 */
	public Vocab(String topic) {
		this.topic = topic;
		this.words = new SinglyLinkedList<>();
	}

	// ----------- toString()-------------//
	@Override
	public String toString() {
		return "Vocab [topic=" + topic + ", words=" + words + "]";
	}

	// ----------- Getters & Setters-------------//
	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @return the words
	 */
	public SinglyLinkedList<String> getWords() {
		return words;
	}

	/**
	 * @param words the words to set
	 */
	public void setWords(SinglyLinkedList<String> words) {
		this.words = words;
	}

	/**
	 * To print the topic and words under the topic
	 */
	public void printVocab() {
		// Print the topic
		System.out.println("Topic: " + this.topic);
		// Print the words
		this.words.printList();
	}

	/**
	 * Write topics and words from a Vocab to file with the support method:
	 * writeAllToPrintWriter from SLL
	 * 
	 * @param writer PrintWriter
	 */
	public void writeVocab(PrintWriter writer) {
		// TODO Auto-generated method stub
//		writer.println("TEST");
		writer.println("#" + topic); // Write the topic name prefixed with '#'
		words.writeWords(writer);
		writer.println(); // Adding a blank line for separation between topics

	}
}
