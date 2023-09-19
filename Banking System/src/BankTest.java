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
import java.util.Scanner;

public class BankTest {

	public static void main(String[] args) {

		
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter name of Bank: ");
		String name = scan.next();
		
		Bank bank = new Bank(name);
		bank.readData(scan);
		int isTrue = 0;	

		do {

			//variable to hold the option of menu user chose 
			int opt = 0;

			do {
				
				System.out.println("What do you want to do?");
				System.out.println("1. Create Account");
				System.out.println("2. Transactions");
				System.out.println("3. Print all Accounts");
				System.out.println("4. Apply for Credit Card");
				System.out.println("5. Delete account");
				System.out.println("6. Change account password");
				System.out.println("7. Get account statement");
				System.out.println("8. Write information");
				System.out.println("9. Exit");

				if(scan.hasNextInt()) {
					opt = scan.nextInt();
					//opt = Account.inputInteger(scan, "Invalid menu option. Try again...");
				}else {
					System.out.println("Invalid menu option. Try again...");
					scan.next();
				}

			} while ( opt < 1 || opt > 10 );

			switch(opt) {
			case 1:

				//calling the read account method to read account detail and passing the scan arguments 
				bank.readAccount(scan);
				//setting the do loop control variable to 1 in order to loop the scope of the do statement again
				isTrue = 1;
				break;
			case 2:

				//calling the method to run transactions
				bank.trancsactions(scan);
				isTrue = 1;
				break;
			case 3:

				//calling the method to print accounts 
				bank.printInfo();
				isTrue = 1;
				break;
			case 4:

				//calling the method to get a credit card
				bank.getCreditCard(scan);
				isTrue = 1;
				break;
			
			case 5:
				
				bank.deleteAccount(scan);
				isTrue = 1;
				break;
				
			case 6:
				
				bank.changePassword(scan);
				isTrue = 1;
				break;
				
			case 7:
				
				bank.getAccStatement(scan);
				isTrue = 1;
				break;
				
			case 8:
				
				isTrue = 1;
				break;
			case 9:
				
				System.out.println("GoodBye......Have a nice day");
				System.out.println("Program by Caleb Orhomre");
				isTrue = 0;
				break;
			}
			
			bank.write(scan);

		} while(isTrue > 0);

		scan.close();
	}
}
