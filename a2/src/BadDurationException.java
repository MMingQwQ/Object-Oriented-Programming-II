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
 * Invalid Duration :(
 * 
 * @author Mingming Zhang
 * @version 03/13/2024
 */

public class BadDurationException extends Exception {
	/**
	 * Default Constructor
	 */
	public BadDurationException() {
		super("Bad Duration");
	}

	/**
	 * @param message custom error message
	 */
	public BadDurationException(String message) {
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