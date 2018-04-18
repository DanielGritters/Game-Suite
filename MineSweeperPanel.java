package gameCode;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
/**
 * 
 * @author Dan Gritters
 * For CIS350 Final Project - Game Suite
 *creates a panel for a GUI that is created in the main method
 * @version 1.0
 * @since 1.0
 */
public class MineSweeperPanel extends JPanel {
	
	private JButton[][] board;
	private Cell tempCell;
	private JButton restartButton;
	private JButton flagButton;
	private MineSweeperGame game;
	private int size;
	private int mines;
	
	//this will be used to determine the difficulty
	private String difficulty;
	
	//flagger used to determine if clicking the cell or adding a flag
	private boolean flagger;
	
	/**
	 * Constructor for the panel includes all of the buttons for game, asking how many buttons you want and how many mines,
	 * also instantiates the quit button, the wins and losses counter, and the flag button.
	 */
	public MineSweeperPanel() {
		setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints();
        

		difficulty = JOptionPane.showInputDialog(null, "Difficulty: easy, medium, or hard");
		if (difficulty != null) {
			if (difficulty.equalsIgnoreCase("easy")) {
				size = 10;
				mines = 10;
			} else if (difficulty.equalsIgnoreCase("medium")) {
				size = 15;
				mines = 40;
			} else if (difficulty.equalsIgnoreCase("hard")) {
				size = 20;
				mines = 60;
			} else {
				size = 10;
				mines = 10;
			}
		} else {
			size = 10;
			mines = 10;
		}
		
		//add the restart button as an action listener
		restartButton = new JButton("Restart");
		restartButton.addActionListener(new ButtonListener());
		loc.gridx = 0;
		loc.gridy = 0;
		loc.insets = new Insets(8, 8, 8, 8);
		this.add(this.restartButton, loc);
		
		//add the flag button as an action listener
		flagButton = new JButton("Flag");
		loc.gridx = 1;
		loc.gridy = 0;
		flagButton.addActionListener(new ButtonListener());
		this.add(this.flagButton, loc);
        
		//create a panel for the minesweeper buttons
		setBackground(Color.gray);
		GridLayout experimentLayout = new GridLayout(size, size);
		JPanel somePanel = new JPanel();	
		somePanel.setLayout(experimentLayout);	
		board = new JButton[size][size];
		flagger = false;
		
		//initialize the 2D array of buttons
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				board[row][col] = new JButton();
				board[row][col].setMargin(new Insets(1, 1, 1, 1));
				board[row][col].setPreferredSize(new Dimension(25, 25));
				board[row][col].setFont(new Font("Arial", Font.PLAIN, 10));
				board[row][col].addActionListener(new ButtonListener());
				somePanel.add(board[row][col]);
			}
		}
		loc.gridx = 0;
		loc.gridy = 1;
		loc.gridwidth = 2;
		
		//append the button panel to the minesweeper panel
		this.add(somePanel, loc);
		game = new MineSweeperGame(size, mines);
	}

	/**
	 * any cell that is exposed has its background switched so it discourages clicking buttons again
	 * then reveals the number of bombs around it,
	 * it also shows the flags and the bombs when they are used.
	 */
	private void displayBoard() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				
				tempCell = game.getCell(row, col);
				int mines = tempCell.getMineCount();
				
				//display the amount of adjacent mines if its clicked and isnt a mine
				if (tempCell.isExposed() && !tempCell.getIsMine()) {
					
					//displays the number in the cell
					if (mines != 0) {
						board[row][col].setText("" + mines);
					}
					//changes the color of the number
					if (mines == 1) {
						board[row][col].setForeground(Color.blue);
					} else if (mines == 2) {
						board[row][col].setForeground(Color.GREEN.darker());
					} else if (mines == 3) {
						board[row][col].setForeground(Color.RED);
					} else if (mines == 4) {
						board[row][col].setForeground(Color.BLUE.darker().darker());
					} else if (mines == 5) {
						board[row][col].setForeground(Color.MAGENTA);
					} else if (mines == 6) {
						board[row][col].setForeground(Color.CYAN);
					} else if (mines == 7) {
						board[row][col].setForeground(Color.BLACK);
					} else if (mines == 8) {
						board[row][col].setForeground(Color.GRAY);
					}
					board[row][col].setBackground(Color.lightGray);
				} else if (!tempCell.isExposed() && tempCell.getIsFlagged()) {
					//if flagged display icon
					board[row][col].setIcon(new ImageIcon("Flag2.png"));
				} else {
					board[row][col].setEnabled(true);
					board[row][col].setText("");
				}
				if (!tempCell.getIsMine() && !tempCell.getIsFlagged() || !tempCell.getIsFlagged() || tempCell.getIsMine() && !tempCell.isExposed() && !tempCell.getIsFlagged()) {
					board[row][col].setIcon(null);
				}
				if (!tempCell.isExposed()) {
					board[row][col].setBackground(null);
				}
				if (tempCell.getIsMine() && tempCell.isExposed()) {
					board[row][col].setIcon(new ImageIcon("mine.png"));
				}
				
			}
		}
	}
	/**
	 * action listener class that resets the game if quit is pressed, lets you flag when flag is pressed, and selects the 
	 * specific squares when they are pressed and recursively presses the adjacent squares to any zeros.
	 */
	private class ButtonListener implements ActionListener {
		/**
		 * takes in the actions performed.
		 * @param event this is the event that took place
		 */
		public void actionPerformed(final ActionEvent event) {
			if (event.getSource() == restartButton) {
				game.reset();
				displayBoard();
			}
			if (event.getSource() == flagButton) {
				flagger = !flagger;
				displayBoard();
			} else if (checkGameStatus()) {
				for (int row = 0; row < size; row++) {
					 for (int col = 0; col < size; col++) {
						 //check which button was the event source
						 if (board[row][col] == event.getSource()) {
							//if flagger is set, then change if the cell is flagged
							 if (flagger) {
								game.getCell(row, col).changeIsFlagged();
								displayBoard();
							} else {
								game.select(row, col);
								
								expandArea(row, col);
								displayBoard();
								//if the game is over display where all the bombs where
								if (game.getGameStatus() == GameStatus.Lost) {
									selectAllBombs();
									displayBoard();
									JOptionPane.showMessageDialog(null, "You Lose.");
									game.reset();
									displayBoard();
								 } else if (game.getGameStatus() == GameStatus.Won) {
									JOptionPane.showMessageDialog(null, "You Win!");
									game.reset();
									displayBoard();
								 }
							}
						 }
					 }
				}
			}
			displayBoard();
		}
	}
	/**
	 * When a game is lost, this method selects all the bombs 
	 * to reveal where they were located.
	 */
	private void selectAllBombs() {
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (game.getCell(row, col).getIsMine()) {
					game.getCell(row, col).setIsFlagged(false);
					game.select(row, col);
					
				}
			}
		}
	}
	/**
	 * This method selects all the squares around the zeros recursively.
	 * @param row the expanding starts at
	 * @param col the expanding starts at
	 */
	private void expandArea(final int row, final int col) {
		if (game.getCell(row, col).getMineCount() == 0 && !game.getCell(row, col).getIsFlagged()) {
			game.select(row, col);
			displayBoard();
			for (int rowCount = -1; rowCount <= 1; rowCount++) {
					for (int colCount = -1; colCount <= 1; colCount++) {
							if (row + rowCount >= 0 && row + rowCount < size && col + colCount >= 0 && col + colCount < size) {
								if (game.getCell(row + rowCount, col + colCount).getMineCount() == 0 && !game.getCell(row + rowCount, col + colCount).isExposed() && !game.getCell(row + rowCount, col + colCount).getIsFlagged()) {
									expandArea(row + rowCount, col + colCount);
								} else {
									game.select(row + rowCount, col + colCount);
								}
								game.select(row + rowCount, col + colCount);
							}
					}
				}
		 }
	}
	/**
	 * checks to see what the game state is.
	 * @return true if the game is still going, false if it is over, won or lost
	 */
	private boolean checkGameStatus() {
		 if (game.getGameStatus() != GameStatus.NotOverYet) {
				return false;
		 } else {
			 return true;
		 }
		 
	}
}


