package project.model.canMoveOntoDecorators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.model.Board;
import project.model.obstacles.*;
import project.model.Direction;
import project.model.Player;
import project.model.enemies.Strategist;

public class CanMoveOntoTest {
	private Player player;
	private Board board;
	
	@BeforeEach
	void setup() throws Exception {
		// create board size 5,5
		board = new Board("Test board", 5, 5);
		// create player at 5,5
		player = new Player(2, 2);
		
		board.addBoardEntity(player);
	}
	
	@Test
	void testPlayerMovingOntoPit() {
		Pit pit = new Pit(2, 3);
		
		// test moving player
		assertTrue(pit.canMoveOnto(board, player));
	}
	
	@Test
	void testBoulderMovingIntoPit() {
		Pit pit = new Pit(2,3);
		Boulder boulder = new Boulder(3, 3);
		
		// test moving boulder
		assertTrue(pit.canMoveOnto(board, boulder));
	}
	
	@Test
	void testBoulderMovingOntoBoulder() {
		Boulder boulder1 = new Boulder(1,1);
		Boulder boulder2 = new Boulder(2, 1);
		board.addBoardEntity(boulder1);
		board.addBoardEntity(boulder2);
		// boulder can't move into together
		assertFalse(boulder1.canMoveOnto(board, boulder2));
	}
	
	@Test
	void testPlayerMovingOntoBoulderInFrontOfBoulder() {
		/*
		 * board looks like this:
		 *   0 1 2 3 4
		 * 0 _ _ _ _ _
		 * 1 _ _ _ _ _
		 * 2 _ _ P _ _
		 * 3 _ _ B _ _
		 * 4 _ _ B _ _
		 */
		
		player.setDirection(Direction.DOWN);
		Boulder boulder1 = new Boulder(2, 3);
		Boulder boulder2 = new Boulder(2, 4);
		board.addBoardEntity(boulder1);
		board.addBoardEntity(boulder2);
		
		// the player should not be able to move onto boulder1
		assertFalse(boulder1.canMoveOnto(board, player));
	}
	
	@Test
	void testPlayerMovingOntoBoulderInFrontOfNoBoulder() {
		/*
		 * board looks like this:
		 *   0 1 2 3 4
		 * 0 _ _ _ _ _
		 * 1 _ _ _ _ _
		 * 2 _ _ _ _ _
		 * 3 _ _ B P _
		 * 4 _ _ B _ _
		 */
		
		player.setDirection(Direction.LEFT);
		player.setY(3);
		player.setX(3);
		Boulder boulder1 = new Boulder(2, 3);
		Boulder boulder2 = new Boulder(2, 4);
		board.addBoardEntity(boulder1);
		board.addBoardEntity(boulder2);
		
		// the player should not be able to move onto boulder1
		assertTrue(boulder1.canMoveOnto(board, player));
		
	}
	
	@Test
	void testPlayerMovingOntoBoulderWithSwitchBehind() {
		/*
		 * board looks like this:
		 *   0 1 2 3 4
		 * 0 _ _ _ _ _
		 * 1 _ _ _ _ _
		 * 2 _ _ P _ _
		 * 3 _ _ B _ _
		 * 4 _ _ S _ _
		 */
		
		player.setDirection(Direction.DOWN);
		Boulder boulder = new Boulder(2, 3);
		FloorSwitch floorSwitch = new FloorSwitch(2, 4);
		board.addBoardEntity(boulder);
		board.addBoardEntity(floorSwitch);
		
		// the player should be able to move onto the boulder
		assertTrue(boulder.canMoveOnto(board, player));
	}
	
	@Test
	void testMovingEnemyOntoPlayer() {
		/*
		 * board looks like this:
		 *   0 1 2 3 4
		 * 0 _ _ _ _ _
		 * 1 _ _ _ _ _
		 * 2 _ S P _ _
		 * 3 _ _ _ _ _
		 * 4 _ _ _ _ _
		 */
		Strategist strategist = new Strategist(1, 2);
		board.addBoardEntity(strategist);
		assertTrue(player.canMoveOnto(board, strategist));
	}
	
	@Test
	void testEnemyMovingOntoPlayer() {

		/*
		 * board looks like this:
		 *   0 1 2 3 4
		 * 0 _ _ _ _ _
		 * 1 _ _ _ _ _
		 * 2 _ S P _ _
		 * 3 _ _ _ _ _
		 * 4 _ _ _ _ _
		 */
		Strategist strategist = new Strategist(1, 2);
		board.addBoardEntity(strategist);
		assertTrue(strategist.canMoveOnto(board, player));
	}
	
	
	
}
