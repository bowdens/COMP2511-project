package project.model.movementBehaviours;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.model.Board;
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
		 * player is directly above the strategist, it should move straight down
		 */
		strategist.update(board);
		
		assertEquals(2, strategist.getX());
		assertEquals(3, strategist.getY());
		
		// should move straight down again
		strategist.update(board);
		assertEquals(2, strategist.getX());
		assertEquals(4, strategist.getY());
		
		// now has reached the bottom - should move either left or right (doesn't matter which)
		strategist.update(board);
		assertEquals(4, strategist.getY());
		assertTrue(strategist.getX() == 1 || strategist.getX() == 3);
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
}
