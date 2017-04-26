/**
 * MinesweeperTile.java
 * CS 101 Final Project
 * Marcy Rattner
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * MinesweeperTile represents a Tile in the Minesweeper game.
 * Adapted from MemoryCard.java from Lab 11.
 * Note: I talked to Audrey about Model-View-Controller setup.
 */
public class MinesweeperTile extends JComponent
{
	//**********************INSTANCE PROPERTIES**************************
	/**
	 * The MineBox that the MinesweeperTile represents
	 */
	private MineBox box;
	
	/**
	 * A number 0-8 if the box represented by the tile is not a mine;
	 * -1 if the box represented by the tile is a mine.
	 */
	private int tileNumber;
	
	/**
	 * Either the mine count, a blank, or an X.
	 */
	private String tileText;
	
	/**
	 * The row number of the box represented by this tile.
	 */
	private int ROW;
	
	/**
	 * The column number of the box represented by this tile.
	 */
	private int COL;
	
	//**********************INSTANCE METHODS*****************************
	
	/**
	 * @return The row number of this tile
	 */
	public int getRow()
	{
		return ROW;
	}
	
	/**
	 * @return The column number of this tile
	 */
	public int getCol()
	{
		return COL;
	}
	
	/**
	 * Constructor: makes a new tile instance representing the passed MineBox,
	 * with the passed row and column number.
	 */
	public MinesweeperTile (MineBox passedBox, int passedRow, int passedCol)
	{
		//Set the property box to the passed MineBox
		box = passedBox;
		
		//Have the tile remember its box's row number
		ROW = passedRow;
		
		//Have the tile remember its box's column number
		COL = passedCol;
		
		//Assign the passed mineCount value as this tile's tileNumber
		tileNumber = box.getMineCount();
		
		//Update the tile text
		updateTileText();
	}
	
	/**
	 * Changes the tileText to reflect whether box is a mine or what its mine
	 * count is. Displays a blank space if the mine count is zero.
	 */
	public void updateTileText()
	{
		//if the box is a mine
		if (box.isMine())
		{
			tileText = "X";
		}//otherwise, if the box is not a mine
		else
		{
			//if the box has no mines around it, display a blank
			if (box.isBlank())
			{
				tileText = " ";
			}//otherwise, since it is not blank, display its mine count
			else
			{
				//turn the tile number from an Integer to a String
				tileText = Integer.toString(tileNumber);
			}
		}
	}
	
	/**
	 * Special method as specified in JComponent (the parent class) for drawing
	 * on this tile. We override the method here to customize the display.
	 */
	public void paint (Graphics g)
	{
		//if the box represented by this tile is revealed
		if (box.getWhetherRevealed())
		{
			//call utility method to paint background of the tile light gray
			//pass along Graphics object to paint on
			paintBackground(g, Color.LIGHT_GRAY);
			
			//call utility method to paint the tile number on the tile
			paintText(g, chooseTextColor());
		}
		//otherwise, the tile is face down
		else
		{
			//simply paint the back of a tile
			paintBackground(g, Color.DARK_GRAY);
		}
	}
	
	/**
	 * Helper method for paint that chooses the text color based on the box's
	 * mine count.
	 */
	public Color chooseTextColor ()
	{
		//if the box is a mine, its X should be red
		if (box.isMine())
			return Color.RED;
		//other color options for the mine count, if the box isn't a mine
		else if (tileNumber==1)
			return Color.BLUE;
		else if (tileNumber==2)
			return Color.CYAN;
		else if (tileNumber==3)
			return Color.GRAY;
		else if (tileNumber==4)
			return Color.ORANGE;
		else if (tileNumber==5)
			return Color.MAGENTA;
		else if (tileNumber==6)
			return Color.GREEN;
		else if (tileNumber==7)
			return Color.YELLOW;
		//catchall for other minecounts
		else
			return Color.BLACK;
	}
	
	/**
	 * Utility method to paint the background of the tile. Invoked by paint.
	 */
	public void paintBackground(Graphics g, Color c)
	{
		//set the color of the "brush"
		g.setColor(c);
		
		//fill a rectangle using the Tile's color. Arguments are x, y, width,
		//height. Note: methods getWidth and getHeight are inherited from parent
		g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
		
		//add a rounded rectangular border in black
		g.setColor(Color.BLACK);
		//arguments are x, y, width, height, arcWidth, arcHeight
		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
	}
	
	/**
	 * Utility method to paint the tile number or mine marker onto the tile.
	 */
	public void paintText (Graphics g, Color c)
	{
		//set the color of the "brush"
		g.setColor(c);
		
		//set font
		g.setFont(new Font("Geneva", Font.BOLD, 24));
		
		//draw text
		g.drawString(tileText, 10, getHeight()/2+15);
	}
} //end MinesweeperTile