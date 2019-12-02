
/**
 *To sort using both quick Sort and shell sort methods to sort 100 random integers from an input file and exported sorted array to an output file. 
 *Jedidiah Prall and Calvin Nguyen 
 *Eclipse Java IDE
 *gallivant: wander aimlessly in search of pleasure.
 *"Yesterday is History. Tomorrow is a mystery. Today is a gift. That’s why it is called the present" — Alice Morse Earle, (4.1851) 
 *@author Jedidiah Prall and Calvin Nguyen 
 *@version Project 6
 *@class JedidiahPrall_CalvinNguyen_06
 */

import java.io.File;                   // Defines a file
import java.io.IOException;            // exception related to input and output
import java.io.FileNotFoundException;  // Exception related to when the file is not found
import java.io.PrintWriter;            // Used to print to the output file
import java.util.Scanner;              // Input stream

public class JedidiahPrall_CalvinNguyen_06 {

	private static String INPUT_FILE = "Project_06_Input.txt";     // Input file, declared in main method
	private static String OUTPUT_FILE1 = "Project_06_Output1.txt"; /* Output file for shell Sort, 
	                                                                   declared in main method*/
	private static String OUTPUT_FILE2 = "Project_06_Output2.txt"; /* Output file for quickSort, 
                                                                      declared in main method*/
	private static int length = 100;                               // Length of array
	private static int array[];                                    // Integer array for unsorted numbers
	private static int[] sortedArray ;                             // array for sorted numbers
	private static int[] quickSortArray;
	private static PrintWriter output;                             // Output

	public static void main(String[] args) throws IOException {

		INPUT_FILE = "Project_06_Input.txt";
		OUTPUT_FILE1 = "Project_06_Output1.txt";
		

		processInput();

		shellSort(sortedArray);

		quickSort(quickSortArray);
		
		generateReport();

	}
/************************************************************************************************************/
	// Start of processInput method
	/**
	 * Uses scanner to read input file and convert it to an integer array to be sorted.
	 */
	public static void processInput() throws IOException {

		int lineNum = 0;
		
		File file = new File(INPUT_FILE);

		Scanner fileReader;

		try {

			fileReader = new Scanner(file);

		} catch (FileNotFoundException e) {
			System.err.println("File \'" + INPUT_FILE + "\' not found.");
			return;

		} // End of catch
		
		array = new int[length];
		sortedArray = new int[length];
		quickSortArray = new int[length];

		while (fileReader.hasNextInt()) {
			array[lineNum] = fileReader.nextInt();
			sortedArray[lineNum] = array[lineNum];
			quickSortArray[lineNum] = array[lineNum];
			
			lineNum++;
		} // End of while loop
		
		System.out.println("Original Array:");
		for (int i = 0; i < length; i++)
			System.out.print(array[i] + ",");

		fileReader.close();
		System.out.println("");

	} // End of processInput method

/************************************************************************************************************/
	// Start of shellSort method

	/**
	 * shellSort with organize the random numbers by comparing elements that are at a certain distance apart 
	 * and reducing the gap value until gap size is 1.
	 * @param Array integer array that holds all of the random numbers to be sorted.
	 */

	private static void shellSort(int[] sortedArray) {

		int increment = sortedArray.length / 2;
		while (increment > 0) {
			for (int i = increment; i < sortedArray.length; i++) {
				int j = i;
				int temp = sortedArray[i];
				while (j >= increment && sortedArray[j - increment] > temp) {
					sortedArray[j] = sortedArray[j - increment];
					j = j - increment;
				} // End of while loop
				
				sortedArray[j] = temp;
			} // End of for loop
			
			if (increment == 2) {
				increment = 1;
			} else {
				increment *= (5.0 / 11);
			} // End of else statement
		} // End of while loop

	} // End of shellSort method

/************************************************************************************************************/
	// Start of quickSort method

	/**
	 * quickSort with organize the random numbers by finding a pivot point and comparing left and right side 
	 * integers to the pivot.
	 * @param Array integer array that holds all of the random numbers to be sorted.
	 */
	private static void quickSort(int[] quickSortArray) {
		if (quickSortArray == null || quickSortArray.length == 0) {
			return;
		}
		array = quickSortArray;
		length = quickSortArray.length;
		sort(0, quickSortArray.length - 1);

	} // End of quickSort method

/************************************************************************************************************/
	// Start of sort method

	/**
	 * sort method will iterate through the array comparing both the low and high
	 * index to the pivot and calling the swap method based on there relationship
	 * to the pivot.
	 * @param lowIndex integer and highIndex integer so they can be compated to the piviot. 
	 */
	private static void sort(int lowIndex, int highIndex) {
		int low = lowIndex;
		int high = highIndex;
		int pivot = quickSortArray[low + (high - low) / 2];

		while (low <= high) {
			while (quickSortArray[low] < pivot) {
				low++;
			} // End of While Loop
			
			while (quickSortArray[high] > pivot) {
				high--;
			} // End of while Loop
			
			if (low <= high) {
				swap(low, high);
				low++;
				high--;
			} // End of if statement
			
		} // End of While Loop
		
		if (lowIndex < high)
			sort(lowIndex, high);
		if (low < highIndex) {
			sort(low, highIndex);
		} // End of if statement

	} // End of sort method

/************************************************************************************************************/
	// Start of swap method

	/**
	 * swap method with switch the lowIndex with the highIndex of the array when
	 * called.
	 * @param lowIndex integer and highIndex integer so they can be swapped.
	 */

	private static void swap(int lowIndex, int highIndex) {
		int temp = quickSortArray[lowIndex];
		quickSortArray[lowIndex] = quickSortArray[highIndex];
		quickSortArray[highIndex] = temp;
	} // End of swap method

/************************************************************************************************************/
	// Start of generateReport method

	/**
	 * generateReport method with write the sorted integer array onto an output file
	 * with desired output.
	 */
	public static void generateReport() throws IOException {
		File creation = new File(OUTPUT_FILE1);
		try {
			output = new PrintWriter(creation);

			output.println("ShellSort array: ");
			for (int j = 0; j < sortedArray.length; j++) {
				if (j % 10 == 0 && j > 0) {
					output.println();
				} // End of if statement
				
				output.print(sortedArray[j] + " ");
			} // End of for loop
			
			output.close();

		} catch (IOException ex) {
			System.out.printf("Error: unable to export data to file $s\n", ex);
		} // End of catch

		File creation2 = new File(OUTPUT_FILE2);
		try {
			output = new PrintWriter(creation2);

			output.println("Quick Sort array: ");
			for (int j = 0; j < quickSortArray.length; j++) {
				if (j % 10 == 0 && j > 0) {
					output.println();
				} // End of if statement
				
				output.print(quickSortArray[j] + " ");
			} // End of for loop
			
			output.close();
		} catch (IOException ex) {
			System.out.printf("Error: unable to export data to file $s\n", ex);
		} // End of catch
		
	} // End of generateReport method
	
} // End of JedidiahPrall_CalvinNguyen_06