package testsrc;

import java.util.ArrayList;

import project.model.*;
import project.model.obstacles.Pit;
import project.model.obstacles.Boulder;
import project.model.obstacles.FloorSwitch;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestJunitObstacles {
	private Game game;
	private GamePlayer gamePlayer;
	private Player player;
	private Board board;
	
	@Before
	public void setUp() {
		this.game = new Game();
		game.createNewBoard("testBoard1", 4, 9);
		this.board = game.getCustomDungeonByName("testBoard1");
		game.setCurrentBoard(board);
		this.player = new Player(1,1);
		board.addBoardEntity(player);
		
	}
	
	@Test
	public void testDoorAndKey() {
		//player attempt to access door without key - doesn't work
		//player gets key and opens door
		//repeat again with another door
	}
	
	//tests US1.11 AND US1.19
	@Test
	public void testPitFall() {
		this.gamePlayer = new GamePlayer(board);
		game.setGamePlayer(gamePlayer);
		Pit pit = new Pit(2,1);
		board.addBoardEntity(pit);
		
		game.movePlayer("Right");
		game.newTurn();
		
		//endGame() is called, gameStatus is set to 2
		assertEquals(board.getGameStatus(), 2);
	}
	
	//test US1.13
	@Test
	public void testMoveBoulder() {
		this.gamePlayer = new GamePlayer(board);
		game.setGamePlayer(gamePlayer);
		Boulder boulder = new Boulder(2,1);
		board.addBoardEntity(boulder);
		//player moves right 5 times, pushing the boulder to the end of the dungeon
		game.movePlayer("Right");game.newTurn();
		game.movePlayer("Right");game.newTurn();
		game.movePlayer("Right");game.newTurn();
		game.movePlayer("Right");game.newTurn();
		game.movePlayer("Right");game.newTurn();
		assertEquals(player.getX(),6);
		assertEquals(player.getY(),1);
		assertEquals(boulder.getX(),7);
		assertEquals(boulder.getY(),1);
		//player can't move right again as the boulder can't move into the wall
		game.movePlayer("Right");game.newTurn();
		assertEquals(player.getX(),6);
		assertEquals(player.getY(),1);
		assertEquals(boulder.getX(),7);
		assertEquals(boulder.getY(),1);
	}
	
	@Test
	public void testBoulderSwitch() {
		//push boulder onto switch
		//win level
	}
	
	@Test
	public void testBoulderPit() {
		this.gamePlayer = new GamePlayer(board);
		game.setGamePlayer(gamePlayer);
		
		Boulder boulder = new Boulder(2,1);
		board.addBoardEntity(boulder);
		Pit pit = new Pit(3,1);
		board.addBoardEntity(pit);
		
		game.movePlayer("Right");game.newTurn();
		assertEquals(player.getX(),2);
		assertEquals(player.getY(),1);
		
		ArrayList<BoardEntity> entities = board.getBoardEntities();
		
		//boulder is nowhere on the board
		
		for(BoardEntity ent : entities) {
			assertFalse(ent instanceof Boulder);
		}
		//player has advanced 1 block right and is the only entity at (2,1)
		entities = board.getEntitiesAt(2,1);
		assertEquals(1, entities.size());
		assert(entities.get(0) instanceof Player);
		//pit is the only entity at (3,1)
		entities = board.getEntitiesAt(3,1);
		assertEquals(1, entities.size());
		assert(entities.get(0) instanceof Pit);
	}
	
	
}
