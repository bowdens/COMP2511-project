package project.model.movementBehaviours;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.model.Board;
import project.model.Direction;
import project.model.Player;
import project.model.enemies.Strategist;
import project.model.obstacles.Wall;

public class TestMovementBehaviours {
	private Board board;
	
	@BeforeEach
	void setup() {
		board = new Board("test", 5,5);
	}
	
	@Test
	void testRunAwayBehaviourSimple() {
		// create enemy, give it runAway behaviour
		Strategist strategist = new Strategist(2,2);
		strategist.setMovementBehaviour(new RunAwayBehaviour());
		board.addBoardEntity(strategist);
		Player player = new Player(2, 0);
		board.addBoardEntity(player);
		/*
		 * player is directly above the strategist, it should move either down, or left, or right - not up
		 */
		strategist.update(board);
		
		assertFalse(strategist.getX() == 2 && strategist.getY() == 1);
	}
	
	@Test
	void testRunAwayBehaviourComplex() {
		/*
		 * Board:
		 *   0 1 2 3 4
		 * 0 _ _ _ _ _
		 * 1 _ W W W _
		 * 2 _ W P W _
		 * 3 _ W S W _
		 * 4 _ _ _ W _ <- will end up at this tile
		 */
		// create enemy, give it runAway behaviour
		Strategist strategist = new Strategist(2,3);
		strategist.setMovementBehaviour(new RunAwayBehaviour());
		board.addBoardEntity(strategist);
		Player player = new Player(2, 2);
		board.addBoardEntity(player);
		
		board.addBoardEntity(new Wall(1, 1));
		board.addBoardEntity(new Wall(1, 2));
		board.addBoardEntity(new Wall(1, 3));
		board.addBoardEntity(new Wall(2, 1));
		board.addBoardEntity(new Wall(3, 1));
		board.addBoardEntity(new Wall(3, 2));
		board.addBoardEntity(new Wall(3, 3));
		board.addBoardEntity(new Wall(3, 4));

		strategist.update(board);
		assertEquals(2, strategist.getX());
		assertEquals(4, strategist.getY());
		
		for (int i = 0; i < 20; i++) {
			strategist.update(board);
			System.out.println("At " + strategist.getX() + "," + strategist.getY());
		}
		
		assertEquals(4, strategist.getX());
		assertEquals(4, strategist.getY());
	}
	
	@Test
	void testMoveTowardsBehaviourSimple() {
		/*
		 * board:
		 *   0 1 2 3 4
		 * 0 _ _ _ _ _
		 * 1 _ S P _ _
		 * 2 _ _ _ _ _
		 * 3 _ _ _ _ _
		 * 4 _ _ _ _ _
		 */
		Strategist strategist = new Strategist(1,1);
		strategist.setMovementBehaviour(new GoTowardsPlayer());
		Player player = new Player(2,1);
		board.addBoardEntity(strategist);
		board.addBoardEntity(player);
		
		strategist.update(board);
		assertEquals(2, strategist.getX());
		assertEquals(1, strategist.getY());
	}
	
	@Test
	void testMoveTowardsBehaviourMedium() {
		/*
		 * board:
		 *   0 1 2 3 4
		 * 0 _ _ _ _ _
		 * 1 _ S _ _ _
		 * 2 _ _ _ _ _
		 * 3 _ _ _ _ _
		 * 4 _ P _ _ _
		 */
		Strategist strategist = new Strategist(1,1);
		strategist.setMovementBehaviour(new GoTowardsPlayer());
		Player player = new Player(1,4);
		board.addBoardEntity(strategist);
		board.addBoardEntity(player);
		
		strategist.update(board);
		assertEquals(1, strategist.getX());
		assertEquals(2, strategist.getY());
		for(int i = 0; i < 5; i++) {
			strategist.update(board);
		}
		assertEquals(1, strategist.getX());
		assertEquals(4, strategist.getY());
	}
	
	@Test
	void testMoveTowardsBehaviourComplex() {
		/*
		 * board:
		 *   0 1 2 3 4
		 * 0 _ _ S W P
		 * 1 _ W W _ _
		 * 2 _ _ W W _
		 * 3 W _ W _ _
		 * 4 W _ _ _ W
		 */
		Strategist strategist = new Strategist(2,0);
		strategist.setMovementBehaviour(new GoTowardsPlayer());
		Player player = new Player(4,0);
		board.addBoardEntity(strategist);
		board.addBoardEntity(player);
		
		board.addBoardEntity(new Wall(3,0));
		board.addBoardEntity(new Wall(1,1));
		board.addBoardEntity(new Wall(2,1));
		board.addBoardEntity(new Wall(2,2));
		board.addBoardEntity(new Wall(3,2));
		board.addBoardEntity(new Wall(0,3));
		board.addBoardEntity(new Wall(2,3));
		board.addBoardEntity(new Wall(0,4));
		board.addBoardEntity(new Wall(4,4));
		
		
		strategist.update(board);
		System.out.println("strategist: " + strategist.getX() + "," + strategist.getY());
		//assertEquals(1, strategist.getX());
		//assertEquals(0, strategist.getY());
		for(int i = 0; i < 15; i++) {
			strategist.update(board);
			System.out.println("strategist: " + strategist.getX() + "," + strategist.getY());
		}
		assertEquals(4, strategist.getX());
		assertEquals(0, strategist.getY());
	}
	
	@Test
	void testPredictSimple() {
		/*
		 * board:
		 *   0 1 2 3 4
		 * 0 _ _ _ _ _
		 * 1 _ _ S _ _
		 * 2 _ P * _ _
		 * 3 _ _ _ _ _
		 * 4 _ _ _ _ _
		 * player looking to the right
		 */
		Strategist strategist = new Strategist(2,1);
		strategist.setMovementBehaviour(new PredictBehaviour());
		Player player = new Player(1,2);
		player.setDirection(Direction.RIGHT);
		board.addBoardEntity(strategist);
		board.addBoardEntity(player);
		
		strategist.update(board);
		assertEquals(2, strategist.getX());
		assertEquals(2, strategist.getY());
		
		// should remain in the same place (player is still looking to the left)
		strategist.update(board);
		assertEquals(2, strategist.getX());
		assertEquals(2, strategist.getY());
		
	}
}
