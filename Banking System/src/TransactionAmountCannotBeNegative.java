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
 * Custom exception class for transaction can not be negative exception
 * @author Caleb Orhomre
 *
 */
public class TransactionAmountCannotBeNegative extends Exception {

	/**
	 * Constructor to receive message for the exception
	 * @param message holds the message for the exception
	 */
	TransactionAmountCannotBeNegative(String message){
		
		super(message);
		
	}
	
}