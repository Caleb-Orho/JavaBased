package Assignment2;

import java.util.Formatter;
import java.util.Scanner;

/**
 * This the the class that prints and accept requied input form user to add item, this class also updates the item quantity and prints out the required output. it also reads and write from files and implement comparator to sort items and also compare objects
 * CET - CS Academic Level 3
 * Assignment 2
 * Student Name: Caleb Orhomre
 * Student Number: 41040764
 * Course: CST8130 - Data Structure
 * @author Caleb Orhomre
 *
 */
public class FoodItem implements Comparable<FoodItem> {

	/**
	 * This variable holds the item code 
	 */
	private int itemCode;

	/**
	 * This variable holds the item name
	 */
	private String itemName;

	/**
	 * This variable holds the item price
	 */
	private double itemPrice;

	/**
	 * This variable hold the amount of item quantity in stock
	 */
	private int itemQuantityInStock;

	/**
	 * This variable holds the value of the item cost
	 */
	private double itemCost;

	public FoodItem() {

	}
	
	/**
	 * Getter to return the item code
	 * @return The required item code
	 */
	public int getItemCode() { 

		return itemCode; 

	}

	/**
	 * This method is used to update the amount of items remaining in stock
	 * @param amount This variable holds the amount thats needed to be added or subtracted
	 * @return true if the program execution was successful or false if it wasnt
	 */
	public boolean updateItem(int amount) {

		if(amount < 0) amount = amount * -1;

		//checking if the amount is less than 0 or grater than the item in stock
		if(amount > itemQuantityInStock) return false;

		amount = amount * -1;

		//updating the quantity in stock if the condition is true
		if (!(amount > itemQuantityInStock)) itemQuantityInStock = itemQuantityInStock + amount; return true;


	}

	/**
	 * Reads a valid itemCode from the Scanner object
	 * @param scan This holds the scanner
	 * @param fromFile This variable is used to check if the process of this method is to read from file or keyboard
	 * @return true if the program execution was successful or false if it wasnt
	 */
	public boolean inputCode(Scanner scan, boolean fromFile) {

		if(!fromFile) {
			
			System.out.print("Enter the code for the item: ");

			//validating the user input
			itemCode = Inventory.inputInteger(scan, "Invalid code \nEnter the code for the item: ", true);
			
		} else if(fromFile) {
			
			itemCode = scan.nextInt();
			
		}

		return false;

	}

	/**
	 * Abstract method to add items to inventory.
	 * @param scan This holds the scanner
	 * @param fromFile This variable is used to check if the process of this method is to read from file or keyboard
	 * @return true if the program execution was successful or false if it wasnt
	 */
	public boolean addItem(Scanner scan, boolean fromFile) {

		if(!fromFile) {
			
			scan.nextLine();

			System.out.print("Enter the name for the item: ");
			itemName = scan.nextLine();

			System.out.print("Enter the quantity for the item: ");
			itemQuantityInStock = Inventory.inputInteger(scan, "Invalid entry\nEnter the quantity of the item: ", false);


			System.out.print("Enter the cost of the item: ");
			itemCost = Inventory.inputFloat(scan, "Invalid entry\nEnter the cost of the item: ");


			System.out.print("Enter the sales price of the item: ");
			itemPrice = Inventory.inputFloat(scan, "Invalid entry\nEnter the sales price of the item: ");

			scan.nextLine();
			
		} else if(fromFile) {
			
			this.itemName = scan.nextLine() +  scan.nextLine();
			this.itemQuantityInStock = scan.nextInt();
			this.itemCost = scan.nextDouble();
			this.itemPrice = scan.nextDouble();	
		}



		return false;

	}

	/**
	 * This method compares the passed in food item object to itself and see if they are equal and if they are not equal it sorts with the other elements 
	 */
	@Override
	public int compareTo(FoodItem item) {

		//checking to see if the object passed in is equal with this itself
		if(this.itemCode == item.itemCode) return 1;

		//sorting the elements if the first condition was false
		else return this.itemCode - item.itemCode;

	}
	
	/**
	 * This method writes the required information into file
	 * @param Writer This variable hold the Formatter object in order to write to file
	 */
	public void outPutItem(Formatter Writer) {
		
		Writer.format("%d\n", itemCode); 
		Writer.format("%s\n", itemName); 
		Writer.format("%d\n", itemQuantityInStock);
		Writer.format("%.2f\n", itemCost); 
		Writer.format("%.2f\n", itemPrice);
		
	}

	/**
	 * This method prints out required output
	 */
	@Override
	public String toString() {

		return  "Item: " + itemCode + " " + itemName + " " + itemQuantityInStock + " Price: $" + String.format("%.2f", itemPrice) +
				" Cost: $" + String.format("%.2f", itemCost);
	}

}
