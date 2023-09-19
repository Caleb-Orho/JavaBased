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
 * Chequing account to run transactions for an account
 * @author Caleb Orhomre
 * 
 */
public class Chequing extends Account {

	//declaring and initializing necessary variable
	private final double fee = 1.5;
	
	/**
	 * Chequing constructor
	 */
	public Chequing() {
		super(null);

	}

	/**
	 * Method to handle deposit
	 * @param amount holds the amount the user entered
	 */
	@Override
	public void deposit(double amount) {

		//if statement to check if balance is grater than 3k to exempt the fee
		if (super.balance > 3000) {

			//setting the balance
			super.balance = amount + super.balance;

		} else {

			//setting the balance
			super.balance = amount - fee + super.balance;
		}
		
		super.setStatement(1, amount);
		

	}

	/**
	 * Method to handle withdrawal
	 * @param amount holds the amount the user entered
	 */
	@Override
	public void withdraw(double amount)  {

			//setting the balance
			super.balance = super.balance - amount - fee;

		
		super.setStatement(2, amount);
	}

}
