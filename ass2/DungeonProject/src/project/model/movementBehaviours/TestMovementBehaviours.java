package project.model.movementBehaviours;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.model.Board;
import project.model.Direction;
import project.model.Player;
import project.model.enemies.Coward;
import project.model.enemies.DoorProtector;
import project.model.enemies.Hunter;
import project.model.enemies.Strategist;
import project.model.obstacles.Exit;
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
		Coward coward = new Coward(2,2);
		coward.setMovementBehaviour(new RunAwayBehaviour());
		board.addBoardEntity(coward);
		Player player = new Player(2, 0);
		board.addBoardEntity(player);
		/*
		 * player is directly above the strategist, it should move either down, or left, or right - not up
		 */
		coward.update(board);
		
		assertFalse(coward.getX() == 2 && coward.getY() == 1);
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
		Coward coward = new Coward(2,3);
		coward.setRegularMovement(new RunAwayBehaviour()); // make it always run away for this test
		board.addBoardEntity(coward);
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

		coward.update(board);
		assertEquals(2, coward.getX());
		assertEquals(4, coward.getY());
		
		for (int i = 0; i < 20; i++) {
			coward.update(board);
			//("At " + coward.getX() + "," + coward.getY());
		}
		
		assertEquals(4, coward.getX());
		assertEquals(4, coward.getY());
	}
	
	@Test
	void testMoveTowardsBehaviourSimple() {
		/*
		 * board:
		 *   0 1 2 3 4
		 * 0 _ _ _ _ _
		 * 1 _ H P _ _
		 * 2 _ _ _ _ _
		 * 3 _ _ _ _ _
		 * 4 _ _ _ _ _
		 */
		Hunter hunter = new Hunter(1,1);
		Player player = new Player(2,1);
		board.addBoardEntity(hunter);
		board.addBoardEntity(player);
		
		hunter.update(board);
		assertEquals(2, hunter.getX());
		assertEquals(1, hunter.getY());
	}
	
	@Test
	void testMoveTowardsBehaviourMedium() {
		/*
		 * board:
		 *   0 1 2 3 4
		 * 0 _ _ _ _ _
		 * 1 _ H _ _ _
		 * 2 _ _ _ _ _
		 * 3 _ _ _ _ _
		 * 4 _ P _ _ _
		 */
		Hunter hunter = new Hunter(1,1);
		Player player = new Player(1,4);
		board.addBoardEntity(hunter);
		board.addBoardEntity(player);
		
		hunter.update(board);
		assertEquals(1, hunter.getX());
		assertEquals(2, hunter.getY());
		for(int i = 0; i < 5; i++) {
			hunter.update(board);
		}
		assertEquals(1, hunter.getX());
		assertEquals(4, hunter.getY());
	}
	
	@Test
	void testMoveTowardsBehaviourComplex() {
		/*
		 * board:
		 *   0 1 2 3 4
		 * 0 _ _ H W P
		 * 1 _ W W _ _
		 * 2 _ _ W W _
		 * 3 W _ W _ _
		 * 4 W _ _ _ W
		 */
		Hunter hunter = new Hunter(2,0);
		Player player = new Player(4,0);
		board.addBoardEntity(hunter);
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
		
		
		hunter.update(board);
		System.out.println("strategist: " + hunter.getX() + "," + hunter.getY());
		//assertEquals(1, strategist.getX());
		//assertEquals(0, strategist.getY());
		for(int i = 0; i < 15; i++) {
			hunter.update(board);
			System.out.println("strategist: " + hunter.getX() + "," + hunter.getY());
		}
		assertEquals(4, hunter.getX());
		assertEquals(0, hunter.getY());
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
	
	@Test
	void testMoveTowardsDoor() {
		/*   0 1 2
		 * 0 P _ P 
		 * 1 _ _ _
		 * 2 _ _ E
		 */
		board = new Board("test",3,3);
		DoorProtector dp = new DoorProtector(0,0);
		Exit exit = new Exit(2, 2);
		Player player = new Player(0,2);
		
		board.addBoardEntity(dp);
		board.addBoardEntity(exit);
		board.addBoardEntity(player);
		
		dp.update(board);
		dp.update(board);
		dp.update(board);
		dp.update(board);

		// dp should be on either 1,2 or 2,1
		assertTrue((dp.getX() == 1 && dp.getY() == 2) || (dp.getX() == 2 && dp.getY() == 1));
	}
	
	@Test
	void testMoveBetweenBehaviour() {
		
	}
}
