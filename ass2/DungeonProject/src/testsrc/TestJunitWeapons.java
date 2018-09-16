package testsrc;

import project.model.*;
import project.model.enemies.Hunter;
import project.model.items.Arrow;
import project.model.items.Sword;
import project.model.obstacles.Exit;
import project.model.obstacles.Wall;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;


public class TestJunitWeapons {
	
	private Player player;
	private Board board;
	private Arrow arrow;
	private Sword sword;

	
	@Before
	public void setUp() {
		Game game = new Game();
		game.createNewBoard("testBoard1", 4, 9);
		this.board = game.getCustomDungeonByName("testBoard1");
		board.addBoardEntity(player);
		board.addBoardEntity(arrow);
		board.addBoardEntity(sword);
		
		//place a player on the board
		this.player = new Player(2,2);
	}
	
	//tests UC1.9 & UC1.16
	@Test
	public void testArrowKill() {
		TestJunitWeapons test1 = new TestJunitWeapons();
		test1.setUp();
		//one arrow next to player
		Arrow arrow = new Arrow(2,3);
		//huntsman at other end of dungeon
		Hunter hunt = new Hunter(4,9);
		//player kills huntsman and wins level
		for(int i = 0; i < 2; i ++) {
			player.moveDown(board);
		}
		for(int i = 0; i < 6; i ++) {
			player.moveRight(board);
		}
	}
	
	//tests UC1.15
	@Test
	public void testSwordKill() {
		TestJunitWeapons test2 = new TestJunitWeapons();
		test2.setUp();
		//one sword next to player
		Sword s = new Sword(3,2);
		//6 different kinds of enemies
		//kills 5 of the 6, 6th one kills the player
	}
	
	//tests UC1.14
	@Test
	public void testBomb() {
		TestJunitWeapons test3 = new TestJunitWeapons();
		test3.setUp();
		//maybe make a very small map with a coward and a boulder
		//check if everything blows up except the player
	}
}
