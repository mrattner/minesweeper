/**
 * MineBox.java
 * CS101 - April 21, 2011
 * Marcy Rattner
 *
 * Note: I asked Audrey a question about how to count mines around boxes.
 */

/**
 * The MineBox class contains properties and methods concerning a single
 * tile in the minefield.
 *
 * @author Marcy Rattner
 */
public class MineBox 
{
	//*****************INSTANCE PROPERTIES*****************************
	/**
	 * The number of mines surrounding the box. -1 if the box is a mine.
	 */
	private int mineCount;
	
	/**
	 * Whether the box has been revealed or not. Initially false.
	 */
	private boolean isRevealed;
	
	//*****************INSTANCE METHODS********************************
	/**
	 * Constructor: Initializes mineCount to 0 and isRevealed to false.
	 */
	public MineBox()
	{
		mineCount = 0;
		isRevealed = false;
	}
	
	/**
	 * Sets isRevealed to true.
	 */
	public void flipOver ()
	{
		isRevealed = true;
	}
	
	/**
	 * Finds out whether the box is a mine. Returns true if it is a mine.
	 */
	public boolean isMine ()
	{
		return (mineCount == -1);
	}
	
	/**
	 * Finds out whether the box has no adjacent mines. Returns true if the
	 * mine count is zero.
	 */
	public boolean isBlank ()
	{
		return (mineCount == 0);
	}
	
	/**
	 * Changes the box to a mine.
	 */
	public void changeToMine ()
	{
		mineCount = -1;
	}
		
	/**
	 * Accesses the mineCount instance property.
	 */
	public int getMineCount( )
	{
		return mineCount;
	}
	
	/**
	 * Adds one to the mine count.
	 */
	public void incrementMineCount ()
	{
		mineCount++;
	}
	
	/**
	 * Accesses the isRevealed instance property.
	 */
	public boolean getWhetherRevealed ()
	{
		return isRevealed;
	}
	
} //end MineBox