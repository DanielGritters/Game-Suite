package gameCode;
/**
 * 
 * @author Dan Gritters
 * For CIS350 Final Project - Game Suite
 * Cell contains all the information needed for each mine sweeper cell
 * @version 1.0
 * @since 1.0
 */
public class Cell {
	//adjacent mines
	private int mineCount;
	private boolean isFlagged;
	private boolean isExposed;
	private boolean isMine;
	/**
	 * Cell Constructor initializes everything to 0 and false.
	 */
	public Cell() {
		this.mineCount = 0;
		this.isFlagged = false;
		this.isExposed = false;
		this.isMine = false;
	}

	/**
	 * change flag to its opposite.
	 */
	public void changeIsFlagged() {
		if (this.isFlagged) {
			this.isFlagged = false;
		} else {
			this.isFlagged = true;
		}
	}
	/**
	 * Flag setter.
	 * @param flag if flagged
	 */
	public void setIsFlagged(final boolean flag) {
		this.isFlagged = flag;
	}
	/**
	 * Exposed setter.
	 * @param exposed if the cell is exposed
	 */
	public void changeIsExposed(final boolean exposed) {
		this.isExposed = exposed;
	}
	/**
	 * isMine setter.
	 * @param mine amount of mines adjacent
	 */
	public void changeIsMine(final boolean mine) {
		this.isMine = mine;
	}
	/**
	 * Mine count getter.
	 * @return int mine count
	 */
	public int getMineCount() {
		return this.mineCount;
	}
	/**
	 * isFlagged getter.
	 * @return bool if the cell is flagged
	 */
	public boolean getIsFlagged() {
		return this.isFlagged;
	}
	/**
	 * get if exposed.
	 * @return bool if it is exposed
	 */
	public boolean isExposed() {
		return this.isExposed;
	}
	/**
	 * return if the cell is a mines.
	 * @return bool if mine
	 */
	public boolean getIsMine() {
		return this.isMine;
	}
	/**
	 * increment the amount adjacent mines.
	 */
	public void incrMine() {
		this.mineCount++;
	}
}
