package testsrc;

import project.model.Board;
import project.model.Game;
import project.model.Player;
import project.model.collisionBehaviours.PickUpTreasure;
import project.model.items.Treasure;
import project.model.GamePlayer;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJunitTreasure {
	
	private Game game;
	private GamePlayer gamePlayer;
	private Board board;
	private Player player;
	private Treasure treasure1, treasure2;
	
	@Before
	public void setUp() {
		this.game = new Game();
		game.createNewBoard("testBoard1", 4, 4);
		this.board = game.getCustomDungeonByName("testBoard1");
		game.setCurrentBoard(board);
		this.player = new Player(1,1);
		this.treasure1 = new Treasure(2,1);
		this.treasure2 = new Treasure(2,2);
		board.addBoardEntity(player);
		board.addBoardEntity(treasure1);
		board.addBoardEntity(treasure2);
		this.gamePlayer = new GamePlayer(board);
		game.setGamePlayer(gamePlayer);
	}
	
	//Tests US1.4
	//-collects two pieces of treasure
	//-wins game
	@Test
	public void testCollectTreasure() {	
		
		assertEquals(board.howMuchTreasureLeft(), 2);
		//this.player.moveRight(board);
		game.movePlayer("Right");
		assertEquals(board.howMuchTreasureLeft(), 1);
		game.newTurn();
		//this.player.moveDown(board);
		game.movePlayer("Down");
		assertEquals(board.howMuchTreasureLeft(), 0);
		
		//winGame() is called
		game.newTurn();
		assertEquals(board.getGameStatus(), 1);
		
	}
}
