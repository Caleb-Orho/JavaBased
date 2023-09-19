package Assignment2;

import java.util.Formatter;
import java.util.Scanner;

/**
 * This class extends food item class and it simply print and get the required output
 * CET - CS Academic Level 3
 * Assignment 1
 * Student Name: Caleb Orhomre
 * Student Number: 41040764
 * Course: CST8130 - Data Structures
 * @author Caleb Orhomre
 * 
 */
public class Supplement extends FoodItem {

	
	/**
	 * Variable that holds the fram name
	 */
	private int proteinGram;
	
	public Supplement() {
			
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
			
			System.out.print("Enter the amount of protein in grams: ");
			proteinGram = Inventory.inputInteger(scan, "Invalid entry\nEnter the amount of protein in grams: ", false);
			
		} else if(fromFile) {
			
			super.addItem(scan, true);
			
			proteinGram = scan.nextInt();
			
		}
		
		return false;
	}
	
	/**
	 * This method writer the protein gram into a specified file
	 * @param writer This variable is used to hold the formatter object in order to write to file
	 */
	@Override
	public void outPutItem(Formatter writer) { 
		
		writer.format("s\n");
		super.outPutItem(writer); 
		writer.format("%d\n", proteinGram); 
	}

	/**
	 * This method prints out required output
	 */
	@Override
	public String toString() {
		
		return super.toString() + "  Amount of protein: " + proteinGram + "G";
	}
}
