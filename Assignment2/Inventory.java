package Assignment2;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import inventorySystem.CouldNotBuyItem;



public class Inventory {


	/**
	 * An ArrayList of food item object to hold required fields of the food item different type
	 */
	private ArrayList<FoodItem> inventory; 

	/**
	 * Formatter to output the values in the array.
	 */
	private Formatter output;

	/**
	 * This variable holds the mid value of the array.
	 */
	private int midValue;

	public Inventory() {

		inventory = new ArrayList<FoodItem>(20);
				
	}

	/**
	 * This method uses the compare to method from the comparable class to see if an item code already exists
	 * @param item This variable holds the food item object that will be passed to this method
	 * @return -1 if the item code does not exist or 1 if it exists
	 */
	public int alreadyExists(FoodItem item) {

		for(int i = 0; i < inventory.size(); i++) {

			//returning index if the item code already exists
			if(inventory.get(i).compareTo(item) == 1) return i;

		}

		//Returning a -1 if the item code does not exist
		return -1;
	}


	/**
	 * This method is used to add items into the food item object array.
	 * @param scan This holds the scanner
	 * @param fromFile This variable is used to check if the process of this method is to read from file or keyboard
	 * @return true if the program execution was successful or false if it wasnt
	 */
	public boolean addItem(Scanner scan, boolean fromFile) {

		int check = 0;
		boolean pass = false;

		//checking to see if the required process is to read information through keyboard
		if(!fromFile) {

			while(pass == false) {

				try {

					System.out.print("Do you wish to add a fruit(f), vegetable(v), preserve(p) or supplements(s)? ");
					String option = scan.next();

					//validating the user input
					InvalidInput("string",option, "Invalid entry", scan, 0);

					switch (option) {

					case "f":

						//building a fruit object
						FoodItem fruit = new Fruit();
						fruit.inputCode(scan, false);

						//checking to see if code already exists
						check = alreadyExists(fruit);

						//printing out an error if the code exists
						if(check != -1) System.out.println("Code already exists");
						if(check != -1) return false;

						fruit.addItem(scan, false);

						inventory.add(fruit);

						break;
					case "v":

						FoodItem veg = new Vegetable();
						veg.inputCode(scan, false);
						
						//checking to see if code already exists
						check = alreadyExists(veg);
						
						//printing out an error if the code exists
						if(check != -1) System.out.println("Code already exists");
						if(check != -1) return false;
						
						veg.addItem(scan, false);
						
						inventory.add(veg);
						
						break;
					case "p":

						FoodItem pre = new Preserve();
						pre.inputCode(scan, false);

						//checking to see if code already exists
						check = alreadyExists(pre);

						//printing out an error if the code exists
						if(check != -1) System.out.println("Code already exists");
						if(check != -1) return false;

						pre.addItem(scan, false);

						inventory.add(pre);

						break;
					case "s":

						FoodItem supplement = new Supplement();
						supplement.inputCode(scan, false);

						//checking to see if code already exists
						check = alreadyExists(supplement);

						//printing out an error if the code exists
						if(check != -1) System.out.println("Code already exists");
						if(check != -1) return false;

						supplement.addItem(scan, false);

						inventory.add(supplement);

						break;
					}

					pass = true;
					
				} catch(Exception e) {

					pass = false;
					System.out.println(e.getMessage());
				}
			} 

		} 
		
		//checking to see if the required process is to read information from file
		else if(fromFile) {

			String type = null;

				//while loop to loop the program while the file still has information to read
				while(scan.hasNextLine()) {

					type = scan.nextLine();

				switch (type){

				case "f":

					FoodItem inv = new Fruit();

					inv.inputCode(scan, true);

					//checking to see if code already exists
					check = alreadyExists(inv);
					if(check != -1) return false;

					inv.addItem(scan, true);
					inventory.add(inv);

					break;
				case "v":

					FoodItem veg = new Vegetable();
					veg.inputCode(scan, true);

					//checking to see if code already exists
					check = alreadyExists(veg);
					if(check != -1) return false;

					veg.addItem(scan, true);
					inventory.add(veg);

					break;
				case "p":

					FoodItem pre = new Preserve();
					pre.inputCode(scan, true);

					//checking to see if code already exists
					check = alreadyExists(pre);
					if(check != -1) return false;

					pre.addItem(scan, true);
					inventory.add(pre);

					break;
				case "s":

					FoodItem supplement = new Supplement();
					supplement.inputCode(scan, true);

					//checking to see if code already exists
					check = alreadyExists(supplement);
					if(check != -1) return false;

					supplement.addItem(scan, true);
					inventory.add(supplement);

					break;
				}
			}
		}

		Collections.sort(inventory);

		return true;
	}
	
	/**
	 * This method is used to buy and sell available items.
	 * @param scan This holds the scanner
	 * @param buyOrSell This variable is used to check if the required task is buying or selling.
	 * @return true if the program execution was successful or false if it wasnt
	 */
	public boolean updateQuantity(Scanner scan, boolean buyOrSell) {

		try {

			//checking if there are items to buy or sell in the inventory
			couldNotBut(buyOrSell);

			//Foot Item object
			FoodItem item = new FoodItem();

			//Getting the code
			item.inputCode(scan, false);

			//Checking if the code already exist
			int index = alreadyExists(item);

			//buying
			if(buyOrSell == true) {

				//if statement to print out an error if the code is not fine
				if(index == -1) System.out.println("Code not found in inventory...\n"
						+ "Error...could not buy item");

				if(index == -1) return false;

				System.out.print("Entar valid quantity to buy: ");
				int quantity = inputInteger(scan, "Invalid quantity\nEntar valid quantity to buy: ", false);

				boolean validation = inventory.get(index).updateItem(quantity);

				if(validation == false) System.out.println("Invalid quantity...\nError...could not buy item"); return false;


			} 

			//selling
			else if(buyOrSell == false) {

				//if statement to print out an error if the code is not fine
				if(index == -1) System.out.println("Code not found in inventory...\n"
						+ "Error...could not sell item");

				if(index == -1) return false;

				System.out.print("Entar valid quantity to sell: ");
				int quantity = inputInteger(scan, "Invalid quantity\nEntar valid quantity to sell: ", false);

				boolean validation = inventory.get(index).updateItem(-quantity);

				if(validation == false) System.out.println("Insufficient stock in inventory...\nError...could not sell item"); return false;

			}

		} catch(InputMismatchException ex) {

			if(buyOrSell == true) System.out.println("Invalid quantity...\nError...could not buy item");

			if(buyOrSell == false) System.out.println("Invalid quantity...\nError...could not sell item");

		} catch(Exception e) {

			System.out.println(e.getMessage());

		}
		return false;
	}

	/**
	 * This method calls the binary search method to search for an inputed item code and prints out its related attributes
	 * @param scan This variable holds the scanner object
	 */
	public void searchForItem(Scanner scan) {

		FoodItem item = new FoodItem();
		item.inputCode(scan, false);

		//calling the binary search method and assigning its result to variabel index
		int index = BinarySearch(item.getItemCode(), 0, inventory.size() - 1);

		//checking if the is not -1 in order to print out the require output
		if(index != -1) System.out.println(inventory.get(index).toString());

		//checking if index is -1 and prining out an error message
		if(index == -1) System.out.println("Code not found in inventory...");
	}

	/**
	 * This method uses binary search algorithm to search for an inputed 
	 * item code and return its index when found.
	 * @param targetCode This variable holds the target item code
	 * @param left This variable holds the beginning of the array
	 * @param right This variable holds the end of the array
	 * @return The index of the item, if not found return -1.
	 */
	public int BinarySearch(int targetCode, int left, int right) {

		//getting the midvalue
		midValue = right - (right - left) / 2;

		//if statement to return a -1 if the code is not found 
		if(left > right) return -1;

		//checking if the target code is equal the inventory object middle value
		if(targetCode == inventory.get(midValue).getItemCode()) return midValue;

		else if(targetCode < inventory.get(midValue).getItemCode()) return BinarySearch(targetCode, left, midValue - 1);

		else return BinarySearch(targetCode, midValue + 1, right);

	}

	/**
	 * This method saves required information into a specified file
	 * @param scan This variable holds the scanner object
	 */
	public void saveToFile(Scanner scan) {

		try {

			System.out.print("Enter the filename to save to: ");
			String fileName = scan.next();

			output = new Formatter(fileName);

			//for loop to write information to file
			for(int i = 0; i < inventory.size(); i++) {

				inventory.get(i).outPutItem(output);
			}

			output.close();

		} catch(IOException e) {

			System.out.println("Unable ot save to file");

		}

	}

	/**
	 * This method reads information from a file by calling the add item method and passing a true boolean value
	 * @param scan This variable holds the scanner object
	 */
	public void readFromFile(Scanner scan) {

		try {

			System.out.print("Name of file to read from: ");
			String fileName = scan.next();


			Scanner read = new Scanner(Paths.get(fileName));

			boolean pass = addItem(read, true);
			
			//if(!pass) System.out.println("Error Encountered while reading the file, aborting...");
			
			//if(pass == false) throw new IOException();
			
			if(pass == false) System.out.println("Item code already exists\n"
					+ "Error Encountered while reading the file, aborting...");


		} catch(IOException e) {

			//System.out.println("Error Encountered while reading the file, aborting...");
			System.out.println("File Not Found, ignoring...");


		} 
	}


	/**
	 * This method prints out required output
	 */
	@Override
	public String toString() {

		System.out.println("Inventory: ");
		String output = "";

		for( int i = 0; i < inventory.size(); i++) {
			
			output+= inventory.get(i).toString() + "\n";

		}


		return output;


	}


	/**
	 * This method validates user input and makes sure they input an integer when needed
	 * 
	 * This method validates user input.
	 * @param action This variable holds the action the user want to accomplish to determine which comparison to make
	 * @param option This variable holds the input the user inputed
	 * @param message This variable holds the message that should be printed out if the user inputed a wrong value
	 * @param scan This holds the scanner
	 * @param num This variable holds any integer that was passed in
	 * @throws InvalidInputException This exception prints an error message
	 */
	static void InvalidInput(String action, String option, String message, Scanner scan, int num) throws InvalidInputException {

		//if statement to validate user menu option
		if(action.equalsIgnoreCase("string")) {

			//checking if the user input is equal to any of the inventory option
			if(!(option.equalsIgnoreCase("f") || option.equalsIgnoreCase("v") || option.equalsIgnoreCase("p") || option.equalsIgnoreCase("s"))) {

				throw new InvalidInputException(message);

			}

		}
	}
	
	/**
	 * This method checks if there are items in the inventory
	 * @param action This variable is a boolean that specify if the user is buying or selling
	 * @throws CouldNotBuyItem Exception that prints out message
	 */
	void couldNotBut(boolean action) throws CouldNotBuyItem {

		if(inventory.size() == 0) {

			if(action == false) throw new CouldNotBuyItem("Error.....Could not sell item");

			if(action == true) throw new CouldNotBuyItem("Error.....Could not buy item");



		}
	}

	/**
	 * This method validate user input, it makes sure the user input an integer
	 * @param scan This holds the scanner
	 * @param message This variable holds the error message
	 * @param check This variable check if the value inputed is less than 0
	 * @return This method return the inputed value
	 */
	public static int inputInteger(Scanner scan, String message, boolean check) {

		boolean isInputBad = true;
		boolean hasNextInt;
		int value = 0;

		while(isInputBad) {
			hasNextInt = scan.hasNextInt();
			if(hasNextInt) {

				value = scan.nextInt();

				if(check != true) {

					if(value < 0) {

						System.out.print(message);
						value = inputInteger(scan, message, check);

					}

				}

				isInputBad = false; // break out of loop
			}
			else {

				System.out.print(message);

				scan.next();
				scan.nextLine(); // clean up input stream
			}

		} 
		return value;
	}


	/**
	 * This method validate user input, it makes sure the user input an float
	 * @param scan This holds the scanner
	 * @param message This variable holds the error message
	 * @return This method return the inputed value
	 */
	public static float inputFloat(Scanner scan, String message) {

		boolean isInputBad = true;
		boolean hasNextFloat;
		float value = 0;

		while(isInputBad) {
			hasNextFloat = scan.hasNextFloat();
			if(hasNextFloat) {

				value = scan.nextFloat();


				if(value < 0) {

					System.out.print(message);
					value = inputFloat(scan, message);

				}

				isInputBad = false; // break out of loop
			}
			else {

				System.out.print(message);

				scan.next();
				scan.nextLine(); // clean up input stream
			}

		} 
		return value;
	}

}
