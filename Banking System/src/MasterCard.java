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
 * Class that returns credit card number for mastercard
 * @author Caleb Orhomre
 *
 */
public class MasterCard implements CreditCard {
	
	/**
	 * Method that generates and return a credit card
	 * @param digit holds the last digit of a credit card
	 */
	@Override
	public String generateCreditCard(int digit) {
		
		return "552212345678" + digit + " (MC)";

	}
}
