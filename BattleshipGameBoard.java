/**
 * @author Seth Pefley
 * CIS 350 - Intro to Software Engineering
 * Methods implemented by Battleship.java to run Battleship game
 */

import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class BattleshipGameBoard {
	
	static int[][] board;
	protected static int numShots = 0;
	protected static int shipsRemaining;
	
	/**
	 * Constructor
	 * Initializes the game board, filling all cells with -1 initially,
	 * then randomly places ships on the grid. The constructor finishes by printing 
	 * the initial board containing only ships and water.
	 */
	public BattleshipGameBoard() {
		
		board = new int[5][5];
		initBoard();
		initShips();
		printBoard();
		
	}
	
	/**
	 * Initializes the board array with all values in the array being equal to -1,
	 * which is used to represent spaces that do not contain a ship and have not yet
	 * been shot at.
	 */
	public void initBoard() {
		
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				board[row][col] = -1;
			}
		}
		
	}
	
	/**
	 * Test used to make sure initBoard() fills every cell in board with -1
	 * 
	 * @return boolean
	 */
	private boolean initTest() {
		
		board = new int[5][5];
				
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board.length; col++) {
				if(board[row][col] != -1) {
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	/**
	 * Uses Random() to obtain two random integers 0-4 which are used to place the ships within
	 * the multi-dimensional array. Also keeps track of the number of ships that have been placed,
	 * and whether the required amount of ships have been placed on the board.
	 */
	public void initShips() {
		
		Random r = new Random();
		boolean placed = false;
		int shipCount = 0;
		
		while(placed==false) {
			
			int x = r.nextInt(5);
			int y = r.nextInt(5);
				
			if(board[x][y] != -2) {
				board[x][y] = -2;
				shipCount++;
			}
				
			if(shipCount == 3) {
				shipsRemaining = 3;
				placed = true;
			}
				
		}
	}
	
	/**
	 * Tests whether or not ships are added after calling the constructor.
	 * 
	 * @return boolean
	 */
	private boolean shipsAdded() {
		
		BattleshipGameBoard test = new BattleshipGameBoard();
		
		int shipCount = 0;
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(board[i][j] != -2) {
					shipCount++;
				}
			}
		}
		
		if(shipCount == 3) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Prints out the board with numbered x and y axis. Also gives the different values
	 * character representations when printing out the board.
	 */
	public void printBoard() {
		
		System.out.println("\t\tBATTLESHIP\n");
		System.out.println("Number of shots: " + numShots + "\n\n");
		
		System.out.println("\t1 \t2 \t3 \t4 \t5");
        System.out.println();
        
        for(int row=0 ; row < board.length ; row++ ){
            System.out.print((row+1)+"");
            for(int col=0 ; col < board.length ; col++ ){
                if(board[row][col]==-1 || board[row][col]==-2){
                    System.out.print("\t"+"~");
                }else if(board[row][col]==0){
                    System.out.print("\t"+"O");
                }else if(board[row][col]==1){
                    System.out.print("\t"+"X");
                }
            }
            
            System.out.println();
            System.out.println();
            
        }
		
	}
	
	/**
	 * Allows the player to reveal whether or not each cell has a ship within it or not.
	 * Takes integer input from the user and checks the value of the cell that is chosen. 
	 * Also keeps track of the total number of shots taken in the game and the number of
	 * ships that remain on teh board that have not been hit. 
	 */
	public void shoot() {
	
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter row coordinate: ");
			int row = input.nextInt();
		System.out.print("Enter column coordinate: ");
			int col = input.nextInt();
			
		if(row < 1 || row > 5 || col < 1 || col > 5) {
			System.out.print("Invalid coordinates! Coordinates must be between 1 and 5!");
		}

		if(board[row-1][col-1]==-2) {
			board[row-1][col-1] = 1;
			numShots++;
			shipsRemaining--;
		}
		else if (board[row-1][col-1]==0 || board[row-1][col-1]==1) {
			
			int cont = 1;
			
			while(cont != 0) {
				System.out.println("\n");
				System.out.println("You've already shot at this area! Try new coordinates!");
				System.out.print("Enter 0 to continue: ");
				cont = input.nextInt();
				System.out.println("\n");
			}
		}
		else {
			board[row-1][col-1] = 0;
			numShots++;
		}
		
	}
	
	/**
	 * Allows the player to reveal whether or not each cell has a ship within it or not.
	 * Takes integer input from the user and checks the value of the cell that is chosen. 
	 * Also keeps track of the total number of shots taken in the game and the number of
	 * ships that remain on teh board that have not been hit. 
	 * 
	 * This function takes in two int variables in order to work correctly with the GUI.
	 */
	public void shoot(int row, int col) {
			
		if(row < 1 || row > 5 || col < 1 || col > 5) {
			JOptionPane.showMessageDialog(BattleshipGUI.gameBoardPanel, "Invalid coordinates! Coordinates must be between 1 and 5!");
		}

		if(board[row-1][col-1]==-2) {
			board[row-1][col-1] = 1;
			numShots++;
			shipsRemaining--;
		}
		else if (board[row-1][col-1]==0 || board[row-1][col-1]==1) {
				JOptionPane.showMessageDialog(BattleshipGUI.gameBoardPanel, "You've already shot at this area! Try new coordinates!");
		}
		else {
			board[row-1][col-1] = 0;
			numShots++;
		}
		
	}

}