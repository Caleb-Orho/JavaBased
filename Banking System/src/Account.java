import java.util.ArrayList;
import java.util.Scanner;


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
 * Class that reads and prints account details 
 * @author Caleb Orhomre
 *
 */
public abstract class Account {

	private Person person; 						//person constructor
	protected double balance;  					//instance variable that holds account balance
	protected int accNum;						//variable that holds account number
	private CreditCard card;					//instance variable of credit card
	protected String cardRefrence;				//variable to hold credit card
	protected String passWord;
	private String accType;
	protected ArrayList<String> accStatement = new ArrayList<String>();  //array list to store account statement
	private long millis = System.currentTimeMillis();   				
	private java.util.Date date = new java.util.Date(millis);  

	/**
	 * Account parameterized constructor 
	 * @param person variable that references person object
	 */
	public Account(Person person) {

		this.person = person;
	}


	/**
	 * Read Info method to read persons information
	 * @param firstName persons first name
	 * @param lastName persons last name
	 * @param email persons email address
	 * @param num persons phone number 
	 * @param accountNumber persons account number
	 */
	public void readInfo(String accType, String firstName, String lastName, String email, long num, int accountNumber, String passWord) {

		//Initializing the person object
		person = new Person(firstName, lastName, email, num);
		//setting person account number
		this.accNum = accountNumber;
		this.passWord = passWord;
		this.accType = accType;

	}

	/**
	 * Method to handle deposit
	 * @param amount amount holds the amount the user entered
	 */
	public abstract void deposit(double amount);

	/**
	 * Method to handle withdrawal
	 * @param amount amount holds the amount the user entered
	 */
	public abstract void withdraw(double amount);

	/**
	 * Method that return account balance
	 */
	public void returnAccountBalance() {

		System.out.println("Account Balance: " + balance);

	}

	/**
	 * Method to set user password
	 * @param pass
	 */
	public void setPassword(String pass) {

		//setting password
		passWord = pass;

	}
	/**
	 * Method to write information to file
	 */
	public void writeInfo(int ans) {

		char[] too = passWord.toCharArray();

		too = reverse(too);

		for (int i = 0; i <= passWord.length() - 1; i++) {

			too[i] = (char) (too[i] + 2);

		}
		String DecodedPassWrd = new String(too);

		
		if(ans == 0) {

			if(cardRefrence == null) {

				Bank.output.format("%s %d %s %s %d %.2f %s %s %s\n",accType, accNum, person.firstName 
						+ " " + person.lastName, person.email, person.phoneNumber, balance, cardRefrence, "Null", DecodedPassWrd);

			} else {

				Bank.output.format("%s %d %s %s %d %.2f %s %s\n",accType, accNum, person.firstName 
						+ " " + person.lastName, person.email, person.phoneNumber, balance, cardRefrence, DecodedPassWrd);

			}

		} else if( ans == 10) {

			for(int i = 0; i < accStatement.size(); i++) {

				Bank.output.format( "%s\n", accStatement.get(i).toString());

			}

		}

	}

	/**
	 * Method to return the account statement
	 */
	public void returnAccStatement() {

		System.out.println("Account statement for " + person.getFullName());
		
		for(int x = 0; x < accStatement.size(); x++) {

			System.out.println(String.format("%15s |", accStatement.get(x)));

		}
	}


	/**
	 * Method to set attributes read form file
	 * @param accType The account type
	 * @param accNum The account number
	 * @param fName The person first name
	 * @param lName The person last name
	 * @param email The person email address
	 * @param phoneNumber The person phone number 
	 * @param bal The person account number
	 */
	public void setInfo(String accType, int accNum, String fName, String lName, String email, long phoneNumber, Double bal, String creditCard, String cardType, String pass) {


		char[] too = pass.toCharArray();

		too = reverse(too);

		for (int i = 0; i <= pass.length() - 1; i++) {

			too[i] = (char) (too[i] - 2);

		}

		pass = new String(too);
		
		System.out.println(pass);

		person = new Person(null, null, null, null);

		this.accType = accType;

		this.accNum = accNum;

		Bank.accNumber = accNum;

		person.firstName = fName;

		person.lastName = lName;

		person.email = email;

		person.phoneNumber = phoneNumber;

		balance = bal;
		
		cardRefrence = creditCard + " " + cardType;

		if(!(creditCard.equals("null"))) {

			Bank.lastFour = Integer.parseInt(creditCard.substring(12, 16)); 

		}



		passWord = pass;
	}

	
	/**
	 * Method to set account statement
	 * @param number Variable to check the account type
	 * @param amount Variable that holds the amount
	 */
	public void setStatement(int number, double amount) {

		if(number == 1) {

			String addx = String.format( "Deposit\n"
					+ "Name: " + person.getFullName() + "\n" 
					+ "Account Number: " 
					+ accNum + "\n"
					+ "Deposit amount: " + amount + "\n"
					+ "Date: " + date
					+ "\n");

			accStatement.add(addx);

		}else {

			String addx = String.format( "Withdraw\n"
					+ "Name: " + person.getFullName() + "\n" 
					+ "Account Number: " 
					+ accNum + "\n"
					+ "Withdrawal amount: " + amount + "\n" 
					+ "Date: " + date
					+ "\n");

			accStatement.add(addx);

		}
	}

	/**
	 * CreditCard method to generate a credit card
	 * @param ans holds the answer the user chose
	 * @param credit variable that holds the last four digit of every creditcard
	 */
	public void creditCard(int ans, int credit) {

		switch(ans) {
		case 1:

			//initializing the credit card object to generate a credit card
			card = new MasterCard();
			cardRefrence = card.generateCreditCard(credit);
			break;
		case 2:

			//initializing the credit card object to generate a credit card
			card = new Visa();
			cardRefrence = card.generateCreditCard(credit);
			break;
		}

	}

	/**
	 * To string method to print out information
	 */
	@Override
	public String toString() {

		String PrintInfo = null;

		if((cardRefrence == null || cardRefrence.equals("null Null"))) {

			PrintInfo = String.format("%14s |%20s |%20s |%15s |%15s |", accNum, person.getFullName(), person.getEmail(), person.getPhoneNumber(), balance);
		}

		else if (!(cardRefrence.equals("null Null")) || !(cardRefrence == null) ) {
			PrintInfo = String.format("%14s |%20s |%20s |%15s |%15s |%25s |", accNum, person.getFullName(), person.getEmail(), person.getPhoneNumber(), balance, cardRefrence);
		}

		return PrintInfo;
	}

	/**
	 * Custom exception to check if transaction is negative
	 * @param input holds the user input 
	 * @throws TransactionAmountCannotBeNegative transaction can not be negative exception
	 */
	void checkDeposit(double input)throws TransactionAmountCannotBeNegative{

		if(input < 0) {

			throw new TransactionAmountCannotBeNegative("Transaction amount cannot be negative");

		}
	}

	/**
	 * Custom exception to check for insufficient fund
	 * @param input holds the user input
	 * @throws InsufficientFundException insufficient fund exception
	 */
	void checkBalance(double input)throws InsufficientFundException{

		if(input > balance) {

			throw new InsufficientFundException("Insufficient Fund");

		}
	}



	public static int inputInteger(Scanner scan, String type) {
		boolean isInputBad = true;
		boolean hasNextInt;
		int value = 0;
		while(isInputBad) {
			hasNextInt = scan.hasNextInt();
			if(hasNextInt) {
				value = scan.nextInt();
				isInputBad = false; // break out of loop
			}
			else {
				System.out.println(type);
				scan.next();
			}
			// clean up input stream
		}
		return value;
	}


	private static char[] reverse(char[] original) {
		int length = original.length;
		char[] reversed = new char[length];
		int writeIndex = 0;
		for(int readIndex = length - 1; readIndex >= 0; readIndex--) {
			reversed[writeIndex] = original[readIndex];
			writeIndex++;
		}return reversed;
	}


}
