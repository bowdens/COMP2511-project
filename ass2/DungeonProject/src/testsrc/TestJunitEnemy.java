package testsrc;

import project.model.*;
import project.model.enemies.Strategist;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class TestJunitEnemy {
	@Before
	public void setUp() {
		Game game = new Game();
		game.createNewBoard("testBoard1", 4, 9);
		//place a player on the board
		Player player = new Player(3,4);
		
	}
	
	//tests UC1.5
	@Test
	public void testHuntsmanKillsPlayer() {
		//place huntsman at other end of dungeon
		
		//player moves into a wall each turn
		//huntsman eventually attacks player
		//game over
	}
	
	//tests UC1.6
	@Test
	public void testHoundKillsPlayer() {
		//hound in board[1][7], huntsman in board[0][8]
		
		//hound attacks player
		//game over
	}
	
	//tests UC1.7
	@Test
	public void testStrategistKillsPlayer() {
		Board b = new Board("Strateg_Board", 9, 9);
		//player moves straight from [0][0] to [0][8]
		Player player = new Player(0,0);
		for(int i = 0; i < 8; i ++) {
			player.moveUp(b);
		}
		//strategist moves from [1][8] to [0][8]
		Strategist s = new Strategist(0,0);
		for(int i = 0; i < 8; i ++) {
			player.moveUp(b);
		}
	}
	
	//tests UC1.8
	@Test
	public void testCowardKillsPlayer() {
		//player moves straight towards the coward and dies
	}
}
