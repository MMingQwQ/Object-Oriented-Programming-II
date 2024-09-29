//-------------------------------------------------------------------- 
//Assignment 3
//Written by: Mingming Zhang 
//
//For COMP 249 Section (S) – Winter 2024
//--------------------------------------------------------------------

import java.io.*;
import java.util.ArrayList;

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
public class SinglyLinkedList<E extends Comparable<E>> {
	// <E extends Comparable<E>> in order to use compareTo method
	private class SNode {
		private SNode next;
		private E data; // in this case we have E(String) as data

		/**
		 * default constructor
		 */
		public SNode() {
			super();
		}

		/**
		 * parameterized constructor
		 * 
		 * @param data E
		 * @param n    next
		 */
		public SNode(E data, SNode n) {
			this();
			this.data = data;
			this.next = n;
		}

		/**
		 * delegating constructor
		 * 
		 * @param data E
		 */
		public SNode(E data) {
			this(data, null);
		}
	} // end of inner SNode class

	// Class SinglyLinkedList
	// Attributes of SinglyLinkedList
	private SNode head;
	private SNode tail;
	private int size; // the num of SNodes stored in the linked list

	// ------------- Constructors for SLL ----------//
	/**
	 * default constructor
	 */
	public SinglyLinkedList() {
		super();
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * parameterized constructor
	 * 
	 * @param head SNode
	 * @param tail SNode
	 * @param size int
	 */
	public SinglyLinkedList(SNode head, SNode tail, int size) {
		this.head = head;
		this.tail = tail;
		this.size = size;
	}

	// ------------- Getters and Setters for SLL ----------//
	/**
	 * @return the head
	 */
	public SNode getHead() {
		return head;
	}

	/**
	 * @param head the head to set
	 */
	public void setHead(SNode head) {
		this.head = head;
	}

	/**
	 * @return the tail
	 */
	public SNode getTail() {
		return tail;
	}

	/**
	 * @param tail the tail to set
	 */
	public void setTail(SNode tail) {
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

	// ------------- Necessary methods in SLL ----------//
	/**
	 * Print list in a 4-column format with index start from 1
	 */
	public void printList() {
		SNode current = head;
		int index = 1; // start from index 1
		while (current != null) {
			System.out.printf("%2d: %-20s", index, current.data); 
			if (index % 4 == 0) { // Every fourth word, move to the next line
				System.out.println();
			}
			current = current.next;
			index++; 
		}
		if ((index - 1) % 4 != 0) { // If the last line isn't complete, add a newline
			System.out.println();
		}
	}

	/**
	 * Insert words. They will be placed in order according to their ASCII values.
	 * 
	 * @param data E
	 */
	public void insertSorted(E data) {
		SNode newNode = new SNode(data);

		// Special case for the head end
		if (head == null || head.data.compareTo(data) > 0) {
			newNode.next = head;
			head = newNode;
			size++;
		} else {
			// Locate the node before the node of insertion
			SNode current = head;
			SNode prev = null;
			while (current != null && current.data.compareTo(data) < 0) {
				prev = current;
				current = current.next;
			}

			// Insert the new node
			if (prev != null) {
				newNode.next = prev.next;
				prev.next = newNode;
			}
			size++;
		}
	}

	/**
	 * Method to check if words SLL contains the specific word
	 * 
	 * @param word E(String)
	 * @return boolean
	 */
	public boolean contains(E word) {
		SNode current = head;
		while (current != null) {
			if (current.data.equals(word)) {
				return true; // The word is found in the list.
			}
			current = current.next;
		}
		return false; // The word is not found in the list.
	}

	/**
	 * Method to remove the specific word in the SLL
	 * 
	 * @param wordToRemove E(String)
	 * @return boolean
	 */
	public boolean remove(E wordToRemove) {
		// TODO Auto-generated method stub
		SNode current = head;
		SNode prev = null;
		while (current != null) {
			if (current.data.equals(wordToRemove)) {
				if (prev == null) {
					// The word to remove is at the head of the list.
					head = head.next;
					if (head == null) { // If the list is empty, tail should be null.
						tail = null;
					}
				} else {
					// The word to remove is not at the head.
					prev.next = current.next;
					if (current.next == null) { // If the removed node is tail, update the tail.
						tail = prev;
					}
				}
				return true; // Word is removed.
			}
			prev = current;
			current = current.next;
		}
		return false; // Word to remove not found.
	}

	/**
	 * Method to get words start with the given letter as a ArrayList
	 * 
	 * @param letter search for starting letter
	 * @return ArrayList
	 */
	public ArrayList<String> getWordsSWSLetter(char letter) {
		ArrayList<String> matchingWords = new ArrayList<>();
		SNode current = head;
		String targetLetter = String.valueOf(letter); // Casting char letter to string

		while (current != null) {
			String currentWord = current.data.toString();
			// startsWith(String prefix) ->that's why we need to cast char to string
			if (currentWord.startsWith(targetLetter)) {
				matchingWords.add(currentWord);
			}
			current = current.next;
		}
		return matchingWords;
	}

	/**
	 * Write all words in a SLL to file
	 * 
	 * @param writer PrintWriter
	 */
	public void writeWords(PrintWriter writer) {
		SNode current = head;
		while (current != null) {
//			writer.println("TEST WORD");
			writer.println(current.data);
			current = current.next;
		}
	}

}
