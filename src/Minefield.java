/**
 * Minefield.java
 * CS101 - April 21, 2011
 * Marcy Rattner
 *
 * Note: I asked Audrey a question about how to count mines around boxes.
 */

/**
 * The Minefield class contains properties and methods concerning the entire
 * grid of boxes, and manages the mechanisms of the game.
 *
 * @author Marcy Rattner
 */
public class Minefield 
{
	//*****************INSTANCE PROPERTIES*****************************
	/**
	 * The minefield is a 2-dimensional array of MineBoxes.
	 */
	private MineBox[][] mineField;
	
	/**
	 * The number of rows in the minefield.
	 */
	private int FIELD_ROWS;
	
	/**
	 * The number of columns in the minefield.
	 */
	private int FIELD_COLS;
	
	/**
	 * The number of mines to be placed in the field.
	 */
	private int NUMBER_MINES;
	
	/**
	 * Stores whether the game has been lost or not. Initially false.
	 */
	private boolean gameLost;
	
	//*****************Methods that access instance properties*********
	/**
	* @return The number of rows in the minefield
	*/
	public int getRows()
	{
		return FIELD_ROWS;
	}
	
	/**
	 * @return The number of columns in the minefield
	 */
	public int getCols()
	{
		return FIELD_COLS;
	}
	
	/**
	 * @return The number of mines in the minefield
	 */
	public int getMines()
	{
		return NUMBER_MINES;
	}
	
	/**
	 * @return The array of MineBoxes
	 */
	public MineBox[][] getMineField()
	{
		return mineField;
	}
	
	/**
	 * @return True if the game has been lost, false if not
	 */
	public boolean checkIfGameLost()
	{
		return gameLost;
	}
	
	//*****************INSTANCE METHODS********************************
	/**
	 * Constructor: Sets the number of rows and columns in mineField.
	 * Sets mineField to a new array of MineBoxes. Fills each element of the
	 * array with a new MineBox instance. Calls placeMines and setMineCounts.
	 */
	public Minefield()
	{
		//Declare values for constants
		FIELD_ROWS = 9;
		FIELD_COLS = 9;
		//Cast the floored calculation as an int to calculate # of mines
		NUMBER_MINES = (int)Math.floor(FIELD_ROWS * FIELD_COLS / 8);
		
		//At first, the game has not been lost yet
		gameLost = false;
		
		//Create a new array of MineBoxes with specified dimensions
		mineField = new MineBox[FIELD_ROWS][FIELD_COLS];
		
		//Fill the mineField with MineBoxes
		for (int i=0; i<mineField.length; i++)
		{
			for (int j=0; j<mineField[i].length; j++)
			{
				//Instantiate a new MineBox instance in each cell.
				mineField[i][j] = new MineBox();
			}
		}
		
		//place the mines in the minefield
		placeMines(NUMBER_MINES);
		
		//now that all mines are placed, set the mine counts
		setMineCounts();
	}
	
	/**
	 * Places the passed number of mines into the minefield by calling the
	 * placeOneMine method. Repeats until placeOneMine is successful
	 * numberOfMines times.
	 */
	public void placeMines(int numberOfMines)
	{
		//temporary variable to store how many times a mine was placed
		int successCount = 0;
		do
		{
			//attempt to place a mine
			if (placeOneMine())
			{
				//if it was successful, add 1 to successCount
				successCount++;
			}
		//repeat until placeOneMine has placed all the mines
		} while (successCount<numberOfMines);
	}
	
	/**
	 * Helper method for placeMines; suggested by Audrey. Picks a random row and
	 * column number within the confines of mineField and checks whether it
	 * already has a mine. If it doesn't, changes that box to a mine.
	 * Returns true if it successfully placed a mine.
	 */
	public boolean placeOneMine()
	{
		//pick a random row within the minefield's row bounds
		int row = (int)Math.floor(Math.random()*FIELD_ROWS);
		//pick a random column within the minefield's column bounds
		int column = (int)Math.floor(Math.random()*FIELD_COLS);
		
		//If the chosen row and column does not already have a mine
		if (!mineField[row][column].isMine())
		{
			//place a mine there
			mineField[row][column].changeToMine();
			//the placement was successful
			return true;
		} else
		{
			//the placement was not successful
			return false;
		}
	}
	
	/**
	 * Invoked by the constructor. For each element of the minefield, check
	 * whether that element contains a mine. If it does not have a mine, add 1
	 * to its mine count for every adjacent box that does have a mine.
	 */
	public void setMineCounts ()
	{
		//Walk over each element of the mineField
		for (int i=0; i<mineField.length; i++)
		{
			for (int j=0; j<mineField[i].length; j++)
			{
				//If the box at [i][j] does not have a mine
				if (!mineField[i][j].isMine())
				{
					//then add to its mine count.
					addToMineCount(i,j);
				}
			}//end inner for loop
		}//end outer for loop
	}
	
	/**
	 * For the passed row and column number, check whether it is inside
	 * the bounds of mineField's rows and columns.
	 */
	public boolean isInsideFieldBounds(int row, int column)
	{
		//if the passed row exists in the mine field
		if (row>=0 && row<mineField.length)
		{
			//if the passed column exists in the mine field
			if (column>=0 && column<mineField[row].length)
			{
				return true;
			}
			//otherwise, if the passed column doesn't exist
			else
			{
				return false;
			}
		}
		//otherwise, if the passed row doesn't exist
		else
		{
			return false;
		}
	}
	
	/**
	 * Helper method for setMineCounts. Checks every box surrounding the
	 * passed row and column. If an adjacent box contains a mine, add 1 to
	 * the count of the box at (row, column).
	 */
	public void addToMineCount (int row, int column)
	{
		//Walk over the previous, current, and next rows
		for (int i=row-1; i<=row+1; i++)
		{
			//Walk over the previous, current, and next columns
			for (int j=column-1; j<=column+1 ; j++)
			{
				//if the box (i,j) exists and contains a mine 
				if (isInsideFieldBounds(i,j) && mineField[i][j].isMine())
				{
					//then add 1 to the mineCount of (row, column)'s box
					mineField[row][column].incrementMineCount();
				}
			}
		}
	}
	
	/**
	 * Invokes the flipOver instance method of the MineBox at the specified
	 * row and column. If the box at that location is a mine, the player loses.
	 * Otherwise, this method invokes checkIfGameWon.
	 */
	public void revealBox (int i, int j)
	{
		//Flip over the box at the passed row and column
		mineField[i][j].flipOver();
		
		//if the revealed box is a mine
		if (mineField[i][j].isMine())
		{
			//then the player loses
			gameLost = true;
		}//otherwise, the revealed box is not a mine
		else
		{
			//If the revealed box has a mine count of zero
		    if (mineField[i][j].isBlank())
		    {
				//then reveal all the adjacent boxes
				revealAdjacentBoxes(i,j);
		    }

			//check whether the game is won
			checkIfGameWon();
		}
	}

	/**
	 * Helper method for revealBox. Reveals every surrounding box that exists
	 * and isn't revealed yet.
	 */
	public void revealAdjacentBoxes (int row, int column)
	{
		//Walk over the previous, current, and next rows
	    for (int i=row-1; i<=row+1; i++)
	    {
			//Walk over the previous, current, and next rows
			for (int j=column-1; j<=column+1; j++)
			{
				//if the box (i,j) exists and isn't revealed yet
				if (isInsideFieldBounds(i,j) 
					&& !mineField[i][j].getWhetherRevealed())
				{
					//then call the revealBox method again
					revealBox(i,j);
				}
			}
		}
	}
	
	/**
	 * Walks through every element of mineField and finds the boxes that are
	 * not mines. For each box that isn't a mine, check if that box has NOT
	 * been revealed yet. If all the non-mine boxes have been revealed, returns
	 * true (the game has been won.)
	 */
	public boolean checkIfGameWon()
	{
		//walk over the entire minefield
		for (int i=0; i<mineField.length; i++)
		{
			for (int j=0; j<mineField[i].length; j++)
			{
				//if the tile is not a mine and it has NOT been flipped over
				if (!mineField[i][j].isMine()
					&& !mineField[i][j].getWhetherRevealed())
				{
					//then the game is not won and we can exit the method
					return false;
				}
			}
		}
		//If we get past the for loop, that means the game has been won.
		return true;
	}
	
	/**
	 * Reveals all the boxes in the field that are mines.
	 */
	public void revealMines()
	{
		//walk over every row of the minefield
		for (int i=0; i<mineField.length; i++)
		{
			//walk over every column of the minefield
			for (int j=0; j<mineField[i].length; j++)
			{
				//if the box at (i,j) has a mine
				if (mineField[i][j].isMine())
				{
					//then reveal it
					mineField[i][j].flipOver();
				}
			}
		} 
	}
	
} //end Minefield