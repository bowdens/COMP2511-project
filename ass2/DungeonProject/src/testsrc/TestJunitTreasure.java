package testsrc;

import project.model.Board;
import project.model.Game;
import project.model.Player;
import project.model.collisionBehaviours.PickUpTreasure;
import project.model.items.Treasure;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class TestJunitTreasure {
	
	private Board board;
	private Player player;
	private Treasure treasure;
	
	@Before
	public void setUp() {
		Game game = new Game();
		game.createNewBoard("testBoard1", 5, 5);
		this.board = game.getCustomDungeonByName("testTreasure");
		//place a player on the board
		board.addBoardEntity(player);
		board.addBoardEntity(treasure);
		this.player = new Player(3,3);
		//place treasure
		
	}
	
	//Tests US1.4
	@Test
	public void testCollectTreasure() {	
		
		TestJunitTreasure test = new TestJunitTreasure();
		test.setUp();
		//collect treasure
		this.treasure = new Treasure(4,4);
		
		this.player.moveDown(board);
		this.player.moveRight(board);
		
		//win game
	}
}
