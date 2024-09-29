//-------------------------------------------------------------------- 
//Assignment 3
//Written by: Mingming Zhang 
//
//For COMP 249 Section (S) – Winter 2024
//--------------------------------------------------------------------

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

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
public class DoublyLinkedList<E extends Vocab> { // make sure E is Vocab class or its subclass
	private class DNode {
		E data;
		DNode next;
		DNode prev;

		// ------------- Constructors for DNode ----------//
		/**
		 * default constructor
		 */
		public DNode() {
			super();
		}

		/**
		 * delegating constructor
		 * 
		 * @param data E
		 */
		DNode(E data) {
			this(data, null, null);
		}

		/**
		 * parameterized constructor
		 * 
		 * @param data E
		 * @param next DNode
		 * @param prev DNode
		 */
		public DNode(E data, DNode next, DNode prev) {
			this();
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		// ---- DNode as an inner class don't need getter and setters---//

	}// end of inner DNode class

	// Class DoublyLinkedList
	// Attributes of DoublyLinkedList
	private DNode head;
	private DNode tail;
	private int size;

	// ------------- Constructors for DLL ----------//
	/**
	 * default constructor
	 */
	public DoublyLinkedList() {
		super();
		head = new DNode(null); // Dummy head node.
		tail = new DNode(null); // Dummy tail node.
		head.next = tail; // Initially, the list is empty, so head points directly to tail.
		tail.prev = head; // And tail's previous is the head.
		size = 0; // The size of the list is initially 0.
	}

	/**
	 * parameterized constructor
	 * 
	 * @param head DNode
	 * @param tail DNode
	 * @param size int
	 */
	public DoublyLinkedList(DoublyLinkedList<E>.DNode head, DoublyLinkedList<E>.DNode tail, int size) {
		super();
		this.head = head;
		this.tail = tail;
		this.size = size;
	}

	// ------------- Getters and Setters for DLL ----------//
	/**
	 * @return the head
	 */
	public DNode getHead() {
		return head;
	}

	/**
	 * @param head the head to set
	 */
	public void setHead(DNode head) {
		this.head = head;
	}

	/**
	 * @return the tail
	 */
	public DNode getTail() {
		return tail;
	}

	/**
	 * @param tail the tail to set
	 */
	public void setTail(DNode tail) {
		this.tail = tail;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	// ------------- Necessary methods in DLL ----------//
	/**
	 * Method to insert a new node at the end of the list.
	 * 
	 * @param data E
	 */
	public void insertAtEnd(E data) {
		DNode newNode = new DNode(data);
		DNode last = tail.prev; // Find the last real node.

		// Link the new node with the last real node and the dummy tail.
		last.next = newNode; 
		newNode.prev = last; 
		newNode.next = tail; 
		tail.prev = newNode; 

		size++;
	}

	/**
	 * Check if the index is out of bounds we start index start from 1
	 * 
	 * @param index int
	 */
	private void validate(int index) {
		if (index <= 0 || index > size) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
	}

	/**
	 * Method to insert a new node before the node at the given index
	 * 
	 * @param index int
	 * @param data  E
	 */
	public void insertBefore(int index, E data) {
		validate(index);

		DNode target = getDNode(index);
		DNode newNode = new DNode(data);

		// Insert the new node before the target node
		newNode.prev = target.prev;
		newNode.next = target;
		target.prev.next = newNode;
		target.prev = newNode;

		size++;
	}

	/**
	 * Method to insert a new node after the node at the given index
	 * 
	 * @param index int
	 * @param data  E
	 */
	public void insertAfter(int index, E data) {
		validate(index);

		DNode target = getDNode(index);
		DNode newNode = new DNode(data);

		// Insert the new node after the target node
		newNode.next = target.next;
		newNode.prev = target;
		target.next.prev = newNode;
		target.next = newNode;

		size++;
	}

	/**
	 * Method to get the DNode at the specific index.
	 * 
	 * @param index int starting from 1
	 * @return DNode
	 */
	public DNode getDNode(int index) {
		validate(index);

		DNode current = head.next; // Start from the first real node.
		for (int i = 1; i < index; i++) { // Loop to the desired index.
			current = current.next;
		}
		return current; // Return the node at the index.
	}

	/**
	 * Method to print the topic list
	 */
	public void printTopics() {
		int index = 1;
		DNode current = head.next;
		while (current != tail) {
			// current.data is Vocab object
			// but we need to cast it in order to use getTopic method.
			System.out.println(index + "  " + ((Vocab) current.data).getTopic());
			index++;
			current = current.next;
		}
	}

	/**
	 * Method to find the topic with corresponding index
	 * 
	 * @param index int
	 * @return E data part
	 */
	public E getTopicAtIndex(int index) {
		validate(index);

		DNode current = head.next;
		for (int i = 1; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}

	/**
	 * Remove the chosen topic by index
	 * 
	 * @param index int
	 */
	public void deleteAt(int index) {
		validate(index);

		DNode current = getDNode(index);
		if (current != null) {
			// If the node to be deleted has a previous node, link it to the next node
			if (current.prev != null) {
				current.prev.next = current.next;
			}
			// If the node to be deleted has a next node, link it to the previous node
			if (current.next != null) {
				current.next.prev = current.prev;
			}
			size--;
		}
	}

	/**
	 * Search the words in all topics
	 * 
	 * @param searchWord E
	 */
	public void searchWordInAllTopics(String searchWord) {
		boolean isWordFound = false;
		DNode current = head.next;

		while (current != tail) { // Loop until the dummy tail node
			E data = current.data; // Get the data object from the current node

			// Use instanceof to check if the data is a Vocab object
			if (data instanceof Vocab) {
				Vocab currentVocab = (Vocab) data; // Cast the data to Vocab
				if (currentVocab.getWords().contains(searchWord)) {
					System.out.println("Found in topic: " + currentVocab.getTopic());
					isWordFound = true;
				}
			}
			current = current.next;
		}

		if (!isWordFound) {
			System.out.println("The word '" + searchWord + "' was not found in any topic.");
		}
	}

	/**
	 * Method to show all words starting with a given letter
	 * 
	 * @param letter char
	 */
	public void showWordsSWSLetter(char letter) {
		ArrayList<String> extractedWords = new ArrayList<>();

		DNode current = head.next; // Skip dummy head
		while (current != tail) { // Stop before dummy tail
			if (current.data != null) {
				extractedWords.addAll(current.data.getWords().getWordsSWSLetter(letter));
				/*
				 * getWordsSWSLetter is defined in SLL. current.data.getWords() -> is an object
				 * of SLL. current.data.getWords().getWordsStartingWith(letter) will return an
				 * ArrayList with given starting letter. We use addAll() to add all relevant
				 * words across different Vocab instances in the DoublyLinkedList.
				 */
			}
			current = current.next;
		}
		// Then sort the ArrayList (asked for prof permission).
		Collections.sort(extractedWords);

		System.out.println("Words starting with '" + letter + "':");
		if (extractedWords.isEmpty()) {
			System.out.println("No words found.");
		} else {
			for (String word : extractedWords) {
				System.out.println(word);
			}
		}
	}

	/**
	 * Save the topics and corresponding words to file in the same format as the
	 * input file with the support methods: writeWords from SLL class and writeVocab
	 * from Vocab list
	 * 
	 * @param filename String
	 * @throws IOException error when opening file
	 */
	public void saveDLLToFile(String filename) throws IOException {
		PrintWriter writer = new PrintWriter(new FileWriter(filename), true);
		DNode current = head.next;
		while (current != tail) {
			if (current.data != null) {
				current.data.writeVocab(writer);
			}
			current = current.next;
		}
	}
}
