/**
* MinesweeperApplet.java
* CS 101 Final Project
* Marcy Rattner
*/

/**
 * This class is copied from the MemoryApplet.java file from Lab 11.
 */

//awt
import javax.swing.JApplet;

/**
* MinesweeperApplet is a wrapper for the MinesweeperPanel class.
*/
public class MinesweeperApplet extends JApplet
{
	/**
	 * Invoked when applet is initialized.
	 */
	public void init()
	{
		//add MinesweeperPanel
		getContentPane().add(new MinesweeperPanel());
	}
	
	public void destroy()
	{
	}
	
	public void start()
	{
	}
	
	public void stop()
	{
	}
	
}//end MinesweeperApplet