/**
* MinesweeperApplication.java
* CS101 Final Project
* Marcy Rattner
**/

/**
 * Copied from MemoryApplication.java, Lab 11.
 */

import javax.swing.JFrame;

/**
* MinesweeperApplication wraps a MinesweeperPanel for the Minesweeper game.
*/
public class MinesweeperApplication
{
	public static void main(String[] args)
	{
		JFrame memoryFrame = new JFrame("Minesweeper!");
		memoryFrame.getContentPane().add(new MinesweeperPanel());
		memoryFrame.setSize(500,500);
		memoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		memoryFrame.setVisible(true);
	}
} //end MinesweeperApplication