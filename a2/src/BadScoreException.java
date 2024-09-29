// -------------------------------------------------------------------- 
// Assignment 2
// Question: Part I
// Written by: Mingming Zhang 
//
// For COMP 249 Section (S) – Winter 2024
// --------------------------------------------------------------------

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
 * Invalid Score :(
 * 
 * @author Mingming Zhang
 * @version 03/13/2024
 */
public class BadScoreException extends Exception {
	/**
	 * Default Constructor
	 */
	public BadScoreException() {
		super("Bad Score");
	}

	/**
	 * @param message custom error message
	 */
	public BadScoreException(String message) {
		super(message);
	}

	/**
	 * Overriding the method for getting message
	 */
	@Override
	public String getMessage() {
		return super.getMessage();
	}
}
