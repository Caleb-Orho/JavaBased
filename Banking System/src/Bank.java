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

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * This class does the main reading and printing of the account type
 * @author Caleb Orhomre
 */
public class Bank {
	
	private ArrayList<Account> accounts; 	//instance array list named accounts
	private String name; 					//instance variable to hold bank name
	protected static int lastFour = 1000;			//variable that holds the last four digit of a creditCard and increments
	
	//variable that holds account number
	private int getAccNum;
	static int accNumber;	
	
	//variable that controls loops
	private int count;						
	private int ans;
	private String accPass;
	protected static Formatter output;



	/**
	 * Bank parameterized constructor 
	 * @param name holds the name of the bank
	 */
	public Bank(String name) {
		this.name = name;
		this.accounts = new ArrayList<>();
	}


	/**
	 * Print title method to print out headers
	 * @param name variable that holds the name of the bank
	 */
	public void printTitle(String name) {
		
		printStars();

		System.out.printf("%14s |", "Acc Number#");
		System.out.printf("%20s |", "Name");
		System.out.printf("%20s |", "Email");
		System.out.printf("%15s |", "Phone Number");
		System.out.printf("%15s |", "Balance");

		// if statement to determine if the credit card header will be printed Account.cardRefrence
		if ( lastFour != 1000 ) {

			System.out.printf("%25s |", "Credit Card");

		}

		System.out.println();

		printStars();
	}

	/**
	 * Method to print out stars
	 */
	public void printStars() {

		//loop statement to print out stars if there is a credit card
		if ( lastFour != 1000 ) {

			for(int x = 1; x <= 121; x++) {

				System.out.print("*");
			}

			System.out.println();

		}else {

			System.out.println("**********************************************************************************************");

		}

	}

	public void readData(Scanner scan) {

		try {
			scan = new Scanner(Paths.get("C:\\Users\\Caleb Orhomre\\"
					+ "CST8116 Homework\\CST8116 Eclipse Workspace\\Lab 5\\AccountInfo.txt"));

			while(scan.hasNextLine()) {

				String accType = scan.next();
				
				//getting the type of account from the file
				if(accType.equals("C")) {

					Account acc = new Chequing();
					acc.setInfo(accType, scan.nextInt(), scan.next() , scan.next(), scan.next(), scan.nextLong(), scan.nextDouble(), scan.next(), scan.next(), scan.next());
					accounts.add(acc);

				} else if (accType.equals("S")) {

					Account sav = new Savings();
					sav.setInfo(accType, scan.nextInt(), scan.next() , scan.next(), scan.next(), scan.nextLong(), scan.nextDouble(), scan.next(), scan.next(), scan.next());
					accounts.add(sav);
				}

			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Method to write data to file
	 */
	public void write(Scanner scan) {	

		do {

			try {
				
					output = new Formatter("C:\\Users\\Caleb Orhomre\\"
							+ "CST8116 Homework\\CST8116 Eclipse Workspace\\Lab 5\\AccountInfo.txt");

					for (int i = 0; i < accounts.size(); i++) {
						
						ans = 0;
						
						accounts.get(i).writeInfo(ans);
					}
					
					output.close();

					output = new Formatter("C:\\Users\\Caleb Orhomre\\"
							+ "CST8116 Homework\\CST8116 Eclipse Workspace\\Lab 5\\AccStatement.txt");

					for (int i = 0; i < accounts.size(); i++) {

						ans = 10;
						accounts.get(i).writeInfo(ans);
					}
					output.close();

					//System.out.println("\n****All accounts written to file******\n");

				count = 0;

			} catch (IOException e) {

				e.printStackTrace();
			}
			
			
		} while(count > 1);
	}

	/**
	 * ReadAccount method that reads and sets information of person
	 * @param scan the name of scanneer
	 */
	public void readAccount(Scanner scan) {

		//count++;
		Long num = null;

		//getting account holder information
		System.out.println("Let's get to know our new customer.");

		System.out.print("Enter first name: ");
		String firstName = scan.next();

		System.out.print("Enter last name: ");
		String lastName = scan.next();

		System.out.print("Enter email: ");
		String email = scan.next();

		boolean done = false;
		while (!done) {	
			try {

				System.out.print("Enter phone number: ");
				num = scan.nextLong();

				done = true;

			} catch (InputMismatchException ex) {

				scan.next();
				System.out.println("Invalid input");

			}
		}

		System.out.print("Enter account password: ");
		String password = scan.next();

		do {

			//getting the account type of user
			System.out.println("1 - Chequing ");
			System.out.println("2 - Savings");

			ans = Account.inputInteger(scan, "Inavalid menu option. Try again..... \n 1 - Chequing \n 2 - Savings");

			try {

				menu(ans, 2, 1,"Invalid menu option. Try again");
				count = 0;
			} catch(Exception e) {
				count = 2;
				System.out.println(e.getMessage());

			}

		} while(count > 1);

		count = 0;

		switch (ans) {
		case 1:

			//creating the account type the customer chose and passing the variable as a parameter to the account class.
			accNumber++;
			Account accType1 = new Chequing();
			accType1.readInfo("C", firstName, lastName, email, num, accNumber, password);
			accounts.add(accType1);

			printTitle(name);

			System.out.println(accType1.toString());

			break;
		case 2:

			//creating the account type the customer chose and passing the variable as a parameter to the account class.
			accNumber++;
			Account accType2 = new Savings();
			accType2.readInfo("S", lastName, firstName, email, num, accNumber, password);
			accounts.add(accType2);

			printTitle(name);

			System.out.println(accType2.toString());

			break;
		}
	}

	/**
	 * Method that handles transactions for accounts
	 * @param scan the name of scanneer
	 */
	public void trancsactions(Scanner scan) {
		double firstBalance;

		//Variable to print out an error if no account has been created
		if (accNumber == 0) {
			System.out.println("You need to create an account first");
		}

		else {

			printInfo();

			do {

				System.out.print("Enter account number: ");

				getAccNum = Account.inputInteger(scan, "Inavalid menu option. Try again.....\nEnter account number");

				//try statement to catch unknown account exception
				try {

					unknownAcc(getAccNum);
					count = 0;
				}catch(Exception e) {
					count = 2;
					System.out.println(e.getMessage());
				}

			} while(count > 1);

			count = 0;

			do {

				try {

					System.out.print("Enter account passWord: ");
					accPass = scan.next();

					invalidpassowrd(accPass ,accounts.get(getAccNum - 1).passWord, "Invalid Password\nTry again" );
					count = 0;
				} catch(Exception e) {

					System.out.println(e.getMessage());
					count = 2;
				}

			}while(count > 1);

			do {



				if(!(accPass.equals(accounts.get(getAccNum - 1).passWord))) {
					System.out.println("Invalid password");
				}

			} while(!(accPass.equals(accounts.get(getAccNum - 1).passWord)));

			do {

				System.out.println("1 - Deposit ");
				System.out.println("2 - Withdraw");
				System.out.println("3 - Balance");

				ans = Account.inputInteger(scan, "Inavalid menu option. Try again.....\n 1 - Deposit \n 2 - Withdraw \n 3 - Balance");
				count = 0;

				//try statement to catch invalid menu option
				try {

					menu(ans, 3, 1, "Invalid menu option. Try again..");

				} catch(Exception e) {
					count = 2;
					System.out.println(e.getMessage());

				}

			} while(count > 1);

			count = 0;

			switch (ans) {
			case 1:

				System.out.println("Enter amount to deposit: ");

				do {

					firstBalance = Account.inputInteger(scan, "Invalid deposit amount.\n Enter the Amount to deposit: ");

					//try statement to catch required exception
					try {

						accounts.get(getAccNum - 1).checkDeposit(firstBalance);
						count = 0;
					} catch(Exception e) {
						count = 2;
						System.out.println(e.getMessage());
						System.out.println("Enter amount to deposit: ");
					}
				}while(count > 1);

				//getting account to run transaction on
				accounts.get(getAccNum - 1).deposit(firstBalance);

				printTitle(name);

				System.out.println( accounts.get( getAccNum - 1).toString() );

				break;
			case 2:

				System.out.println("Enter amount to withdraw: ");

				do {


					firstBalance = Account.inputInteger(scan, "Enter a valid withdrawal amount...\n Enter the Amount to withdraw: ");

					//try statement to catch required exception
					try {
						accounts.get(getAccNum - 1).checkDeposit(firstBalance);
						accounts.get(getAccNum - 1).checkBalance(firstBalance);
						count = 0;
					} catch(Exception e) {
						count = 2;
						System.out.println( e.getMessage() );
						System.out.println("Enter amount to withdraw: ");
					}

				}while ( count > 1);

				//getting account to run transaction on
				accounts.get(getAccNum - 1).withdraw(firstBalance);

				printTitle(name);

				System.out.println( accounts.get( getAccNum - 1).toString() );

				break;
			case 3:

				accounts.get(getAccNum - 1).returnAccountBalance();
				break;

			}
		}
	}

	/**
	 * Method to delete an account
	 * @param scan
	 */
	public void deleteAccount(Scanner scan) {

		printInfo();

		System.out.print("Enter account number to delete: ");
		getAccNum = scan.nextInt();

		do {

			try {

				System.out.print("Enter account passWord: ");
				accPass = scan.next();

				invalidpassowrd(accPass ,accounts.get(getAccNum - 1).passWord, "Invalid Password\nTry again" );
				accounts.remove(accNumber - 1);
				System.out.println("Account Deleted");

				count = 0;
			} catch(Exception e) {

				System.out.println(e.getMessage());
				count = 2;
			}

		}while(count > 1);


	}

	/**
	 * Method to return account statement
	 * @param scan
	 */
	public void getAccStatement(Scanner scan) {

		if(accNumber == 0) {

			System.out.println("No account to get a statement from");

		} else {

			printInfo();
			System.out.println("Enter account number");
			getAccNum = scan.nextInt();
			accounts.get(getAccNum - 1).returnAccStatement();

		}
	}


	/**
	 * Method to set account password
	 * @param scan the name of the scanner
	 */
	public void changePassword(Scanner scan) {

		if(accNumber == 0 ) {

			System.out.println("No account password to change");

		} else {

			int computerNumber = (int) (Math.random()*100 + 1);
			int token;

			try {

				FileWriter myWriter = new FileWriter("C:\\Users\\Caleb Orhomre\\"
						+ "CST8116 Homework\\CST8116 Eclipse Workspace\\Lab 5\\token.txt");
				
				
				myWriter.write(Integer.toString(computerNumber));
				myWriter.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			do {

				System.out.println("Input token form the token file");
				token = scan.nextInt();

				if(token != computerNumber) {

					System.out.println("Wrong token");

				}

			} while((token != computerNumber));

			printInfo();

			System.out.print("Enter account number: ");
			getAccNum = scan.nextInt();

			do {

				try {

					System.out.print("Enter account passWord: ");
					accPass = scan.next();

					invalidpassowrd(accPass ,accounts.get(getAccNum - 1).passWord, "Invalid Password\nTry again" );
					count = 0;
				} catch(Exception e) {

					System.out.println(e.getMessage());
					count = 2;
				}

			} while(count > 1);

			System.out.print("Enter new passoword: ");
			accPass = scan.next();

			accounts.get(getAccNum - 1).setPassword(accPass);

			System.out.println("PassWord Changed");
		}
	}

	/**
	 * Method that get credit card for an account
	 * @param scan the name of scanneer
	 */
	public void getCreditCard(Scanner scan) {

		//if statement to print out an error message if there is not account
		if (accNumber == 0) {

			System.out.println("You need to create an account first");
		} else {

			printInfo();

			lastFour++;

			do {

				System.out.println("Enter account number");

				getAccNum = Account.inputInteger(scan, "Invalid Account option. Try again....\n Enter acccount number.");

				//try statement to catch exception
				try {


					unknownAcc(getAccNum);

					do {

						//try statement to catch invalid menu expection 
						try {

							System.out.println("1 - MasterCard ");
							System.out.println("2 - Visa");
							ans = Account.inputInteger(scan, "Invalid menu option. Try again... Wrong selection. Retry... \n 1 - MasterCard \n 2 - Visa");
							menu(ans, 2, 1,"Wrong selection. Retry...");
							count = 0;

						} catch(Exception e) {

							count = 2;
							System.out.println("Inavalid menu option. Try again.....");
						}

					} while (count > 1);

					accounts.get(getAccNum - 1 ).creditCard(ans, lastFour);
					count = 0;

				}catch(Exception e) {
					count = 2;
					System.out.println(e.getMessage());
				}

			} while(count > 1);

			printTitle(name);

			System.out.println( accounts.get( getAccNum - 1).toString() );
		}

	}

	/**
	 * PrintInfo method that prints info of account
	 */
	public void printInfo() {

		//System.out.println(accounts);

		//an if statement that checks if the count is equal zero and print an error message if the condition is true
		if ( accNumber == 0) {
			System.out.println("No account to disaplay");
		}else {

			printStars();

			System.out.println("                                            " +  name.toUpperCase() + " BANK");

			printTitle(name);

			for( int i = 0; i < accounts.size(); i++) {

				System.out.println( accounts.get(i).toString() );
				System.out.println();
			}
		}
	}

	/**
	 * Custom exception to check if account exist
	 * @param getCredit holds account number 
	 * @throws AccountNotFoundException account not found exception
	 */
	void unknownAcc(double getCredit)throws AccountNotFoundException{

		if( getCredit > accNumber || getCredit <= -1) {

			throw new AccountNotFoundException("Account number entered " + getAccNum + " not found. Try again...");

		}

	}

	/**
	 * Custom exception to check menu input range
	 * @param ans holds the menu input user entered
	 * @param value1 lowest menu range 
	 * @param value2 highest menu range
	 * @param message holds the message that will be printed out
	 * @throws menuValidationException menu validation exception
	 */
	void menu(int ans, int value1, int value2, String message) throws menuValidationException {

		if(ans > value1 || ans < value2) {

			throw new menuValidationException(message);

		}

	}

	void invalidpassowrd(String input, String original, String message) throws IncorrectPasswordExcption {

		if(!(input.equals(original))) {

			throw new IncorrectPasswordExcption(message);

		}

	}


}