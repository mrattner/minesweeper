/**
 * MinesweeperPanel.java
 * CS101 Final Project
 * Marcy Rattner
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * MinesweeperPanel is the main GUI panel for the Minesweeper game.
 * Adapted from MemoryPanel.java from Lab 11.
 * Note: I talked to Audrey about Model-View-Controller setup, and to Henry
 * about how to set up panels inside of panels.
 */
public class MinesweeperPanel extends JPanel implements MouseListener
{
	//**********************INSTANCE PROPERTIES****************************
	/**
	 * The minefield that contains all the game logic.
	 */
	private Minefield field;
	
	/**
	 * The JPanel that contains the mine grid.
	 */
	private JPanel mineGrid;
	
	/**
	 * The JPanel that contains the win/lose message.
	 */
	private JPanel labelPanel;
	
	/**
	 * The JLabel inside of labelPanel that displays win/lose message.
	 */
	private JLabel message;
	
	/**
	 * Stores whether the game has stopped.
	 */
	private boolean gameOver;
	
	//***********************INSTANCE METHODS*****************************
	/**
	 * Constructor: Creates a Minesweeper game layout with two sub-panels.
	 **/
	public MinesweeperPanel()
	{	
		//initialize gameOver to false
		gameOver = false;
		
		//instantiate a new Minefield instance
		field = new Minefield();
		
		//set the layout of this panel to a centered FlowLayout
		setLayout(new BorderLayout());
		
		//initiate the grid's layout
		initGrid();
		
		//set up the label sub-panel
		initLabel();
		
	} //end constructor
	
	/**
	 * Sets up the minefield grid.
	 */
	public void initGrid ()
	{
		//set mineGrid to a new instance of a JPanel
		mineGrid = new JPanel();
		
		//set the layout of this panel to be a grid layout.
		//The rows and columns are specified by the Minefield.
		mineGrid.setLayout(new GridLayout(field.getRows(),field.getCols()));
		
		//Walk through every row of the Minefield
		for (int i=0; i<field.getRows(); i++)
		{
			//Walk through every column of the Minefield
			for (int j=0; j<field.getCols(); j++)
			{
				//For each element of the field, add a tile to the grid to
				//represent the box at (i,j) in the minefield.
				addTile(field.getMineField()[i][j], i, j);
			}
		}
		//add the mineGrid panel to the center of the page
		add(mineGrid, BorderLayout.CENTER);
	}
	
	/**
	 * Create and add a tile to the mineGrid.
	 */
	public void addTile (MineBox box, int row, int col)
	{
		//create a new tile representing the passed MineBox
		MinesweeperTile tile = new MinesweeperTile(box, row, col);
		
		//add this object as tile's mouse listener
		tile.addMouseListener(this);
		
		//add tile to the panel
		mineGrid.add(tile);
	}
	
	/**
	 * Make a JLabel for the label sub-panel. 
	 */
	public void initLabel ()
	{
		//set labelPanel to a new instance of a JPanel
		labelPanel = new JPanel();
		
		//Set the layout of the label to FlowLayout
		labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//add labelPanel to the MinesweeperPanel, at the top of the page
		add(labelPanel, BorderLayout.PAGE_START);
		
		//create a new JLabel that displays the label text
		
		message = new JLabel("Beware of mines...");
		//make the label opaque
		message.setOpaque(true);
		//set the label's background color
		message.setBackground(Color.LIGHT_GRAY);
		//set the font of the label text
        message.setFont(new Font("Geneva", Font.BOLD, 24));
		
		//add the label sub-panel to MinesweeperPanel
		labelPanel.add(message);
	}
	
	/**
	 * Checks whether the game is over.
	 */
	public void checkIfGameOver ()
	{
		//check whether the game was lost
		if (field.checkIfGameLost())
		{
			//Change the label text to a "you lose" message
			message.setText("BOOM! You lose!");
			//Change the background color
			message.setBackground(Color.PINK);
			
			//Have the minefield reveal all the mines
			field.revealMines();
			//refresh the display
			repaint();
			//prevent mouseClicked method from doing further actions
			gameOver = true;
		}
		//otherwise, the game was not lost
		else
		{
			//check whether the game was won
			if (field.checkIfGameWon())
			{
				//Change the label text to a "you win" message"
				message.setText("Cool! You win!");
				//Change the background color
				message.setBackground(Color.GREEN);
				
				//prevent mouseClicked method from doing further actions
				gameOver = true;
			}
		}
	}
	
	//*******************REQUIRED MOUSE LISTENER METHODS********************
	/**
	 * Invoked when the mouse button has been clicked on a component.
	 */
	public void mouseClicked(MouseEvent e)
	{
		//only perform this method if the game is not over
		if (!gameOver)
		{
			//Determine which tile generated this event. Cast the target
			//as a MinesweeperTile.
			MinesweeperTile clickedTile = (MinesweeperTile)e.getSource();
		
			//Have the minefield reveal this tile's box
			field.revealBox(clickedTile.getRow(), clickedTile.getCol());
		
			//Repaint all tiles
			repaint();
			
			//Now check whether the game is over
			checkIfGameOver();
			
		}
	}
	
	/**
	 * Invoked when the mouse enters a component.
	 */
	public void mouseEntered(MouseEvent e) {}
	
	/**
	 * Invoked when the mouse exits a component.
	 */
	public void mouseExited(MouseEvent e) {}
	
	/**
	 * Invoked when a mouse button has been pressed on a component.
	 */
	public void mousePressed(MouseEvent e) {}
	
	/**
	 * Invoked when a mouse button has been released on a component.
	 */
	public void mouseReleased(MouseEvent e) {}
	
} //end MinesweeperPanel