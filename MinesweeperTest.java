package gameCode;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * testing class.
 * @author Marvin
 *
 */
public class MinesweeperTest {
	/**
	 * tests the game creation.
	 */
	@Test
	   public void gameCreateTest() throws Throwable {
			MineSweeperGame game = new MineSweeperGame(10, 10);
			Cell[][] temp = game.getBoard();
			int mineCount = 0;
			for (int i = 0; i < 10; i++) {
				for (int k = 0; k < 10; k++) {
					Assert.assertEquals(false, (temp[i][k].isExposed()));
					Assert.assertEquals(false, (temp[i][k].getIsFlagged()));
					if (temp[i][k].getIsMine()) {
						mineCount++;
					}
				}
			}
			Assert.assertEquals(mineCount, 10);
	   }
	@Test
	   public void gameWinTest() throws Throwable {
			MineSweeperGame game = new MineSweeperGame(10, 10);
			Cell[][] temp = game.getBoard();
			for (int i = 0; i < 10; i++) {
				for (int k = 0; k < 10; k++) {
					if (!temp[i][k].getIsMine()) {
						game.select(i, k);
					}
				}
			}
			Assert.assertEquals(GameStatus.Won, game.getGameStatus());
	   }
	@Test
	   public void gameLoseTest() throws Throwable {
			MineSweeperGame game = new MineSweeperGame(10, 10);
			Cell[][] temp = game.getBoard();
			for (int i = 0; i < 10; i++) {
				for (int k = 0; k < 10; k++) {
					if (temp[i][k].getIsMine()) {
						game.select(i, k);
					}
				}
			}
			Assert.assertEquals(GameStatus.Lost, game.getGameStatus());
	   }
	@Test
	   public void gameFlagTest() throws Throwable {
			MineSweeperGame game = new MineSweeperGame(10, 10);
			Cell[][] temp = game.getBoard();
			temp[0][0].changeIsFlagged();
			game.select(0, 0);
			Assert.assertEquals(false, temp[0][0].isExposed());
	   }
	@Test
	   public void gameResetTest() throws Throwable {
			MineSweeperGame game = new MineSweeperGame(10, 10);
			Cell[][] temp = game.getBoard();
			for (int i = 0; i < 10; i++) {
				for (int k = 0; k < 10; k++) {
					if (!temp[i][k].getIsMine()) {
						game.select(i, k);
					}
				}
			}
			Assert.assertEquals(GameStatus.Won, game.getGameStatus());
			game.reset();
			temp = game.getBoard();
			int mineCount = 0;
			for (int i = 0; i < 10; i++) {
				for (int k = 0; k < 10; k++) {
					Assert.assertEquals(false, (temp[i][k].isExposed()));
					Assert.assertEquals(false, (temp[i][k].getIsFlagged()));
					if (temp[i][k].getIsMine()) {
						mineCount++;
					}
				}
			}
			Assert.assertEquals(mineCount, 10);
	   }
	@Test
	   public void gameNotOverTest() throws Throwable {
			MineSweeperGame game = new MineSweeperGame(10, 10);
			Cell[][] temp = game.getBoard();
			int done = 0;
			for (int i = 0; i < 10; i++) {
				for (int k = 0; k < 10; k++) {
					if (!temp[i][k].getIsMine()) {
						if (done % 2 == 0) {
							game.select(i, k);
							done++;
						}
					}
				}
			}
			Assert.assertEquals(GameStatus.NotOverYet, game.getGameStatus());
	   }
	@Test
	   public void gameGetCellTest() throws Throwable {
			MineSweeperGame game = new MineSweeperGame(10, 10);
			Cell[][] temp = game.getBoard();
			for (int i = 0; i < 10; i++) {
				for (int k = 0; k < 10; k++) {
					Assert.assertEquals(game.getCell(i, k), temp[i][k]);
				}
			}
	   }
	


}
