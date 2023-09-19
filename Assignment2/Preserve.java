package Assignment2;

import java.util.Formatter;
import java.util.Scanner;

/**
 * This class extends food item class and it simply print and get the required output
 * CET - CS Academic Level 3
 * Assignment 2
 * Student Name: Caleb Orhomre
 * Student Number: 41040764
 * Course: CST8130 - Data Structures
 * @author Caleb Orhomre
 * 
 */
public class Preserve extends FoodItem {

	/**
	 * Variable that hold the jar size
	 */
	private int jarSize;
	
	public Preserve() {
		
	}


	/**
	 * This method add item to the inventory
	 * @param scan This holds the scanner
	 */
	@Override
	public boolean addItem(Scanner scan, boolean fromFile) {
		
		if(!fromFile) {
			
			//calling the super class add item method
			super.addItem(scan, false);
			
			System.out.print("Enter the size of the jar in millilitres: ");
			jarSize = Inventory.inputInteger(scan, "Invalid entry\nEnter the size of the jar in millilitres: ", false);
			
		} else if(fromFile) {
			
			super.addItem(scan, true);
			
			jarSize = scan.nextInt();
		}

		
		return false;
	}
	
	/**
	 * This method writer the jar size into a specified file
	 * @param writer This variable is used to hold the formatter object in order to write to file
	 */
	@Override
	public void outPutItem(Formatter writer) { 
		
		writer.format("p\n"); 
		super.outPutItem(writer); 
		writer.format("%d\n", jarSize); 
	}

	/**
	 * This method prints out required output
	 */
	@Override
	public String toString() {
		
		return super.toString() + " size: " + jarSize + "mL";
	}
}

