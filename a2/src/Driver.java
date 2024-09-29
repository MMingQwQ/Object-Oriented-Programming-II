//-------------------------------------------------------------------- 
//Assignment 2
//Question: Part 1-2-3
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
 * Assignment # 2
 * 
 * Due Date: 03/27/2024
 * 
 * @author Mingming Zhang
 * @version 03/13/2024
 */
public class Driver {
	private static final String[] VALID_GENRE = { "musical", "comedy", "animation", "adventure", "drama", "crime",
			"biography", "horror", "action", "documentary", "fantasy", "mystery", "sci-fi", "family", "romance",
			"thriller", "western" };
	private static final String[] VALID_RATING = { "PG", "Unrated", "G", "R", "PG-13", "NC-17" };
	private static final String[] GENRE_CSV_LIST = { "musical.csv", "comedy.csv", "animation.csv", "adventure.csv",
			"drama.csv", "crime.csv", "biography.csv", "horror.csv", "action.csv", "documentary.csv", "fantasy.csv",
			"mystery.csv", "sci-fi.csv", "family.csv", "western.csv", "romance.csv", "thriller.csv" };

	/**
	 * Main method to run the actual code: (1) partition all valid movie records
	 * into new genre-based1 text files, (2) load an array of movie records from
	 * each of the partitioned text file, serializing the resulting movie array to a
	 * binary file, and (3) deserialize (reconstruct) the serialized arrays from the
	 * binary files into a 2D array of movie record objects, and finally provide an
	 * interactive program that allows the user to navigate a movie array,
	 * displaying user-specified number of movie-records.
	 * 
	 * @param args String[]
	 */
	public static void main(String[] args) {
		// part 1's manifest file
		String part1_manifest = "part1_manifest.txt";

		// part 2's manifest file
		String part2_manifest = do_part1(part1_manifest); // partition

		// part 3's manifest file
		String part3_manifest = do_part2(part2_manifest); // serialize
		do_part3(part3_manifest); // deserialize and navigate

		return;
	}

	// ----------------------- Part 1 -------------------------//
	/**
	 * Part1:
	 * 
	 * Validating the inputStream and partition movies based on genre
	 * 
	 * Input: 10 movies CSV files. Read and write the name of movies file names into
	 * "part1_manifest.txt"; Use the names in the "part1_manifest.txt" then read the
	 * file and check each movie record
	 * 
	 * Output: 19 files (1: "bad_movie_records.txt"; 17 : CSV files; 1 :
	 * part2_manifest.txt record genre CSV file names)
	 * 
	 * @param part1_manifest String directory name
	 * @return String
	 */
	public static String do_part1(String part1_manifest) {
		// Display the welcome message
		welcome();

		Scanner sc = null;
		PrintWriter errorWriter = null;
		PrintWriter[] genreWriters = new PrintWriter[GENRE_CSV_LIST.length];
		String movieInfo = null;
		int errorMovieCount = 0;
		int movieIndex = 0;

		String inputPath = "part1_input";
		String outputPath = part1_manifest;

		try {
			String[] fileNames = writeFileNames(inputPath, outputPath);
			errorWriter = new PrintWriter(new FileOutputStream("bad_movie_records.txt"), true);
			// Create the corresponding csv files and store in the part1_output Folder
			for (int j = 0; j < GENRE_CSV_LIST.length; j++) {
				String genreFileName = "part1_output/" + GENRE_CSV_LIST[j];
				File file = new File(genreFileName);
				// Check if the file exists or empty, if exist ->overwrite
				if (!file.exists() || file.length() == 0) {
					genreWriters[j] = new PrintWriter(new FileOutputStream(genreFileName, true));
				} else {
					genreWriters[j] = new PrintWriter(new FileOutputStream(genreFileName));
				}
			}

			for (int i = 0; i < fileNames.length; i++) {
				File file = new File("part1_input/" + fileNames[i]);
				movieIndex = 0;
				sc = new Scanner(new FileInputStream(file));

				while (sc.hasNextLine()) {
					movieIndex++;
					movieInfo = sc.nextLine(); // Read movie line by line

					try {
						checkSyntaxError(movieInfo);

						String[] fields = splitLineIntoFields(movieInfo); // Check and split into fields
						checkSemanticError(fields);

						String genreCSV = fields[3] + ".csv";

						for (int k = 0; k < GENRE_CSV_LIST.length; k++) {
							if (genreCSV.equalsIgnoreCase(GENRE_CSV_LIST[k])) {
								genreWriters[k].println(movieInfo);
								break; // Once the right genre writer is found and used, break out of the loop.
							}
						}

					} catch (MissingQuotesException | ExcessFieldsException | MissingFieldsException e) {
						errorWriter.write("\n======================================================");
						errorWriter.write("\nError Type -> " + e.getMessage());
						errorWriter.write("\nThe Original movie record: " + movieInfo);
						errorWriter.write("\nAt Line: " + movieIndex + " | in the file : " + fileNames[i]);
						errorWriter.write("\n======================================================\n");
						errorMovieCount++;

					} catch (BadYearException | BadDurationException | BadGenreException | BadRatingException
							| BadTitleException | BadNameException | BadScoreException e) {
						errorWriter.write("\n======================================================");
						errorWriter.write("\nError Type -> " + e.getMessage());
						errorWriter.write("\nThe Original movie record: " + movieInfo);
						errorWriter.write("\nAt Line: " + movieIndex + " | in the file : " + fileNames[i]);
						errorWriter.write("\n======================================================\n");
						errorMovieCount++;
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File was not found or could not be opened.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error when reading the files.");
			e.printStackTrace();
		}
		errorWriter.write("\nTotal bad movie records are: " + errorMovieCount); // 28
		errorWriter.close();
		// Close all PrintWriter objects
		for (PrintWriter writer : genreWriters) {
			if (writer != null)
				writer.close();
		}

		return "part2_manifest.txt";
	}

	/**
	 * This method will read the files and then sort and write the name into output
	 * txt file and finally return the String array of file names
	 * 
	 * @param inputPath  String
	 * @param outputPath String
	 * @return String[] file names
	 */
	public static String[] writeFileNames(String inputPath, String outputPath) {
		File folder = new File(inputPath);
		// Returns an array of abstract pathnames denoting the files in the directory
		File[] listOfFiles = folder.listFiles();
		BufferedWriter writer = null;
		String[] fileNames = null;
		try {
			if (listOfFiles != null) {
				fileNames = new String[listOfFiles.length];
				int count = 0;
				for (File file : listOfFiles) {
					if (file.isFile()) {
						// Method getName() -> Returns the name of the file.
						fileNames[count++] = file.getName();
					}
				}
				// Sort the name in order to better display
				sortName(fileNames, count);
				writer = new BufferedWriter(new FileWriter(outputPath));
				for (int i = 0; i < count; i++) {
					writer.write(fileNames[i]);
					writer.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close all BufferedWriter
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return fileNames;
	}

	/**
	 * Sort the movie file names in order to better display output
	 * 
	 * @param fileNames String[] array of sorted file names
	 * @param count     array element count
	 */
	public static void sortName(String[] fileNames, int count) {
		// Set less than count - 1 to prevent OutOfBound
		for (int i = 0; i < count - 1; i++) {
			for (int j = 0; j < count - i - 1; j++) {
				if (fileNames[j].compareTo(fileNames[j + 1]) > 0) {
					// Swap fileNames[j+1] and fileNames[i]
					String temp = fileNames[j];
					fileNames[j] = fileNames[j + 1];
					fileNames[j + 1] = temp;
				}
			}
		}
	}

	// ----- Checking for Syntax Error -----//
	/**
	 * Check for Syntax Error : A CSV record is valid if it has exactly 10 data
	 * fields;
	 * 
	 * @param line The input line from csv file
	 * @throws MissingQuotesException When title has quotes, at least one of \" is
	 *                                missing
	 * @throws ExcessFieldsException  Too many fields
	 * @throws MissingFieldsException Fields less than 10
	 */
	public static void checkSyntaxError(String line)
			throws MissingQuotesException, ExcessFieldsException, MissingFieldsException {
		// Checking if the line has "\""
		if (line.indexOf("\"") == -1) {
			// No "\"", then separate the line by ","
			String[] fields = line.split(",");

			// ----- ExcessFieldsException-----//
			if (fields.length > 10)
				throw new ExcessFieldsException("Syntax Error : Too many fields.");

			// ----- MissingFieldsException-----//
			for (String field : fields) {
				if (field.isEmpty() || fields.length < 10)
					throw new MissingFieldsException("Syntax Error : Missing fields.");
			}

		} else {
			// Has "\"": normally, 2 quotes so hasQuotes.length should be 3
			String[] hasQuotes = line.split("\"");

			// Case 1: Missing quotes (only one quotes, so the hasQuotes.length should be 2)
			if (hasQuotes.length == 2)
				throw new MissingQuotesException("Syntax Error : At least one of \" is missing.");

			// Case 2: NO Missing but missing Fields
			if (hasQuotes.length == 3) {
				String[] AfterQ = hasQuotes[2].substring(1).split(",");
				if (AfterQ.length > 8) {
					throw new ExcessFieldsException("Syntax Error : Too many fields.");
				}
				for (String field : AfterQ) {
					if (field.isEmpty() || AfterQ.length < 8)
						throw new MissingFieldsException("Syntax Error : Missing fields.");
				}
			}
		}
	}

	/**
	 * Split the line into fields of movie
	 * 
	 * Precondition : The line doesn't have Syntax Error
	 * 
	 * @param line String
	 * @return String[] fields
	 */
	public static String[] splitLineIntoFields(String line) {
		String[] fields = new String[10];
		// Case 1 : no quotes
		if (line.indexOf("\"") == -1) {
			fields = line.split(",");
		} else // Case 2: Title is surrounded by ""
		{
			String beforeQuotes = line.substring(0, line.indexOf("\"") - 1); // get rid of comma as well
			String titleInsideQuotes = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
			String afterQuotes = line.substring(line.lastIndexOf("\"") + 2); // get rid of the following comma at begin

			fields[0] = beforeQuotes;
			fields[1] = titleInsideQuotes;
			String[] AfterQ = afterQuotes.split(",");
			for (int i = 0; i < 8; i++) {
				fields[i + 2] = AfterQ[i];
			}
		}
		return fields;
	}

	// ----- Checking for Semantic Error -----//
	/**
	 * Checking for Semantic Error
	 * 
	 * @param fields String[]
	 * @throws BadYearException     year less than 1990 or greater than 1999 |
	 *                              unconvertible String | Missing
	 * @throws BadDurationException Not an integer from 30 through 300 minutes |
	 *                              Missing
	 * @throws BadGenreException    Not a valid genre listed in the instruction |
	 *                              Missing
	 * @throws BadRatingException   Not a valid rating listed in the instruction |
	 *                              Missing
	 * @throws BadTitleException    A quoted title may not contain double quotes
	 *                              within its text | Missing
	 * @throws BadNameException     Missing
	 * @throws BadScoreException    Not a positive double value less than or equal
	 *                              10 | Missing
	 */
	public static void checkSemanticError(String[] fields) throws BadYearException, BadDurationException,
			BadGenreException, BadRatingException, BadTitleException, BadNameException, BadScoreException {

		// Year: year < 1990 | year > 1999 | unconvertible String | Missing
		if (fields[0] != null && !fields[0].isBlank()) {
			try {
				int year = Integer.parseInt(fields[0]);
				if (year < 1990 || year > 1999)
					throw new BadYearException("Semantic Error: Invalid Year.");
			} catch (NumberFormatException e) {
				throw new BadYearException("Semantic Error: Invalid Year, Should be Integer Not String.");
			}
		} else
			throw new BadYearException("Semantic Error: Missing Year.");

		// Title:
		if (fields[1] == null || fields[1].isBlank())
			throw new BadTitleException("Semantic Error: Missing Title.");
//		else {
//			if (fields[1].indexOf("\"") != -1) // has quotes inside title
//				throw new BadTitleException(
//						"Semantic Error: A quoted title may not contain double quotes within its text");
//		}

		// Duration: an integer from 30 through 300 minutes.
		if (fields[2] != null && !fields[2].isBlank()) {
			try {
				int duration = Integer.parseInt(fields[2]);
				if (duration < 30 || duration > 300)
					throw new BadDurationException("Semantic Error: Invalid Duration.");
			} catch (NumberFormatException e) {
				throw new BadDurationException("Semantic Error: Invalid Duration, Should be Integer Not String.");
			}
		} else
			throw new BadDurationException("Semantic Error: Missing Duration.");

		// Genre:
		if (fields[3] != null && !fields[3].isBlank()) {
			boolean isVaildGenre = false;
			for (String str : VALID_GENRE) {
				if (fields[3].equalsIgnoreCase(str)) {
					isVaildGenre = true;
					break;
				}
			}
			if (!isVaildGenre)
				throw new BadGenreException("Semantic Error: Invalid Genre.");
		} else
			throw new BadGenreException("Semantic Error: Missing Genre.");

		// Rating:
		if (fields[4] != null && !fields[4].isBlank()) {

			boolean isVaildRating = false;
			for (String str : VALID_RATING) {
				if (fields[4].equalsIgnoreCase(str)) {
					isVaildRating = true;
					break;
				}
			}
			if (!isVaildRating)
				throw new BadRatingException("Semantic Error: Invalid Rating.");
		} else
			throw new BadRatingException("Semantic Error: Missing Rating.");

		// Score: a positive double value less than or equal 10.
		if (fields[5] != null && !fields[5].isBlank()) {
			try {
				double score = Double.parseDouble(fields[5]);
				if (score < 0 || score > 10)
					throw new BadScoreException("Semantic Error: Invalid Score.");
			} catch (NumberFormatException e) {
				throw new BadScoreException("Semantic Error: Invalid Score, Should be Double Not String.");
			}
		} else
			throw new BadDurationException("Semantic Error: Missing Score.");

		// Names:
		if (fields[6].isBlank() || fields[7].isBlank() || fields[8].isBlank() || fields[9].isBlank()
				|| fields[6] == null || fields[7] == null || fields[8] == null || fields[9] == null)
			throw new BadNameException("Semantic Error: Missing Names.");
	}

	// ----------------------- Part 2 -------------------------//
	/**
	 * Part2:
	 * 
	 * Create and Serialize Arrays of Movie Records For each csv file named in
	 * part2_manifest.txt, say abc.csv, load the records from the file into an array
	 * of Movie objects and then serialize the array into a binary file named
	 * abc.ser.
	 * 
	 * Output: 1 file called "part3_manifest.txt" recording the SER file names, and
	 * 17 binary SER files.
	 * 
	 * @param part2_manifest String
	 * @return String
	 */

	public static String do_part2(String part2_manifest) {

		try {
			PrintWriter pw1 = new PrintWriter(new FileOutputStream(part2_manifest));
			PrintWriter pw2 = new PrintWriter(new FileOutputStream("part3_manifest.txt"));
			Scanner sc = null;
			String movieInfo;

			// Step 1: Read each csv file named in part2_manifest.txt
			for (int i = 0; i < GENRE_CSV_LIST.length; i++) {
				pw1.append(GENRE_CSV_LIST[i] + System.lineSeparator());
				String csvFile = "part1_output/" + GENRE_CSV_LIST[i];

				// Read the csv file and determine the length of movie array
				File file = new File(csvFile);
				int movieCount = countMovie(file);

				Movie[] movieArr = new Movie[movieCount];
				int movieIndex = 0;
				String binaryFileName = GENRE_CSV_LIST[i].replace(".csv", ".ser");
				pw2.append(binaryFileName + System.lineSeparator());

				if (file.canRead()) {
					sc = new Scanner(new FileInputStream(file));
					if (sc.hasNextLine()) {
						while (sc.hasNextLine()) {
							movieInfo = sc.nextLine();

							// Step 2: Load the records from the file into an array of Movie objects
							movieArr[movieIndex++] = parseStringToMovie(movieInfo);

							// Step 3: Serialize the array into a binary file named abc.ser.
							serializeMoviesArray(movieArr, binaryFileName);
						}
					} else {
//						System.out.println("There is no movie record in " + csvFile);
//						System.out.println("For the purpose of parsing from .csv file to .ser file");
//						System.out.println("Assign a default anonymous movie into the " + binaryFileName + "\n");
						Movie[] emptyMArr = {};
						serializeMoviesArray(emptyMArr, binaryFileName);
					}
				}
			}
			pw1.close();
			pw2.close();
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("File was not found or could not be opened.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error when reading the files.");
			e.printStackTrace();
		}

		return "part3_manifest.txt";
	}

	/**
	 * Determine the number of movies in the csv file in order to allocate the array
	 * length
	 * 
	 * @param csvFileName File
	 * @return int (the number of movies)
	 * @throws IOException error when opening the file
	 */
	public static int countMovie(File csvFileName) throws IOException {
		BufferedReader csvBR = new BufferedReader(new FileReader(csvFileName));
		int movieCount = 0;
		String movieLine = csvBR.readLine();
		while (movieLine != null) {
			movieCount++;
			movieLine = csvBR.readLine();
		}
		csvBR.close();
		return movieCount;
	}

	/**
	 * Take a CSV line and return a Movie object after parsing
	 * 
	 * @param csvLine String
	 * @return movie Movie object
	 */
	public static Movie parseStringToMovie(String csvLine) {
		String[] fields = splitLineIntoFields(csvLine);
		int year = Integer.parseInt(fields[0]);
		String title = fields[1];
		int duration = Integer.parseInt(fields[2]);
		String genre = fields[3];
		String rating = fields[4];
		double score = Double.parseDouble(fields[5]);
		String director = fields[6];
		String actor1 = fields[7];
		String actor2 = fields[8];
		String actor3 = fields[9];

		Movie movie = new Movie(year, title, duration, genre, rating, score, director, actor1, actor2, actor3);
		return movie;
	}

	/**
	 * Serialize the resulting array into a binary file
	 * 
	 * @param movies   Movie[]
	 * @param fileName String
	 * @throws FileNotFoundException error when opening the file (not found or
	 *                               cannot open)
	 * @throws IOException           error when opening the file
	 */
	public static void serializeMoviesArray(Movie[] movies, String fileName) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("part2_output/" + fileName));
		oos.writeObject(movies);
		oos.flush();
		oos.close();
	}

	// ----------------------- Part 3 -------------------------//
	/**
	 * Part3:
	 * 
	 * Deserialize the Movie Arrays and Navigate the Movie Arrays
	 * 
	 * @param part3_manifest String
	 */
	public static void do_part3(String part3_manifest) {
		// Step 1: Deserialize the Movie Arrays
		// Read the binary file names from the part2 file
		try {
			// First-D : Movie Genres
			// Second-D : Movie Records
			Movie[][] all_movies = new Movie[GENRE_CSV_LIST.length][];
			Movie[] movieArr;
			int index = 0;

			for (int i = 0; i < GENRE_CSV_LIST.length; i++) {
				String binaryFileName = GENRE_CSV_LIST[i].replace(".csv", ".ser");
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("part2_output/" + binaryFileName));
				try {
					while (true) {
						// Deserialize the array from the binary file
						movieArr = (Movie[]) ois.readObject(); // Casting into Movie[]
						all_movies[index++] = movieArr;
					}
				} catch (EOFException e) {
					// If reach to the end of binary file
					// System.out.println("No more movie in the file.");
				}
				ois.close();
			}
			// Step 2:Navigate the Movie Arrays
			navigateMovies(all_movies);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * The method will provide a vertical view of the objects within a movie array,
	 * allowing the user to display a range of records above or below the current
	 * movie record at its current position, which is initially set to the index of
	 * the first record in the array.
	 * 
	 * @param all_movies Movie[][]
	 */
	public static void navigateMovies(Movie[][] all_movies) {
		Scanner keyin = new Scanner(System.in);
		int currentGenreIndex = 0; // Index for genre array
		int currentMovieIndex = 0; // Index for movies within the genre

		String choice;
		do {
			System.out.println("-----------------------------");
			System.out.println("          Main Menu          ");
			System.out.println("-----------------------------");
			System.out.println(" s  Select a movie array to navigate");
			System.out.println(" n  Navigate " + getGenreName(currentGenreIndex) + " "
					+ (all_movies[currentGenreIndex] != null ? all_movies[currentGenreIndex].length : 0) + " movies");
			System.out.println(" x  Exit");
			System.out.println("-----------------------------");
			System.out.print("\nEnter Your Choice: ");

			choice = keyin.next().trim().toLowerCase(); // Make sure user input casting to lower case

			switch (choice) {
			case "s":
				// Display genre sub-menu and prompt user to select a movie genre
				currentGenreIndex = selectGenre(all_movies, keyin);
				currentMovieIndex = 0; // Reset movie index when switching genres
				break;
			case "n":
				// Navigate movies in the current genre
				currentMovieIndex = navigateGenre(all_movies[currentGenreIndex], currentGenreIndex, currentMovieIndex,
						keyin);
				break;
			case "x":
				closing(); // Display closing message
				break;
			default:
				System.out.println("Invalid choice, please try again.");
			}
		} while (!choice.equals("x"));

	}

	/**
	 * Display genre sub-menu and prompt user to select a movie genre
	 * 
	 * @param all_movies Movie[][]
	 * @param keyin      Scanner
	 * @return int genreChoice
	 */
	public static int selectGenre(Movie[][] all_movies, Scanner keyin) {
		System.out.println("-----------------------------");
		System.out.println("        Genre Sub-Menu       ");
		System.out.println("-----------------------------");
		for (int i = 0; i < all_movies.length; i++) {
			if (i == 9) {
				// shorthand for an if-else, and use if for index(9) for better display layout
				System.out.println(" " + (i + 1) + "  " + getGenreName(i) + "\t("
						+ (all_movies[i] != null ? all_movies[i].length : 0) + " movies)");
			} else
				System.out.println(" " + (i + 1) + "  " + getGenreName(i) + "\t\t("
						+ (all_movies[i] != null ? all_movies[i].length : 0) + " movies)");
		}
		System.out.println(" 18  Exit");
		System.out.println("-----------------------------");

		int genreChoice = checkValidInt(keyin);
		// Handle the exit option
		boolean isValid = false;
		while (!isValid) {
			if (genreChoice == 18) {
				closing(); // Display closing message and exit
				System.exit(0);
			} else if (genreChoice < 1 || genreChoice > 18) {
				System.out.println("Invalid choice, please try again.");
				genreChoice = checkValidInt(keyin);
			} else {
				// Adjust for 0-based index
				genreChoice -= 1;
				isValid = true;
			}
		}
		return genreChoice;
	}

	/**
	 * Parsing user input from convertible string to integer
	 * 
	 * @param keyin Scanner
	 * @return int num(valid integer)
	 */
	public static int checkValidInt(Scanner keyin) {
		String strNum;
		int num;
		do {
			System.out.print("\nEnter Your Choice: ");
			strNum = keyin.next().trim();
			try {
				num = Integer.parseInt(strNum);
				break;
			} catch (NumberFormatException e) {
				System.out.println("Bad input: " + strNum);
				System.out.println("try again ...");
			}
		} while (true);
		return num;
	}

	/**
	 * Navigate movies in the current genre
	 * 
	 * @param movies            Movie[]
	 * @param currentGenreIndex int
	 * @param currentMovieIndex int
	 * @param keyin             Scanner
	 * @return newIndex
	 */
	public static int navigateGenre(Movie[] movies, int currentGenreIndex, int currentMovieIndex, Scanner keyin) {
		System.out.println("Navigating " + getGenreName(currentGenreIndex) + " movies (" + movies.length + ")");

		if (movies == null || movies.length == 0) {
			System.out.println("\nThere are no movies in this genre.");
			return -1; // Indicates an empty or null movie array
		}

		int move = checkValidInt(keyin);

		// If user enters 0, exit the navigation for this genre
		if (move == 0) {
			return currentMovieIndex;
		}

		int newIndex = 0;
		// Calculate the new index and make sure it's within the bounds
		if (move > 0)
			newIndex = currentMovieIndex + move - 1;
		if (move < 0)
			newIndex = currentMovieIndex + move + 1;

		// Compare newIndex with the newIndex, preventing OutOfBound
		newIndex = Math.max(0, Math.min(movies.length - 1, newIndex));
		displayMovies(movies, currentMovieIndex, newIndex, move);

		return newIndex;
	}

	/**
	 * Display Movies selected output
	 * 
	 * @param movies            Movie[]
	 * @param currentMovieIndex int
	 * @param newIndex          int
	 * @param move              int
	 */
	public static void displayMovies(Movie[] movies, int currentMovieIndex, int newIndex, int move) {
		int start, end;

		if (move < 0) { // Moving up
			end = currentMovieIndex;
			start = currentMovieIndex + move + 1;
			if (start < 0) {
				start = 0; // Reset start from beginning
				System.out.println("BOF has been reached.");
			}

		} else { // Moving down
			start = currentMovieIndex;
			// Compare newIndex with the newIndex, preventing OutOfBound
			end = Math.min(movies.length - 1, newIndex);
			if (end == movies.length - 1)
				System.out.println("EOF has been reached.");
		}

		for (int i = start; i <= end; i++) {
			if (i == newIndex) {
				// Mark the new current movie
				System.out.println("--> " + movies[i].toString());
			} else {
				// Display other movies in the range
				System.out.println("    " + movies[i].toString());
			}
		}
	}

	/**
	 * Get the genre name in String
	 * 
	 * @param index integer
	 * @return String genre name
	 */
	public static String getGenreName(int index) {
		String genre = GENRE_CSV_LIST[index].replace(".csv", "");
		return genre;
	}

	/**
	 * Display a welcome message which includes name.
	 */
	public static void welcome() {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+    Welcome to MINGMING's Movie Theater    +");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println();
	}

	/**
	 * Display the closing message
	 */
	public static void closing() {
		System.out.println(">>> Exiting... ");
		System.out.println("Thank you for choosing MINGMING's Movie Theater :)");
	}
}