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
		// create an enemy and give it runAway behaviour
		Coward coward = new Coward(2,2);
		coward.setMovementBehaviour(new RunAwayBehaviour());
		board.addBoardEntity(coward);
		Player player = new Player(2, 0);
		board.addBoardEntity(player);
		/*
		 * if player is directly above the strategist, it should move either down, or left, or right - not up
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
		Coward coward1 = new Coward(2,3);
		coward1.setRegularMovement(new RunAwayBehaviour()); // make it always run away for this test
		board.addBoardEntity(coward1);
		Player player = new Player(2, 2);
		board.addBoardEntity(player);
		
		// add board elements
		board.addBoardEntity(new Wall(1, 3));
		board.addBoardEntity(new Wall(1, 1));
		board.addBoardEntity(new Wall(1, 2));
		board.addBoardEntity(new Wall(2, 1));
		board.addBoardEntity(new Wall(3, 4));
		board.addBoardEntity(new Wall(3, 1));
		board.addBoardEntity(new Wall(3, 2));
		board.addBoardEntity(new Wall(3, 3));
		

		coward1.update(board);
		assertEquals(2, coward1.getX());
		assertEquals(4, coward1.getY());
		
		// display coward current position
		for (int i = 0; i < 20; i++) {
			coward1.update(board);
		}
		
		assertEquals(4, coward1.getX());
		assertEquals(4, coward1.getY());
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
		Player player1 = new Player(2,1);
		board.addBoardEntity(hunter);
		board.addBoardEntity(player1);
		
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
		Hunter hunter1 = new Hunter(1,1);
		Player player2 = new Player(1,4);
		board.addBoardEntity(hunter1);
		board.addBoardEntity(player2);
		
		hunter1.update(board);
		assertEquals(2, hunter1.getY());
		assertEquals(1, hunter1.getX());
		for(int i = 0; i < 5; i++) {
			hunter1.update(board);
		}
		
		assertEquals(4, hunter1.getY());
		assertEquals(1, hunter1.getX());
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
		Hunter hunter2 = new Hunter(2,0);
		Player player3 = new Player(4,0);
		board.addBoardEntity(hunter2);
		board.addBoardEntity(player3);
		
		board.addBoardEntity(new Wall(3,0));
		board.addBoardEntity(new Wall(1,1));
		board.addBoardEntity(new Wall(2,1));
		board.addBoardEntity(new Wall(2,2));
		board.addBoardEntity(new Wall(3,2));
		board.addBoardEntity(new Wall(0,3));
		board.addBoardEntity(new Wall(2,3));
		board.addBoardEntity(new Wall(0,4));
		board.addBoardEntity(new Wall(4,4));
		
		
		hunter2.update(board);
		System.out.println("strategist: " + hunter2.getX() + "," + hunter2.getY());
		//assertEquals(1, strategist.getX());
		//assertEquals(0, strategist.getY());
		
		// display hunter current position
		for(int i = 0; i < 15; i++) {
			hunter2.update(board);
			System.out.println("strategist: " + hunter2.getX() + "," + hunter2.getY());
		}
		assertEquals(4, hunter2.getX());
		assertEquals(0, hunter2.getY());
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
		Player player4 = new Player(1,2);
		player4.setDirection(Direction.RIGHT);
		board.addBoardEntity(strategist);
		board.addBoardEntity(player4);
		
		strategist.update(board);
		
		assertEquals(2, strategist.getY());
		assertEquals(2, strategist.getX());
		
		// should remain in the same place if (player is still looking to the left)
		strategist.update(board);
		
		assertEquals(2, strategist.getY());
		assertEquals(2, strategist.getX());
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
