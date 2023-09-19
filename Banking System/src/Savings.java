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
 * Savings account to run transactions for an account
 * @author Caleb Orhomre
 */
public class Savings extends Account {

	//declaring and initializing necessary variable
	private final double fee = 0.5; 

	/**
	 * Savings constructor
	 */
	public Savings() {
		super(null);

	}

	/**
	 * Method to handle deposit
	 * @param amount holds the amount the user entered
	 */
	@Override
	public void deposit(double amount) {

		//setting the balance
		super.balance = amount - fee + super.balance;

		super.setStatement(1, amount);
	}

	/**
	 * Method to handle withdrawal
	 * @param amount holds the amount the user entered
	 */
	@Override
	public void withdraw(double amount) {

		//setting the balance
		super.balance = super.balance - amount;

		super.setStatement(2, amount);
	}
	
	

}
