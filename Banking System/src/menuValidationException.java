/*
 * ===========================================
 * Program: CET
 * course: CST8132 Object-Oriented Programming
 * Task: Lab 5
 * Prof: James Mwangi
 * Section: 311
 * Student: Caleb Orhomre
 * ==========================================
 */

/**
 * Custom exception class for the menu exception
 * @author Caleb Orhomre
 *
 */
public class menuValidationException extends Exception {

	/**
	 * Constructor to receive message for the exception
	 * @param message holds the message for the exception
	 */
	menuValidationException(String message){
		super(message);
	}
}
