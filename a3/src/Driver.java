//-------------------------------------------------------------------- 
//Assignment 3
//Written by: Mingming Zhang 
//
//For COMP 249 Section (S) – Winter 2024
//--------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

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
public class Driver {
	/**
	 * Main method designed to implements all methods listed in the outline with a
	 * dynamic main menu
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args) {
		Scanner keyin = new Scanner(System.in);
		// Display welcome message
		welcome();
//		String inputFile = "A3_input_file.txt";
//		String inputFile = "test.txt";
		DoublyLinkedList<Vocab> vocabList = null;
		int option;
		do {
			displayControlCenter();
			option = validateOptionInput(keyin);

			switch (option) {
			case 1:
				if (vocabList == null) {
					System.out.println("Please load from a file firstly.");
					break;
				}
				browseATopic(vocabList, keyin);
				break;

			case 2:
				if (vocabList == null) {
					System.out.println("Please load from a file firstly.");
					break;
				}
				insertATopicBefore(vocabList, keyin);
				break;

			case 3:
				if (vocabList == null) {
					System.out.println("Please load from a file firstly.");
					break;
				}
				insertATopicAfter(vocabList, keyin);
				break;

			case 4:
				if (vocabList == null) {
					System.out.println("Please load from a file firstly.");
					break;
				}
				removeATopic(vocabList, keyin);
				break;

			case 5:
				if (vocabList == null) {
					System.out.println("Please load from a file firstly.");
					break;
				}
				modifyATopic(vocabList, keyin);
				break;

			case 6:
				if (vocabList == null) {
					System.out.println("Please load from a file firstly.");
					break;
				}
				searchTopicsForAWord(vocabList, keyin);
				break;

			case 7:
				vocabList = loadFromFile(keyin);
				break;
			case 8:
				if (vocabList == null) {
					System.out.println("Please load from a file firstly.");
					break;
				}
				showAllWordsStrartWithAGivenLetter(vocabList, keyin);
				break;
			case 9:
				if (vocabList == null) {
					System.out.println("Please load from a file firstly.");
					break;
				}
				saveToFile(vocabList, keyin);
				break;
			case 0:
				// Exit
				break;
			}
		} while (option != 0);

		keyin.close();
		// Display closing message
		closing();
	}

	/**
	 * isReadableFile method help to test if the file is readable
	 * 
	 * @param fileName String
	 * @return boolean
	 */
	public static boolean isReadableFile(String fileName) {
		try {
			if (fileName == null) {
				throw new IllegalAccessException("File name undefined");
			}
			File file = new File(fileName);
			if (!file.exists()) {
				throw (new FileNotFoundException("No such file:" + fileName));
			}
			if (!file.canRead()) {
				throw (new IOException("File is not readable : " + fileName));
			}
			return true;
		} catch (FileNotFoundException fnfe) {
			System.out.println("I/O error : File not found : " + fileName);
			return false;
		} catch (IOException ioe) {
			System.out.println("I/O error: " + ioe.getMessage());
			return false;
		} catch (IllegalAccessException iae) {
			System.out.println(iae.getMessage());
			return false;
		}
	}

	/**
	 * isWritableFile method help to test if file is writable
	 * 
	 * @param fileName String
	 * @return boolean
	 */
	public static boolean isWritableFile(String fileName) {
		try {
			if (fileName == null) {
				throw new IllegalAccessException("File name undefined");
			}
			if (fileName.isEmpty()) {
				throw new IllegalAccessException("File name must have at least one character");
			}
			File file = new File(fileName);
			/*
			 * public boolean canWrite() true if and only if the file system actually
			 * contains a file denoted by this abstract pathname and the application is
			 * allowed to write to the file; false otherwise.
			 */
			if (file.exists() && !file.canWrite()) {
				throw (new IOException("File is not writeable : " + fileName));
			}
			return true;
		} catch (IOException ioe) {
			System.out.println("I/O error: " + ioe.getMessage());
			return false;
		} catch (IllegalAccessException iae) {
			System.out.println(iae.getMessage());
			return false;
		}
	}

	/**
	 * Option 7: load the topics and words from a file
	 * 
	 * @param keyin Scanner
	 * @return DoublyLinkedList
	 */
	public static DoublyLinkedList<Vocab> loadFromFile(Scanner keyin) {
		System.out.println("Enter the name of the input file: ");
		String filename = keyin.nextLine(); // consume the newLine
		filename = keyin.nextLine();

		Scanner fileScanner = null;
		Vocab currentVocab = null;
		DoublyLinkedList<Vocab> vocabList = new DoublyLinkedList<>();
		try {
			// check if readable
			if (isReadableFile(filename)) {
				fileScanner = new Scanner(new FileInputStream(filename));

				String line = null;
				String topic = null; // topic start with'#'
				while (fileScanner.hasNextLine()) {
					line = fileScanner.nextLine();
					if (line.startsWith("#")) {
//						//For test purpose
//						if (currentVocab != null) { 
//							// Print the current Vocab's words before starting a new topic
//							currentVocab.printVocab();
//						}
						topic = line.substring(1);
						currentVocab = new Vocab(topic);
						vocabList.insertAtEnd(currentVocab);
						// System.out.println(topic);
					} else if (!line.isEmpty()) {
						// Insert the word into the current Vocab's SinglyLinkedList of words
						currentVocab.getWords().insertSorted(line);
					}
				}

			} else {
				System.out.println("Terminating the program...Please try again :)");
				System.exit(0);
			}
//			//For test purpose
//			if (currentVocab != null) {
//				// Print the last Vocab's words after reading the last line of the file
//				currentVocab.printVocab();
//			}

			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File was not found");
			System.out.println("or could not be opened.");
			e.printStackTrace();
		}
		System.out.println("Done loading");
		return vocabList;
	} // end of loadFromFile method

	/**
	 * Method to display the Pick a topic Menu
	 * 
	 * @param vocabList DoublyLinkedList
	 */
	public static void displayPickATopicMenu(DoublyLinkedList<Vocab> vocabList) {
		System.out.println("------------------------------");
		System.out.println("          Pick a topic        ");
		System.out.println("------------------------------");
		vocabList.printTopics();
		System.out.println("0  Exit");
		System.out.println("------------------------------");
		System.out.print("Enter Your Choice: ");
	}

	/**
	 * Option 1: browseATopic,load the topics and let user to choose interested
	 * topic and display the words under the topic, display the pick-a-topic
	 * sub-menu until user enter 0. Enter 0 will go back to main menu topic name:
	 * not sorted words: sorted
	 * 
	 * @param vocabList DoublyLinkedList
	 * @param keyin     Scanner
	 */
	public static void browseATopic(DoublyLinkedList<Vocab> vocabList, Scanner keyin) {
		int choice;

		do {
			displayPickATopicMenu(vocabList);

			choice = validateOptionInput(keyin);
			if (choice > 0 && choice <= vocabList.getSize()) {
				Vocab selectedTopic = vocabList.getTopicAtIndex(choice);
				System.out.println("Topic: " + selectedTopic.getTopic());
				selectedTopic.getWords().printList();
			} else if (choice > vocabList.getSize()) {
				System.out.println("option out of range, try again!");
			}

		} while (choice != 0);

	}// end of browseATopic method

	/**
	 * Option 2: after user enter 2, display the pick-a-topic sub-menu. inserting a
	 * new topic before the current top topic User will also enter words under this
	 * topic. Press Enter to quit The words user entered should be sorted
	 * 
	 * @param vocabList DoublyLinkedList
	 * @param keyin     Scanner
	 */
	public static void insertATopicBefore(DoublyLinkedList<Vocab> vocabList, Scanner keyin) {
		int choice;
		do {
			displayPickATopicMenu(vocabList);

			choice = validateOptionInput(keyin);
			if (choice > 0 && choice <= vocabList.getSize()) {
				keyin.nextLine(); // Consume the newline
				System.out.println("Enter a topic name: ");
				String topicName = keyin.nextLine();
				if (topicName.isEmpty()) {
					System.out.println("No topic entered. Returning to the main menu.");
					return;
				}
				Vocab newVocab = new Vocab(topicName);
				System.out.println("Enter words - to quit press Enter :");
				while (true) {
					String word = keyin.nextLine();
					if (word.isEmpty()) {
						break; // Exit the loop if the input is empty
					}
					newVocab.getWords().insertSorted(word); // Insert the word into the Vocab
				}
				vocabList.insertBefore(choice, newVocab);
				choice = 0; // break the outer loop
			} else if (choice > vocabList.getSize()) {
				System.out.println("option out of range, try again!");
			}
		} while (choice != 0);
	}// end of insertATopicBefore method

	/**
	 * Option 3: after user enter 3, display the pick-a-topic sub-menu. inserting a
	 * new topic after the current last topic User will also enter words under this
	 * topic. Press Enter to quit The words user entered should be sorted
	 * 
	 * @param vocabList DoublyLinkedList
	 * @param keyin     Scanner
	 */
	public static void insertATopicAfter(DoublyLinkedList<Vocab> vocabList, Scanner keyin) {
		int choice;
		do {
			displayPickATopicMenu(vocabList);

			choice = validateOptionInput(keyin);
			if (choice > 0 && choice <= vocabList.getSize()) {
				keyin.nextLine(); // Consume the newline
				System.out.println("Enter a topic name: ");
				String topicName = keyin.nextLine();
				if (topicName.isEmpty()) {
					System.out.println("No topic entered. Returning to the main menu.");
					return;
				}
				Vocab newVocab = new Vocab(topicName);
				System.out.println("Enter words - to quit press Enter :");
				while (true) {
					String word = keyin.nextLine();
					if (word.isEmpty()) {
						break; // Exit the loop if the input is empty
					}
					newVocab.getWords().insertSorted(word); // Insert the word into the Vocab
				}
				vocabList.insertAfter(choice, newVocab);
				choice = 0; // break the outer loop
			} else if (choice > vocabList.getSize()) {
				System.out.println("option out of range, try again!");
			}
		} while (choice != 0);
	}// end of insertATopicAfter method

	/**
	 * Option 4:Remove the topic user entered from the list Same this should give
	 * user the pick-a-topic menu, after entering the topic option they want to
	 * remove, remove it and go back to main menu
	 * 
	 * @param vocabList DoublyLinkedList
	 * @param keyin     Scanner
	 */
	public static void removeATopic(DoublyLinkedList<Vocab> vocabList, Scanner keyin) {
		int choice;
		do {
			displayPickATopicMenu(vocabList);

			choice = validateOptionInput(keyin);
			if (choice > 0 && choice <= vocabList.getSize()) {
				vocabList.deleteAt(choice); // Remove the chosen topic by index
				break; // After remove, go back to main menu
			} else if (choice > vocabList.getSize()) {
				System.out.println("option out of range, try again!");
			}
		} while (choice != 0);

	}// end of removeATopic method

	/**
	 * Option 5: After user enter 5, it will display pick-a-topic menu, user enter
	 * the topic choice, then display Modify-Topics-Menu : (a) add a word(sorted);
	 * (r)remove a word;(c) change a word;0 Exit
	 * 
	 * @param vocabList DoublyLinkedList
	 * @param keyin     Scanner
	 */
	public static void modifyATopic(DoublyLinkedList<Vocab> vocabList, Scanner keyin) {
		int choice;
		do {
			displayPickATopicMenu(vocabList);

			choice = validateOptionInput(keyin);
			if (choice > 0 && choice <= vocabList.getSize()) {
				Vocab selectedVocab = vocabList.getTopicAtIndex(choice);
				String action = null;
				do {
					displayModifyTopicsMenu();
					action = keyin.next().trim(); // make sure it's "a", "r", "c" or "0"
					keyin.nextLine(); // Consume the newline

					switch (action) {
					case "a":
						// Add a word
						System.out.println("Type a word and press Enter, or press Enter to end input");
						String wordToAdd = keyin.nextLine();
						if (wordToAdd.isEmpty()) {
							break; // Exit the loop if the input is empty
						}
						if (!selectedVocab.getWords().contains(wordToAdd)) {
							selectedVocab.getWords().insertSorted(wordToAdd);
//							System.out.println(wordToAdd + " has been added.");
						} else {
							System.out.println("Sorry, the word: '" + wordToAdd + "' is already listed");
						}
						action = "0"; // To break out of ModifyTopicsMenu loop
						break;
					case "r":
						// Remove a word
						System.out.println("Enter a word: ");
						String wordToRemove = keyin.nextLine();
						if (selectedVocab.getWords().contains(wordToRemove)) {
							selectedVocab.getWords().remove(wordToRemove);
//							System.out.println(wordToRemove + " has been removed.");
						} else {
							System.out.println("sorry, there is no word: " + wordToRemove);
						}
						action = "0"; // To break out of ModifyTopicsMenu loop
						break;
					case "c":
						// Change a word
						System.out.println("Enter the word you want to change: ");
						String oldWord = keyin.nextLine();
						if (selectedVocab.getWords().contains(oldWord)) {
							System.out.print("Enter the new word: ");
							String newWord = keyin.nextLine();
							if (!selectedVocab.getWords().contains(newWord)) {
								selectedVocab.getWords().remove(oldWord);
								selectedVocab.getWords().insertSorted(newWord);
								System.out.println("Word changed.");
							} else {
								System.out.println("New word " + newWord + " is already in the list");
							}
						} else {
							System.out.println("sorry, there is no word: " + oldWord);
						}
						action = "0"; // To break out of ModifyTopicsMenu loop
						break;
					case "0":
						// Exit
						break;
					}
				} while (!action.equals("0")); // end of Modify Topics Menu loop
				break; // break out of Pick A Topic Menu loop

			} else if (choice > vocabList.getSize()) {
				System.out.println("option out of range, try again!");
			}
		} while (choice != 0); // end of Pick A Topic Menu loop

	}// end of modifyATopic method

	/**
	 * Display Modify Topics Menu
	 */
	public static void displayModifyTopicsMenu() {
		System.out.println("------------------------------");
		System.out.println("      Modify Topics Menu      ");
		System.out.println("------------------------------");
		System.out.println(" a add a word");
		System.out.println(" r remove a word");
		System.out.println(" c change a word");
		System.out.println(" 0 Exit");
		System.out.println("------------------------------");
		System.out.print("Enter Your Choice: ");
	}

	// -------------manipulation on words-----------------//
	/**
	 * Option 6: After user entering a word, all corresponding topics will be
	 * displayed
	 * 
	 * @param vocabList DoublyLinkedList
	 * @param keyin     Scanner
	 */
	public static void searchTopicsForAWord(DoublyLinkedList<Vocab> vocabList, Scanner keyin) {
		System.out.println("Enter a word to search for: ");
		String searchWord = keyin.nextLine(); // consume the newLine
		searchWord = keyin.nextLine(); // Get the user input word to search for
		vocabList.searchWordInAllTopics(searchWord); // A method defined in the DLL class

	}// end of searchTopicsForAWord method

	/**
	 * Option 8: show all words starting with a given letter
	 * 
	 * @param vocabList DoublyLinkedList
	 * @param keyin     Scanner
	 */
	public static void showAllWordsStrartWithAGivenLetter(DoublyLinkedList<Vocab> vocabList, Scanner keyin) {
		char startLetter = validateCharInput(keyin);

		vocabList.showWordsSWSLetter(startLetter); // A method defined in the DLL class

	}// end of showAllWordsStrartWithAGivenLetter method

	/**
	 * Check for valid char input to prevent program crush
	 * 
	 * @param keyin Scanner
	 * @return char
	 */
	public static char validateCharInput(Scanner keyin) {
		String input;
		while (true) {
			System.out.print("Enter a starting letter to search for: ");
			input = keyin.next(); // Use scanner.next() to get the next token from the input
			if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
				System.out.println("Invalid input. Please enter a single character.");
				keyin.nextLine(); // Consume the newline
			} else {
				return input.charAt(0); // Return the first character if input length is 1
			}
		}
	}

	/**
	 * Option 9:save to file Save the topics and corresponding words to file in the
	 * same format as the input file
	 * 
	 * @param vocabList DoublyLinkedList
	 * @param keyin     Scanner
	 */
	public static void saveToFile(DoublyLinkedList<Vocab> vocabList, Scanner keyin) {
		System.out.println("Enter the filename to save to: ");
		String filename = keyin.nextLine(); // consume the newline
		filename = keyin.nextLine();

		// Check if the file is writable
		if (!isWritableFile(filename)) {
			return;
		}

		// Proceed to save the data to the file
		try {
			vocabList.saveDLLToFile(filename);
			System.out.println("Data has been saved successfully to " + filename);
		} catch (Exception e) {
			System.err.println("Failed to save data: " + e.getMessage());
		}
	}// end of saveToFile method

	/**
	 * Check for valid integer input to prevent program crush
	 * 
	 * @param keyin Scanner
	 * @return int option
	 */
	public static int validateOptionInput(Scanner keyin) {
		int input;
		while (true) {
			while (!keyin.hasNextInt()) {
				String badInput = keyin.next();
				System.out.println("Error, '" + badInput + "' is not a valid integer. Please try again:");
			}
			input = keyin.nextInt();
			if (input >= 0) {
				break;
			} else {
				System.out.println("Error, the number must be non-negative. Please try again:");
			}
		}
		return input; // Return the valid input.
	}

	/**
	 * Display a welcome message which includes name.
	 */
	public static void welcome() {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+    Welcome to MINGMING's Vocabulary Control Center    +");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println();
	}

	/**
	 * Display the closing message
	 */
	public static void closing() {
		System.out.println(">>> Exiting... ");
		System.out.println("Thank you for choosing MINGMING's Vocabulary Control Center :)");
	}

	/**
	 * Display the main menu
	 */
	public static void displayControlCenter() {
		System.out.println("------------------------------");
		System.out.println("   Vocabulary Control Center  ");
		System.out.println("------------------------------");
		System.out.println(" 1  browse a topic");
		System.out.println(" 2  insert a new topic before another one");
		System.out.println(" 3  insert a new topic after another one");
		System.out.println(" 4  remove a topic");
		System.out.println(" 5  modify a topic");
		System.out.println(" 6  search topics for a word");
		System.out.println(" 7  load from a file");
		System.out.println(" 8  show all words starting with a given letter");
		System.out.println(" 9  save to file");
		System.out.println(" 0  exit");
		System.out.println("------------------------------");
		System.out.print("Enter Your Choice: ");
	}
}
