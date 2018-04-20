/**
 * @author Seth Pefley
 * CIS 350 - Intro to Software Engineering
 * Runs Battleship game using methods from BattleshipGameBoard.java
 */

import java.util.Scanner;

/**
 * Class used to run Battleship text UI.
 * 
 * @author Seth Pefley
 *
 */
public final class Battleship {

	/**
	 * Main function. Runs the Battleship text UI.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {

		Scanner input = new Scanner(System.in);
		int playAgain = 1;

		while (playAgain != 0) {
			BattleshipGameBoard game = new BattleshipGameBoard();

			while (BattleshipGameBoard.shipsRemaining > 0) {
				game.shoot();
				game.printBoard();
			}

			System.out.println("Congratulations! You won the game in " + BattleshipGameBoard.numShots + " shots.");
			System.out.println("Enter 0 to quit");
			playAgain = input.nextInt();

		}
		
		input.close();

	}

}
