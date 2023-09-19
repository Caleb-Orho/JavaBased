package Assignment2;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the driver class that calls required method based on the user input 
 * CET - CS Academic Level 3
 * Assignment 2
 * Student Name: Caleb Orhomre
 * Student Number: 41040764
 * Course: CST8130 - Data Structure
 * @author Caleb Orhomre
 *
 */
public class Assign2 {

	/**
	 * This is the main method that does the calling for the required task and it also hold the object of the inventory class.
	 * @param args array of sequence of characters
	 */
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Inventory inv = new Inventory();
		int option = 0;
		int pass = 0;

		do {

			do {

				try{

					//calling the displaying menu method
					displayMenu();
					option = scan.nextInt();

					//if statement to validate user input
					if(option > 8 || option <= 0) {

						System.out.println("...Invalid input, please try again...");
						pass = 1;

					} else {

						//setting the pass value to to false to terminate the loop
						pass = 0;
						scan.nextLine();

					}

				} catch(InputMismatchException  ex) {

					pass = 1;

					System.out.println("...Invalid input, please try again...");
					scan.nextLine();

				}


			}while(pass == 1);

			switch(option){

			case 1:

				//calling the additem method 
				inv.addItem(scan, false);
				break;
			case 2:

				System.out.println(inv.toString());
				break;
			case 3:

				//calling the update quantity method to buy item
				inv.updateQuantity(scan, true);
				break;
			case 4:

				//calling the update quantity method to sell item
				inv.updateQuantity(scan, false);
				break;
			case 5:

				inv.searchForItem(scan);
				break;
			case 6:

				inv.saveToFile(scan);
				break;
			case 7:

				inv.readFromFile(scan);
				break;
			case 8:

				System.out.println("Exiting....GoodBye......Have a nice day");
				System.out.println("Program by Caleb Orhomre");
				break;

			}

		} while(option != 8);
	}

	/**
	 * This method prints out the menu
	 */
	public static void displayMenu() {

		//outputting menu option
		System.out.print("Please select one of the following:\n"
				+ "1: Add Item to Inventory.\n"
				+ "2: Display Current Inventory.\n"
				+ "3: Buy Item(s).\n"
				+ "4: Sell Item(s).\n"
				+ "5: Search for item.\n"
				+ "6: Save Inventory to File.\n"
				+ "7: Read Inventory from File.\n"
				+ "8: To Exit. \n"
				+ "> ");
	}

}
