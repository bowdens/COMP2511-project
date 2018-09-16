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
	private Treasure treasure1, treasure2;
	
	@Before
	public void setUp() {
		Game game = new Game();
		game.createNewBoard("testBoard1", 4, 4);
		this.board = game.getCustomDungeonByName("testBoard1");
		this.player = new Player(1,1);
		this.treasure1 = new Treasure(2,1);
		this.treasure2 = new Treasure(2,2);
		board.addBoardEntity(player);
		board.addBoardEntity(treasure1);
		board.addBoardEntity(treasure2);
	}
	
	//Tests US1.4
	//-collects two pieces of treasure
	//-wins game
	@Test
	public void testCollectTreasure() {	
		
		assertEquals(board.howMuchTreasureLeft(), 2);
		this.player.moveRight(board);
		assertEquals(board.howMuchTreasureLeft(), 1);
		this.player.moveDown(board);
		assertEquals(board.howMuchTreasureLeft(), 0);
		//win game
	}
}
