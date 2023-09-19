import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GameModel {

	int[][] binaryArray = { {0,0,1,0,0}, {0,0,1,0,0}, {1,1,1,1,1}, {0,1,1,1,0}, {0,1,0,1,0} };
	int[][] saveArray = { {0,0,1,0,0}, {0,0,1,0,0}, {1,1,1,1,1}, {0,1,1,1,0}, {0,1,0,1,0} };
	int[][] matrix;
	BufferedWriter writer;

	int dimRow = 5;
	int dimCol = 5;
	/*
	 * 10 = Correct selected mark
	 * 11 = Wrong selected mark
	 * 12 = Correct
	 * 13 = Wrong
	 */

	static int[][] colVal = new int[5][5];
	static int[][] rowVal = new int[5][5];
	int val = 0;
	int totalPass = 0;

	/**
	 * The Game model class default constructor.
	 */
	public GameModel() {

		solveColNum();
		countPass();
		printAnswer();
		solveRowNum();
	}


	/**
	 * This method basically counts the amount of correct tile there is.
	 */
	public void countPass() {
		totalPass = 0;
		for (int i = 0; i < binaryArray.length; i++) {
			for (int j = 0; j < binaryArray[i].length; j++) {
				if(binaryArray[i][j] == 1)
					totalPass += 1;
			}
		}
	}

	/**
	 * This method prints out the solution of the game to the console. this will be used by developers.
	 */
	public void printAnswer() {
		for (int i = 0; i < binaryArray.length; i++) {
			for (int j = 0; j < binaryArray[i].length; j++) {

				System.out.print(binaryArray[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println();
		System.out.println();
	}

	/**
	 * This method is used to solve for the top numbers.
	 */
	public void solveColNum() {

		colVal = new int[dimRow][dimCol]; 
		int col = 0;
		for (int j = 0; j < binaryArray[0].length; j++) { 
			col = 0;
			for (int i = 0; i < binaryArray.length; i++) {
				int value = binaryArray[i][j];
				if(value == 1) 
					colVal[col][j] = colVal[col][j] + 1;
				else
					if(colVal[col][j] != 0)
						col++;
			}
		}
	}

	/**
	 * This method is used to solve for the side numbers.
	 */
	public void solveRowNum() {

		rowVal = new int[dimRow][dimCol]; 
		int row = 0;
		for (int i = 0; i < binaryArray.length; i++) { 
			row = 0;
			for (int j = 0; j < binaryArray[0].length; j++) {
				int value = binaryArray[i][j];
				if(value == 1) 
					rowVal[i][row] = rowVal[i][row] + 1;
				else
					if(rowVal[i][row] != 0)
						row++;
			}
		}
	}

	/**
	 * This method generates a binary game.
	 */
	public void generateBinary() {

		binaryArray = new int[dimRow][dimCol]; 
		Random rand = new Random();

		for (int i = 0; i < binaryArray.length; i++)
			for (int j = 0; j < binaryArray[i].length; j++) 
				binaryArray[i][j] = rand.nextInt(2); 


		saveArray = new int[dimRow][dimCol]; 

		for (int i = 0; i < saveArray.length; i++)
			for (int j = 0; j < saveArray[i].length; j++) 
				saveArray[i][j] = 0;

		solveColNum();
		solveRowNum();
		printAnswer();
		countPass();
	}

	/**
	 * This method saves a configuration. 
	 * @param file The file that the configuration is to be saved to.
	 * @param dim The dimension of the configuration. 
	 */
	public void save(File file, String dim) {

		if(file == null) 
			return;

		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(dim);
			writer.newLine();
			for (int[] row : saveArray) {
				for (int i = 0; i < row.length; i++) {
					writer.write(row[i] + " ");
				}
				writer.newLine();
			}

			saveCurrentBinary();
			writer.close();
			System.out.println("Values saved to output.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to save the current generated binary.
	 */
	public void saveCurrentBinary() {

		// printAnswer();

		try {

			writer.newLine();
			for (int[] row : binaryArray) {
				for (int i = 0; i < row.length; i++) {
					writer.write(row[i] + " ");
				}
				writer.newLine();
			}
			// writer.close();
			System.out.println("Values saved to output.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to load a configuration.
	 * @param file The file to be loaded
	 * @return The dimension of the configuration read.
	 */
	public String load(File file) {
		if(file == null)
			return null;
		
		String dim = null;
		String[] arrOfStr = null;

		/*
		 * 10 = Correct selected mark
		 * 11 = Wrong selected mark
		 * 12 = Correct
		 * 13 = Wrong
		 */

		int i = 0, j = 0;
		try {
			Scanner sc = new Scanner(new File(file.toString()));
			dim = sc.next();
			arrOfStr = dim.split("x", 2);
			matrix = new int[Integer.parseInt(arrOfStr[0])][Integer.parseInt(arrOfStr[1])];
			binaryArray = new int[Integer.parseInt(arrOfStr[0])][Integer.parseInt(arrOfStr[1])];
			sc.nextLine();
			while (sc.hasNextLine() && i < Integer.parseInt(arrOfStr[1])) {
				String[] row = sc.nextLine().split(" ");
				for (String s : row) {
					matrix[i][j++] = Integer.parseInt(s);
				}
				i++;
				j = 0;
			}

			sc.nextLine();

			i = 0;
			j = 0;

			while (sc.hasNextLine() && i < Integer.parseInt(arrOfStr[1])) {
				String[] row = sc.nextLine().split(" ");
				for (String s : row) {
					binaryArray[i][j++] = Integer.parseInt(s);
				}
				i++;
				j = 0;
			}

			sc.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		saveArray = new int[Integer.parseInt(arrOfStr[0])][Integer.parseInt(arrOfStr[1])];

		for (i = 0; i < saveArray.length; i++)
			for (j = 0; j < saveArray[i].length; j++) 
				saveArray[i][j] = 0; 
		return dim;
	}

	/**
	 * This method is used to print an array passed in. This method is to be used by the programmer.
	 * @param array The array that is to be printed.
	 */
	public static void printArray(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}
	/**
	 * This method return the value at a specific index in the binary array.
	 * @param col Column index in the binary array.
	 * @param row Row index in the binary array.
	 * @return Returns the value in the specified index.
	 */
	public int getArray(int col, int row) {
		return binaryArray[col][row];
	}
}
