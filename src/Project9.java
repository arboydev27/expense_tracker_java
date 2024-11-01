/**
 * @author Arboy Magomba
 * @version 1.3
 * Project 9
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Project9
{
	final private static int NUM_MONTHS = 4;
	final private static int NUM_ROWS = 100;
	final private static int NUM_COLS = 4;
	final private static String FILENAME = "Data.txt"; 

	public static void main(String[] args) throws FileNotFoundException
	{
		//final int NUM_MONTHS = 4; FIXME
		//final int NUM_ROWS = 100;
		//final int NUM_COLS = 4; 
		
		//TODO Store the names of the rows in an oversize array
		
		String[] rows = new String[100];
		
		//TODO Store the names of the columns in a perfect size array
		String[] cols = {"Jan", "Feb", "Mar", "Apr"};
		
		//TODO Create a two dimensional array to store the data. This array is both oversize and perfect size.  
		// The columns are perfect size
		// The rows are oversize
		double[][] data = new double[NUM_ROWS][NUM_COLS];
		

		
		String fileName;
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter the file name:");
		fileName = keyboard.nextLine();
		
		//TODO Call the readFile method
		readFile(rows, data, NUM_COLS, FILENAME );
		
		//TODO Call the performOperations method that handles most of the user interaction
		performOperations(rows, NUM_ROWS, cols, data, keyboard);
		
		//TODO Call the writeFile method
		writeFile(rows, NUM_ROWS, NUM_COLS, data, FILENAME);
		
		
		keyboard.close();
	}
	
	/** This method does most of the user input and calls the other methods.
	 * 
	 * @param rows The names of the rows in an oversize array.
	 * @param rowSize The number of rows in the oversize array (also the number of rows is data).
	 * @param cols The names of the columns in a perfect size array.
	 * @param data The amount spent on each category in each month. Data is oversize in rows, and perfect size in columns.
	 * @param input The Scanner that is connected to the console for user input/output.
	 * @return The number of rows that are in the rows and data array at the end of the method.
	 */
	// This method is longer than is ideal.  Since it deals with input and output, this is OK.
	public static int performOperations(String[] rows, int rowSize, String[] cols, double[][] data, Scanner input)
	{
		int menuChoice = getMenuChoice(input);
		
		while (menuChoice != 6)
		{
			double total = 0.0;
			if (menuChoice==1)
			{
				System.out.println("Enter the month");
				String month = input.nextLine();
				//TODO Call the findMonthlyTotal method
				total = findMonthlyTotal(cols, data, rowSize, month);

				System.out.println("The total is " + total);
			}
			else if (menuChoice==2)
			{
				System.out.println("Enter the item");
				String item = input.nextLine();
				//TODO Call the findItemTotal method
				total = findItemTotal(rows, rowSize, cols.length, data, item);

				System.out.println("The total is " + total);
			}
			else if (menuChoice==3)  // grand total
			{
				//TODO Call the findGrandTotal method
				total = findGrandTotal(data, rowSize, cols.length);

				System.out.println("The total is " + total);
			}
			else if (menuChoice==4)
			{
				System.out.println("Enter the lower amount");
				double lower = input.nextDouble();
				System.out.println("Enter the upper amount");
				double upper = input.nextDouble();
				//TODO Call the findRangeTotal method
				total = findRangeTotal(data, rowSize, cols.length, lower, upper);

				System.out.println("The total is " + total);
			}
			else if (menuChoice == 5)
			{
				System.out.println("What is the item?");
				String item = input.nextLine();
				System.out.println("Which month?");
				String month = input.nextLine();
				System.out.println("What is the amount");
				double amount = input.nextDouble();
				input.nextLine();
				
				//TODO Call the addToData method
				
				
			}

			// Priming read
			menuChoice = getMenuChoice(input);
		}
		return rowSize;
	}
	
	/** Update an existing row/column or add another row to the rows and data arrays.
	 * 
	 * @param rows The names of the rows (oversize array)
	 * @param rowSize The number of rows in the rows and data arrays that have been used.
	 * @param cols The  names of the columns (perfect size array).
	 * @param data The data that is stored for each row and column.
	 * @param item The item to be added to the array. This may go in an existing row or may form a new rows if item is
	 * not contained in rows.
	 * @param month The name of the column to be updated.
	 * @param amount The amount to be added to the existing element, or stored in the new element.
	 * @return The number of rows that have been used in rows and data.  This could be the same as rowSize, or may be
	 * one larger if the row name is not already in the rows array.
	 */
	public static int addToData(String[] rows, int rowSize, String[] cols, double[][] data, String item, String month, double amount)
	{	
		//TODO Write the body of this method
		 int itemIndex = findLabelIndex(rows, rowSize, item);

		    // If the item doesn't exist, add it to the rows array and data array
		    if (itemIndex == -1) {
		        if (rowSize < rows.length) {
		            rows[rowSize] = item;
		            itemIndex = rowSize;
		            rowSize++;
		        } else {
		            System.out.println("Cannot add more rows. Array is full.");
		            return rowSize;
		        }
		    }

		    // Find the index of the specified month in the cols array
		    int monthIndex = findLabelIndex(cols, cols.length, month);

		    // If the month is not found, print an error message and return rowSize
		    if (monthIndex == -1) {
		        System.out.println("Invalid month: " + month);
		        return rowSize;
		    }

		    // Update the data array with the specified amount
		    data[itemIndex][monthIndex] += amount;

		    return rowSize;
	}
	

	/** Find a given label in an array. The array may be either oversize or
	 * perfect size. In other words, this method may be used with both rows and cols.
	 * @param array The array to be searched. This array is not sorted and should not be sorted.
	 * @param arraySize The number of elements used in the array.
	 * @param label The label to find in the array.
	 * @return The index in the array where label was found, or -1 if label was not found. 
	 */
	// This method is written in such a way that it can be used for either an
	// overisze or perfect size array
	
	public static int findLabelIndex(String[] array, int arraySize, String label)
	{
		//TODO Write the body of this method
		for (int i = 0; i < arraySize; ++i)
		{
			if (array[i].equals(label)) {
				return i;
			}
		}
		
		return -1;
	}
	
	/** Find the total of the data elements for a given month.
	 * 
	 * @param cols The names of the columns (the months).
	 * @param data The data stored in each row and column.
	 * @param rowSize The number of rows in the data array.
	 * @param month The month where the data is sought.
	 * @return The sum of the values in the data array, in the column with label month.
	 */
	public static double findMonthlyTotal(String[] cols, double[][]data, int rowSize, String month)
	{
		//TODO Write the body of this method
		
	        int monthIndex = findLabelIndex(cols, cols.length, month);

	        if (monthIndex == -1) {
	            System.out.println("Invalid month: " + month);
	            return 0.0;  // or throw an exception, depending on your error-handling strategy
	        }

	        double monthlyTotal = 0.0;

	        for (int i = 0; i < rowSize; i++) {
	            monthlyTotal += data[i][monthIndex];
	        }

	        return monthlyTotal;
	}
	
	/** Find the total of all values in the data array between 0 and rowSize for all columns.
	 * 
	 * @param data An array of data.
	 * @param rowSize The number of rows that were used in the array.
	 * @param colSize The number of columns in the array.
	 * @return The sum of the values in all of the data array between indices 0,0 and rowsSize, colSize.
	 */
	public static double findGrandTotal(double[][]data, int rowSize, int colSize)
	{
		//TODO Write the body of this method
		double sum = 0.0;
		
		for (int row = 0; row < rowSize; ++row)
		{
			for (int col =  0; col < colSize; ++col)
			{
				sum += data[row][col];
			}
		}
		return sum; // stub // Sum is a double value 
	}
	
	/** Find the sum of all of the values in the array that are between lowerAmount and upperAmount (both inclusive).
	 * 
	 * @param data The array of data.
	 * @param rowSize The number of rows in the array that have been used.
	 * @param colSize The number of columns in the array that have been used.
	 * @param lowerAmount The lower bound of values to include (inclusive).
	 * @param upperAmount The upper bound of values to include (inclusive).
	 * @return
	 */
	public static double findRangeTotal(double[][]data, int rowSize, int colSize, double lowerAmount, double upperAmount)
	{
		//TODO Write the body of this method

		double sum = 0.0;
		
		for (int row = 0; row < rowSize; ++row)
		{
			for (int col =  0; col < colSize; ++col)
			{
				if (data[row][col] >= lowerAmount && data[row][col] <= upperAmount)
				{
					sum += data[row][col];
				}
			}
		}
		return sum;	 // Stub
	}
	
	/** Find the sum of all of the values in the data array with row label item.
	 * 
	 * @param rows The names of the rows.
	 * @param rowSize The number of rows in rows and data that were used.
	 * @param colSize The number of cols in data.
	 * @param data The data array.
	 * @param item The name of the row that should be summed.
	 * @return The sum of the values in the row that is labeled item.
	 */
	public static double findItemTotal(String[] rows, int rowSize, int colSize, double[][]data, String item)
	{
		//TODO Write the body of this method
		 int itemIndex = findLabelIndex(rows, rowSize, item);

		    if (itemIndex == -1) {
		        System.out.println("Invalid item: " + item);
		        return 0.0;  // or throw an exception, depending on your error-handling strategy
		    }

		    double itemTotal = 0.0;

		    for (int i = 0; i < colSize; i++) {
		        itemTotal += data[itemIndex][i];
		    }

		    return itemTotal;
	}
	
	/** Show the menu to the user and get a choice from the menu.
	 * 
	 * @param stdin The Scanner that is hooked up to the console.
	 * @return The number of the menu item selected.
	 */
	public static int getMenuChoice(Scanner stdin)
	{
		System.out.println("Choose one of the following options:");
		System.out.println("1. Find monthly total");
		System.out.println("2. Find item total");
		System.out.println("3. Find grand total");
		System.out.println("4. Find range total");
		System.out.println("5. Add to table");
		System.out.println("6. Exit program");
		
		int choice = stdin.nextInt();
		stdin.nextLine();
		
		while (choice < 0 || choice > 6)
		{
			System.out.println("Please enter a value from 1 to 6");
			choice = stdin.nextInt();
			stdin.nextLine();
		}
		
		return choice;
	}
	
	/** Read the contents of a file with the given fileName into the rows and data array. The format of the file is as follows:
	 * The number of rows <newline>
	 * For each rows in the data array:
	 * The row header then colSize double values, ending in a newline character.
	 * 
	 * @param rows The oversize array that contains the names of the rows. This array must be constructed
	 * before the method is called.
	 * @param data The array that contains a two-dimensional group of values. This array  must be constructed before the
	 * method is called.
	 * @param colSize The number of columns in data.
	 * @param fileName The name of the file that contains the data.
	 * @return The number of rows that were read in from the file.
	 * @throws FileNotFoundException If the file with the given fileName cannot be found in the Project directory.
	 */
	public static int readFile(String[] rows, double[][] data, int colSize, String fileName) throws FileNotFoundException
	{
		Scanner file = new Scanner(new File(fileName));
		
		int index;
		int size = file.nextInt();
		file.nextLine(); // get rid of newline character
		
		for (index=0; index < size && file.hasNextLine() && index < rows.length; ++index)
		{
			// Each row has a header and then twelve double values to be read in
			rows[index] = file.next();
			for (int count=0; count < colSize; ++count)  // explain that test in handout
			{
				data[index][count] = file.nextDouble();
			}
			file.nextLine(); // get rid of that pesky newline
		}
		
		file.close();
		return index;
	}
	
	/** Write the rows and data array out to a file with the given name.
	 * 
	 * @param rows The array that contains the name of the rows.
	 * @param rowSize The number of rows that are used (both in rows and data).
	 * @param colSize The number of columns that are needed.
	 * @param data A two-dimensional array that contains the amount spent in that category/month.
	 * @param fileName The name of the file to write.
	 * @throws FileNotFoundException If it is impossible to write to this file.
	 */
	public static void writeFile(String[] rows, int rowSize, int colSize, double[][] data, String fileName) throws FileNotFoundException
	{
		PrintWriter pw = new PrintWriter(fileName);
		
		pw.write(rowSize+"\n");
		for (int row = 0; row < rowSize; ++row)
		{
			pw.write(rows[row] + " ");
			for (int col = 0; col < colSize; ++col)
			{
				pw.write(data[row][col] + " ");
			}
			pw.write("\n");
		}
		
		pw.close();
	}
}
