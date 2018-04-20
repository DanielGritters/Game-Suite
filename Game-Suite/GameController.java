import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;

/**
 * Controls the non-GUI logic.
 * <p>
 * Creates and stores the play order and checks and records the player's action.
 * 
 * @author Kevin Holkeboer
 * @version 1.0
 * @since 1.0
 *
 */
public class GameController {
	/**
	 * The pattern the player must match.
	 */
	private ArrayList<JButton> pattern = new ArrayList<JButton>();
	/**
	 * The number of correct buttons the player has selected each round.
	 */
	private int playerLoc;
	/**
	 * A random number used to add a button to the pattern at the end of
	 * each round.
	 */
	private Random num = new Random();
	/**
	 * Flag to say to if the player selected the correct button.
	 */
	private boolean correctColor;
	/**
	 * Constructor initializes the game pattern and player location.
	 * 
	 * @param starter
	 * 			An array containing all the game buttons.
	 */
	public GameController(final JButton[] starter) {
		newColor(starter);
		playerLoc = 0;
		
		
	}
	
	/**
	 * Compares the button the player clicked to the game pattern.
	 * 
	 * @param clickedButton
	 * 			The button the player click.
	 * @return true if the button matches the pattern at the current
	 * location otherwise return false.
	 */
	public boolean checkColor(final JButton clickedButton) {
		
		if (clickedButton == pattern.get(playerLoc)) {
			correctColor = true;
			playerLoc++;
		} else {
			correctColor = false;
		}
		return correctColor;
	}
	
	/**
	 * Adds a new button to the end of the pattern
	 * by generating a random number between 0 and 3
	 * and selecting that button from the array.
	 * 
	 * @param colors
	 * An array containing the four possible buttons
	 * of the pattern.
	 */
	public void newColor(final JButton[] colors) {
		pattern.add(colors[num.nextInt(4)]);
		
	}
	/**
	 * Returns the value of playerLoc.
	 * @return The current location of the player.
	 */
	public int getPlayLoc() {
		return playerLoc;
	}
	/**
	 * Resets the value of playerLoc to 0
	 * when the player reaches the end of the pattern.
	 * 
	 */
	public void resetPlayerLoc() {
		playerLoc = 0;
	}
	/**
	 * Returns the size of the pattern arrayList.
	 * @return the length of the current pattern.
	 */
	public int getPatternSize() {
		return pattern.size();
	}
	/**
	 * 
	 * @param currentButton
	 * The location of the current button in the pattern.
	 * @return
	 * The button at that location.
	 */
	public JButton getPatternButton(final int currentButton) {
		return pattern.get(currentButton);
	}
}
