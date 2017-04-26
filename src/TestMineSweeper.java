/**
 * TestMineSweeper.java
 * CS101 - April 5, 2011
 * Marcy Rattner
 */

/**
 * This file is for testing the logic of the Minesweeper game from a command
 * line.
 *
 * @author Marcy Rattner
 */
public class TestMineSweeper 
{
	//*****************INSTANCE PROPERTIES*********************************
	/**
	 * Make space for a new minefield to test
	 */
	private Minefield testField;
	
	//*****************INSTANCE METHODS************************************
	/**
	 * Constructor
	 */
	public TestMineSweeper()
	{
		//Create a new Minefield instance
		testField = new Minefield();
		print2DArray(testField.getMineField());
	}
	
	/**
	 * Prints a two-dimensional array. Edited from Lab 10, part 1
	 */
	public void print2DArray (MineBox[][] array)
	{
		//Walk across the multi-dimensional array and print to the screen
		//the contents of the array
		for (int i=0; i<array.length; i++)
		{
			//create an empty string for this row
			String arrayRow = "";
			for (int j=0; j<array[i].length; j++)
			{
				//Temporary variables
				String display = "";
				
				if (array[i][j].isMine())
				{
					display = "*";
				} else {
					display = Integer.toString(array[i][j].getMineCount());
				}
				
				//Concatenate the display value of each cell.
				//It will either be a number 0-8 or a bomb marker
				arrayRow = arrayRow + display + " ";
			}
			//Print the contents of this row
			System.out.println(arrayRow);
		}
	} //end print2DArray
	
	/**
	 * Special 'main' method is run only when the program for this particular
	 * class is executed from a command line (like Terminal).
	 */
	public static void main( String[] args )
	{
		new TestMineSweeper();
		
	} // end main
	
} //end TesMineSweeper