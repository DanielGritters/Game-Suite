import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import javax.swing.JButton;
import org.junit.jupiter.api.Test;

/**
 * Tests the non-GUI and non-trivial methods
 * 
 * @author Kevin Holkeboer
 * @version 1.0
 * @since 1.0
 */
class SimonSaysTests {

	/**
	 * Tests that one random button is added
	 * <p>
	 * The button that is added is from the array
	 * of the four colored game buttons. 
	 */
	@Test
	void testNewColorAddsOneRandomColoredButton() {
		/**
		 * Creates the red game button which is used 
		 * in the game panel.
		 */
		JButton redButton = new JButton("Red");
		/**
		 * Creates the blue game button which is used 
		 * in the game panel.
		 */
		JButton blueButton = new JButton("Blue");
		/**
		 * Creates the green game button which is used 
		 * in the game panel.
		 */
		JButton greenButton = new JButton("Green");
		/**
		 * Creates the yellow game button which is used 
		 * in the game panel.
		 */
		JButton yellowButton = new JButton("Yellow");
		/**
		 * Creates the button array which is used 
		 * in the game panel.
		 */
		JButton[] colorButtons = {redButton, blueButton,
				greenButton, yellowButton};
		
		GameController testGameController = new GameController(
				colorButtons);
		
		int red = 0;
		int blue = 0;
		int green = 0;
		int yellow = 0;
		
		assertNotNull(testGameController.getPatternButton(0));
		for (int i = 1; i <= 10000000; i++) {
			testGameController.newColor(colorButtons);
			if (testGameController.getPatternButton(
					i) == redButton) {
				red++;
			} else if (testGameController.getPatternButton(
					i) == blueButton) {
				blue++;
			} else if (testGameController.getPatternButton(
					i) == greenButton) {
				green++;
			} else if (testGameController.getPatternButton(
					i) == yellowButton) {
				yellow++;
			}
		}
		/**
		 * Checks that for every newColor is called
		 * one of the four buttons was added.
		 */
		assertTrue((red + blue + green + yellow) == 10000000);
		
		/**
		 * Checks that every buttons is added at least
		 * 10% of the time.
		 */
		assertTrue(red > 1000000);
		assertTrue(blue > 1000000);
		assertTrue(green > 1000000);
		assertTrue(yellow > 1000000);
		
		/**
		 * Checks that no button is added more than 
		 * 50% of the time.
		 */
		assertFalse(red > 5000000);
		assertFalse(blue > 5000000);
		assertFalse(green > 5000000);
		assertFalse(yellow > 5000000);
	}
	
	/**
	 * Tests that checkColors moves through the 
	 * game pattern and checks the buttons.
	 */
	@Test
	void testCheckColorReturnsTheCorrectBoolean() {
		JButton redButton = new JButton("Red");
		JButton blueButton = new JButton("Blue");
		JButton greenButton = new JButton("Green");
		JButton yellowButton = new JButton("Yellow");
		JButton[] colorButtons = {redButton, blueButton,
				greenButton, yellowButton};
		GameController testGameController = new GameController(
				colorButtons);
		
		/**
		 * Used to store the correct  random button sequence
		 * to be able to send the correct button to the method.
		 */
		JButton[] answerKey = new JButton[100];
		
		/**
		 * Initializes the answerKey array to the correct first 
		 * answer which is placed by the constructor.
		 */
		if (testGameController.getPatternButton(
				0).equals(redButton)) {
			answerKey[0] = redButton;
		} else if (testGameController.getPatternButton(
				0).equals(blueButton)) {
			answerKey[0] = blueButton;
		} else if (testGameController.getPatternButton(
				0).equals(greenButton)) {
			answerKey[0] = greenButton;
		} else if (testGameController.getPatternButton(
				0).equals(yellowButton)) {
			answerKey[0] = yellowButton;
		}
		
		/**
		 * Fills the rest of the answerKey array with the
		 * correct answers from the random pattern that is 
		 * being generated.
		 */
		for (int i = 1; i < 100; i++) {
			testGameController.newColor(colorButtons);
			
			if (testGameController.getPatternButton(
					i).equals(redButton)) {
				answerKey[i] = redButton;
			} else if (testGameController.getPatternButton(
					i).equals(blueButton)) {
				answerKey[i] = blueButton;
			} else if (testGameController.getPatternButton(
					i).equals(greenButton)) {
				answerKey[i] = greenButton;
			} else if (testGameController.getPatternButton(
					i).equals(yellowButton)) {
				answerKey[i] = yellowButton;
			}
		}
		
		/**
		 * Tests that checkColor returns true when the
		 * correct button is passed to it.
		 */
		for (int i = 0; i < 100; i++) {
			assertTrue(testGameController.checkColor(answerKey[i]));
		}
		/**
		 * resets the player location to 0.
		 */
		testGameController.resetPlayerLoc();
		
		/**
		 * Tests that when the correct button isn't red
		 * checkColor returns false when the red button
		 * is passed to it. 
		 */
		for (int i = 0; i < 100; i++) {
			if (testGameController.getPatternButton(
					testGameController.getPlayLoc(
							)) != redButton) {
				assertFalse(testGameController.checkColor(
						redButton));
			} else {
				assertTrue(testGameController.checkColor(
						redButton));
			}
		}
		
		/**
		 * Tests that when the correct button isn't blue
		 * checkColor returns false when the blue button
		 * is passed to it. 
		 */
		for (int i = 0; i < 100; i++) {
			if (testGameController.getPatternButton(
					testGameController.getPlayLoc(
							)) != blueButton) {
				assertFalse(testGameController.checkColor(
						blueButton));
			} else {
				assertTrue(testGameController.checkColor(
						blueButton));
			}
		}
		
		/**
		 * Tests that when the correct button isn't green
		 * checkColor returns false when the green button
		 * is passed to it. 
		 */
		for (int i = 0; i < 100; i++) {
			if (testGameController.getPatternButton(
					testGameController.getPlayLoc(
							)) != greenButton) {
				assertFalse(testGameController.checkColor(
						greenButton));
			} else {
				assertTrue(testGameController.checkColor(
						greenButton));
			}
		}
		
		/**
		 * Tests that when the correct button isn't yellow
		 * checkColor returns false when the yellow button
		 * is passed to it. 
		 */
		for (int i = 0; i < 100; i++) {
			if (testGameController.getPatternButton(
					testGameController.getPlayLoc(
							)) != yellowButton) {
				assertFalse(testGameController.checkColor(
						yellowButton));
			} else {
				assertTrue(testGameController.checkColor(
						yellowButton));
			}
		}
		
	}
}
