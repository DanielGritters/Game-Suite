package gameCode;

import java.util.Random;

/**
 * this allows the cells to function and is linked to the panel.
 * @author Dan Gritters
 * @version 1.0
 * @since 1.0
 *
 */
public class MineSweeperGame {
	private Cell[][] board;
	private int totalMineCount;
	private int size;
	
	/**
	 * constructor that creates the board according to the given size and 
	 * number of mines.
	 * @param sizeOfGame is how big the board will be
	 * @param mines is the number of mines there will be
	 */
	public MineSweeperGame(final int sizeOfGame, final int mines) {
		this.size = sizeOfGame;
		totalMineCount = mines;
		createBoard();
	}
	/**
	 * selects the cell at the give location if it isn't flagged.
	 * @param row of the array
	 * @param col of the array
	 */
	public void select(final int row, final int col) {
		if (board[row][col].getIsFlagged()) {
			return;
		} else {
			board[row][col].changeIsExposed(true);
		}
	}
	/**
	 * resets the board completely.
	 */
	public void reset() {
		createBoard();
	}
	/**
	 * returns the status of the game, if a bomb is selected you lose, 
	 * if all the nonbombs are selected without a bomb
	 * being selected you win, otherwise it is still going.
	 * @return the status of the game
	 */
	public GameStatus getGameStatus() {
		int winCondition = 1;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (board[row][col].getIsMine() && board[row][col].isExposed()) {
						return GameStatus.Lost;
				} else if (!board[row][col].getIsMine() && !board[row][col].isExposed()) {
					winCondition = 0;
				}
			}
		}
		if (winCondition == 1) {
			return GameStatus.Won;
		}
		return GameStatus.NotOverYet;
	}
	/**
	 * returns the cell at the given location.
	 * @param row of the array
	 * @param col of the array
	 * @return the cell
	 */
	public Cell getCell(final int row, final int col) {
		return board[row][col];
	}
	/**
	 * returns the board.
	 * @return the board
	 */
	public Cell[][] getBoard() {
		return this.board;
	}
	/**
	 * uses a random number generator to randomly select 
	 * the cells created in the 2D array to turn into bombs.
	 */
	private void createBoard() {
		board = new Cell[size][size];
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				board[row][col] = new Cell();
			}
		}
		Random random = new Random();
		int mineCount = 0;
		
		//sets the mines in their cell
		while (mineCount < totalMineCount) {
			int col = random.nextInt(size);
			int row = random.nextInt(size);
			if (!board[row][col].getIsMine()) {
				board[row][col].changeIsMine(true);
				mineCount++;
				//changes the mine count of the adjacent cells
				int rowCount = -1;
				while (rowCount != 2) {
					int colCount = -1;
					while (colCount != 2) {
					
						if (row + rowCount >= 0 && row + rowCount < size && col + colCount >= 0 && col + colCount < size) {
							board[row + rowCount][col + colCount].incrMine();
						}
						colCount++;
					}
					rowCount++;
				}
			}
			
		}
	}
}
