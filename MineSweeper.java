package gameCode;

import javax.swing.JFrame;
/**
 * 
 * @author Dan Gritters
 * For CIS350 Final Project - Game Suite
 * runs the minesweeper game by creating a JFrame and attaching the 
 * minesweeper panel to it
 * @version 1.0
 * @since 1.0
 */

public class MineSweeper {
	
	/**
	 * main method.
	 * @param args arguments
	 */
	public static void main(final String[] args) {
		JFrame frame;
		frame = new JFrame("MineSweeper");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MineSweeperPanel panel = new MineSweeperPanel();
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
