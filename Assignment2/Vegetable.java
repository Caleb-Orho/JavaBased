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
public class Vegetable extends FoodItem {

	/**
	 * Variable that holds the fram name
	 */
	private String farmName;
	
	public Vegetable() {
			
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

			System.out.print("Enter the name of the farm supplier: ");
			farmName = scan.nextLine();
			
		} else if(fromFile) {

			super.addItem(scan, true);

			farmName = scan.nextLine() + scan.nextLine();
			
		}
		
		return false;
	}
	
	/**
	 * This method writer the orchard name into a specified file
	 * @param writer This variable is used to hold the formatter object in order to write to file
	 */
	@Override
	public void outPutItem(Formatter writer) { 

		writer.format("v\n"); 
		super.outPutItem(writer); 
		writer.format("%s\n", farmName); 
	}

	/**
	 * This method prints out required output
	 */
	@Override
	public String toString() {
		
		return super.toString() + "  farm supplier: " + farmName;
	}

}