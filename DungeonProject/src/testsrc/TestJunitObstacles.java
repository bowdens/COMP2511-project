package testsrc;

import java.util.ArrayList;

import project.model.*;
import project.model.enemies.Hunter;
import project.model.items.Key;
import project.model.obstacles.Pit;
import project.model.obstacles.Trap;
import project.model.obstacles.Wall;
import project.model.obstacles.Boulder;
import project.model.obstacles.BoulderSwitch;
import project.model.obstacles.Door;
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
		/*
		 * board:
		 *   0 1 2 3
		 * 0 _ _ _ _
		 * 1 _ K _ _
		 * 2 _ D P _
		 * 3 _ _ _ _
		 */
		//player attempt to access door without key - doesn't work
		//player gets key and opens door
		//repeat again with another door
		board = new Board("test", 4, 4);
		player.setX(2);
		player.setY(2);
		Key key = new Key(1, 1, 1);
		Door door = new Door(1, 2, 1);
		
		board.addBoardEntity(player);
		board.addBoardEntity(key);
		board.addBoardEntity(door);
		
		assertFalse(player.moveLeft(board));
		assertTrue(player.moveUp(board));
		assertTrue(player.moveLeft(board));
		// have to update the door
		door.update(board);
		assertTrue(player.hasKey(key.getKeyID()));
		// check key is gone
		ArrayList<BoardEntity> entities = board.getEntitiesAt(1, 1);
		assertEquals(1, entities.size());
		assertTrue(entities.contains(player));
		assertTrue(door.isOpen());
		
		assertTrue(player.moveDown(board));
		entities = board.getEntitiesAt(1, 2);
		assertEquals(2, entities.size());
		assertTrue(entities.contains(player));
		assertTrue(entities.contains(door));
		
		
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
	
	@Test
	public void testPushBoulderOnSwitch() {
		/*   0 1 2
		 * 0 _ P _
		 * 1 _ B _
		 * 2 _ S _
		 */
		board = new Board("test", 3, 3);
		player = new Player(1, 0);
		player.setDirection(Direction.DOWN);
		board.addBoardEntity(player);
		Boulder boulder = new Boulder(1, 1);
		board.addBoardEntity(boulder);
		FloorSwitch floorSwitch = new FloorSwitch(1, 2);
		board.addBoardEntity(floorSwitch);
		
		player.moveDown(board);
		
		// check the player has moved
		assertEquals(1, player.getX());
		assertEquals(1, player.getY());
		
		ArrayList<BoardEntity> entities = board.getEntitiesAt(1, 2);
		assertEquals(1, entities.size());
		assertTrue(entities.get(0) instanceof BoulderSwitch);
		
		entities = board.getEntitiesAt(1, 1);
		assertEquals(1, entities.size());
		assertTrue(entities.get(0) instanceof Player);
	}
	
	@Test
	public void moveBoulderOffSwitchSucessful() {
		/*   0 1 2        0 1 2
		 * 0 _ _ _      0 _ _ _
		 * 1 P S _  --> 1 _pf B
		 * 2 _ _ _      2 _ _ _
		 */
		board = new Board("test", 3, 3);
		player = new Player(0, 1);
		player.setDirection(Direction.RIGHT);
		board.addBoardEntity(player);
		BoulderSwitch boulderSwitch = new BoulderSwitch(1, 1);
		board.addBoardEntity(boulderSwitch);
		
		assertTrue(player.moveRight(board));
		ArrayList<BoardEntity> entities = board.getEntitiesAt(1, 1);
		System.out.println(entities);
		assertEquals(2, entities.size());
		assertTrue(entities.contains(player));
		assertTrue(entities.get(0) instanceof FloorSwitch || entities.get(1) instanceof FloorSwitch);
		
		entities = board.getEntitiesAt(2, 1);
		assertEquals(1, entities.size());
		assertTrue(entities.get(0) instanceof Boulder);
	}
	
	@Test
	public void moveBoulderOffSwitchFail() {
		/*   0 1 2        0 1 2
		 * 0 _ _ _      0 _ _ _
		 * 1 P S W  --> 1 P S W
		 * 2 _ _ _      2 _ _ _
		 */
		board = new Board("test", 3, 3);
		player = new Player(0, 1);
		player.setDirection(Direction.RIGHT);
		board.addBoardEntity(player);
		BoulderSwitch boulderSwitch = new BoulderSwitch(1, 1);
		board.addBoardEntity(boulderSwitch);
		Wall wall = new Wall(2, 1);
		board.addBoardEntity(wall);
		
		assertFalse(player.moveRight(board));
		
		ArrayList<BoardEntity> entities = board.getEntitiesAt(0, 1);
		assertTrue(entities.contains(player));
		assertEquals(1, entities.size());
		
		entities = board.getEntitiesAt(1, 1);
		assertTrue(entities.contains(boulderSwitch));
		assertEquals(1, entities.size());
		
		entities = board.getEntitiesAt(2, 1);
		assertTrue(entities.contains(wall));
		assertEquals(1, entities.size());
		
		assertTrue(player.moveUp(board));
		assertTrue(player.moveRight(board));
		assertTrue(player.moveDown(board));
		
		entities = board.getEntitiesAt(1, 1);
		assertTrue(entities.contains(player));
		assertEquals(2, entities.size());
		assertTrue(entities.get(0) instanceof FloorSwitch || entities.get(1) instanceof FloorSwitch);
		
		entities = board.getEntitiesAt(1,2);
		assertEquals(1, entities.size());
		assertTrue(entities.get(0) instanceof Boulder);
	}
	
	@Test
	public void testPlayerTrap() {
		/*   0 1 2
		 * 0 _ _ _ 
		 * 1 P T _
		 * 2 _ _ _
		 */
		board = new Board("test", 3, 3);
		player = new Player(0, 1);
		player.setDirection(Direction.RIGHT);
		board.addBoardEntity(player);
		Trap trap = new Trap(1, 1);
		board.addBoardEntity(trap);
		
		assertTrue(player.moveRight(board));
		// game state should be 2 (game is lost)
		assertEquals(board.getGameStatus(), 2);
		
	}
	
	@Test
	public void testEnemyTrap() {
		/*   0 1 2
		 * 0 _ E _ 
		 * 1 E T _
		 * 2 _ _ _
		 */
		board = new Board("test", 3, 3);
		Enemy enemy1 = new Hunter(0, 1);
		Enemy enemy2 = new Hunter(1, 0);
		enemy1.setDirection(Direction.RIGHT);
		enemy2.setDirection(Direction.DOWN);
		board.addBoardEntity(enemy1);
		board.addBoardEntity(enemy2);
		Trap trap = new Trap(1, 1);
		board.addBoardEntity(trap);
		
		assertTrue(enemy1.moveRight(board));
		
		ArrayList<BoardEntity> entities = board.getEntitiesAt(1, 1);
		assertEquals(entities.size(), 0);
		
		assertTrue(enemy2.moveDown(board));
		
		entities = board.getEntitiesAt(1, 1);
		assertEquals(entities.size(), 1);
		assertTrue(entities.contains(enemy2));
		
	}
}
