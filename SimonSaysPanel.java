import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Creates the game board and responds to player actions.
 * <p>
 * Creates the four game buttons and a play button to show the pattern.
 * Formats the layout.
 * <p>
 * Displays the pattern to the layer. Creates a GameController object.
 * 
 * @author Kevin Holkeboer
 * @version 1.0
 * @since 1.0
 */
public class SimonSaysPanel extends JPanel implements ActionListener {
	
	/**
	 * The number of completed rounds or score.
	 */
	private int scoreValue = 0;
	
	/**
	 * The label that displays the score.
	 */
	private JLabel score = new JLabel("Score: " + scoreValue);
	
	/**
	 * The red button of the game board.
	 */
	private JButton redButton = new JButton("Red");
	
	/**
	 * The blue button of the game board.
	 */
	private JButton blueButton = new JButton("Blue");
	
	/**
	 * The green button of the game board.
	 */
	private JButton greenButton = new JButton("Green");
	
	/**
	 * The yellow button of the game board.
	 */
	private JButton yellowButton = new JButton("Yellow");
	
	/**
	 * The play button of the game board.
	 */
	private JButton playButton = new JButton("Play");
	
	/**
	 * An array containing all the game buttons that are added to the
	 * pattern.
	 */
	private JButton[] colorButtons = {redButton, blueButton,
			greenButton, yellowButton};
	
	/**
	 * The active game. starting with the red button.
	 */
	private GameController game = new GameController(colorButtons);
	
	/**
	 * Constructor that creates the layout for the buttons.
	 * <p>
	 * Adds the colors and actionListeners to the buttons.
	 * 
	 */
	public SimonSaysPanel() {
		/**
		 * Sets the layout to GridBag.
		 */
		setLayout(new GridBagLayout());
		/**
		 * Constraint to place the red button in the top center.
		 */
		GridBagConstraints red = new GridBagConstraints();
		red.gridx = 100;
		red.gridy = 0;
		red.weighty = 1;
		/**
		 * constraint to place the blue button in the middle right.
		 */
		GridBagConstraints blue = new GridBagConstraints();
		blue.gridx = 200;
		blue.gridy = 100;
		blue.weightx = 1;
		/**
		 * Constraint to place the green button in the middle left.
		 */
		GridBagConstraints green = new GridBagConstraints();
		green.gridx = 100;
		green.gridy = 200;
		green.weighty = 1;
		/**
		 * constraint to place the yellow button in the bottom center.
		 */
		GridBagConstraints yellow = new GridBagConstraints();
		yellow.gridx = 0;
		yellow.gridy = 100;
		yellow.weightx = 1;
		/**
		 * constraint to place the play button in the middle center.
		 */
		GridBagConstraints playConst = new GridBagConstraints();
		playConst.gridx = 100;
		playConst.gridy = 100;
		playConst.weightx = 1;
		/**
		 * Constraint to place the score label above the play button.
		 */
		GridBagConstraints scoreConst = new GridBagConstraints();
		scoreConst.gridx = 100;
		scoreConst.gridy = 50;
		scoreConst.weightx = 1;
		scoreConst.weighty = .01;
		/**
		 * Adds ActionListener to all five buttons.
		 */
		redButton.addActionListener(this);
		blueButton.addActionListener(this);
		greenButton.addActionListener(this);
		yellowButton.addActionListener(this);
		playButton.addActionListener(this);
		/**
		 * Sets the background colors of the four game buttons.
		 */
		redButton.setBackground(Color.RED);
		blueButton.setBackground(Color.BLUE);
		greenButton.setBackground(Color.GREEN);
		yellowButton.setBackground(Color.YELLOW);
	
		/**
		 * Adds the buttons and the score label and
		 * their constraints to the board.
		 */
		add(redButton, red);
		add(blueButton, blue);
		add(yellowButton, green);
		add(greenButton, yellow);
		add(playButton, playConst);
		add(score, scoreConst);
		
	}
	
	/**
	 * Responds to the buttons being clicked.
	 * <p>
	 * The play button calls the flashButtons method to display the 
	 * current pattern.
	 * <p>
	 * The four colored game buttons call the GameController to check
	 * against the pattern and start a new round if the pattern is complete.
	 * <p>
	 * If the game button doesn't match the pattern, the GameController 
	 * constructor is called to reset the game and a pop-up tells the player
	 * they lost and their score.
	 * 
	 * @param e
	 * 		One of the buttons being clicked. 
	 */
	public void actionPerformed(final ActionEvent e) {
		
		if (e.getSource() == playButton) {
		
			flashButtons(); 
		}
		if (e.getSource() != playButton) {
			
			if (e.getSource() instanceof JButton) {
				if (game.checkColor((JButton) e.getSource())) {
					//game.setPlayerLoc(
						//	game.getPlayLoc() + 1);
					if (game.getPlayLoc() 
						>= game.getPatternSize()) {
						
						game.newColor(colorButtons);
						flashButtons();
						setScore();
						game.resetPlayerLoc();
					}
				} else {
					/**
					 * Displays a pop up massage 
					 * when the game is lost
					 */
					JOptionPane.showInternalMessageDialog(
						getParent(),
						"Game Reset \n Score: "
						+ scoreValue);
					
					game = new GameController(colorButtons);
					setScore();
				}
				
			}
			
		}
	}
	/**
	 * Alternate the background colors of the game buttons
	 *  to display the current pattern.
	 */
	private void flashButtons() {
		/**
		 * Indicates the current button to change color.
		 */
		int gameLoc = 0;
		/**
		 * Indicates if the button's color has been reset to it's 
		 * original color.
		 */
		boolean resetColors = false;
		/**
		 * Used to increase the delay time of each color change
		 *  to make them visible.
		 */
		int delayMulti = 0;
		/**
		 * Loops through the current pattern changing and then
		 * reseting the buttons' colors.
		 */
		int delay = 0;
		/**
		 * Disables all the buttons while they are being flashed
		 */
		redButton.setEnabled(false);
		blueButton.setEnabled(false);
		yellowButton.setEnabled(false);
		greenButton.setEnabled(false);
		playButton.setEnabled(false);
		
		while (gameLoc < game.getPatternSize()) {
			/**
			 * Increases the delay by one second for each color
			 * change and reset.
			 */
			delay = (delayMulti * 1000);
			/**
			 * Indicates which button's color will be changed.
			 */
			final Integer currentButton = Integer.valueOf(gameLoc);
			
			/**
			 * Indicates on true that the button's color has been
			 *  reset to the original.
			 */
			final Boolean innerResetColors = 
					Boolean.valueOf(resetColors);
					
			/**
			 * Changes the color of the current button or calls
			 * resetColors to reset the original color.
			 */
			ActionListener setColor = new ActionListener() {

				public void actionPerformed(
						final ActionEvent e) {
					if (innerResetColors) {
						resetColors();
					} else {
						game.getPatternButton(
								currentButton).
						setBackground(Color.CYAN);
					}
					
				}
				
			};
			
		
			/**
			 * Delays the color changes by the time of delay.
			 */
			Timer timer = new Timer(delay,setColor); 
			timer.setRepeats(false);
			timer.start();
			/**
			 * Increases the delay to offset color changes from 
			 * each other.
			 */
			delayMulti++;
			/**
			 * Sets the next button to be flashed.
			 */
			if (resetColors) {
				gameLoc++;
				resetColors = false;
			
			/**
			 * Allow the color of the current button to be reset.
			 */
			} else {
				resetColors = true;
			}
			
		} //End While
		
		/**
		 * Re-enables all the buttons is delayed
		 * by the timer enableDelay
		 */
		ActionListener enableButtons = new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				redButton.setEnabled(true);
				blueButton.setEnabled(true);
				yellowButton.setEnabled(true);
				greenButton.setEnabled(true);
				playButton.setEnabled(true);
				
			}
			
		};
		
		/**
		 * Delays the buttons getting enabled
		 * until after their colors are reset
		 */
		Timer enableDelay = new Timer(delay, enableButtons);
		enableDelay.setRepeats(false);
		enableDelay.start();
		
	}
	
	
	
	/**
	 * Resets the four game buttons to their original color.
	 */
	private void resetColors() {
		redButton.setBackground(Color.RED);
		blueButton.setBackground(Color.BLUE);
		greenButton.setBackground(Color.GREEN);
		yellowButton.setBackground(Color.YELLOW);
	}
	/**
	 * Set the value of scoreValue which is displayed. 
	 * by the JLabel score
	 */
	private void setScore() {
		scoreValue = game.getPatternSize() - 1;
		score.setText("Score: " + scoreValue);
	}
	
}
