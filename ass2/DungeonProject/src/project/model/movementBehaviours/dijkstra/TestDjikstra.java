package project.model.movementBehaviours.dijkstra;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.model.Board;
import project.model.BoardEntity;
import project.model.obstacles.*;
import project.model.Direction;
import project.model.Player;
import project.model.enemies.Strategist;

public class TestDjikstra {
	private Board board;
	private Strategist strategist;
	
	@BeforeEach
	void setup() throws Exception {
		// create board size 5,5
		board = new Board("Test board", 5, 5);
		strategist = new Strategist(2, 3);
	}
	
	@Test
	void testNoMovement() {
		Direction nextMove = Dijkstra.getNextMove(board, strategist, 2, 3);
		assertTrue(nextMove == Direction.NONE);
	}
	
	@Test
	void testMoveDown() {
		Direction nextMove = Dijkstra.getNextMove(board, strategist, 2, 4);
		assertTrue(nextMove == Direction.DOWN);
	}
	
	@Test
	void testMoveUp() {
		Direction nextMove = Dijkstra.getNextMove(board, strategist, 2, 0);
		assertTrue(nextMove == Direction.UP);
	}
	
	@Test
	void testMoveAround() {
		/*
		 * Board:
		 *   0 1 2 3 4
		 * 0 _ _ _ _ _
		 * 1 _ _ _ _ _
		 * 2 _ _ X _ _
		 * 3 _ B B B _
		 * 4 _ _ E B _
		 */
		strategist.setX(2);
		strategist.setY(4);
		board.addBoardEntity(new Boulder(3, 4));
		board.addBoardEntity(new Boulder(3, 3));
		board.addBoardEntity(new Boulder(2, 3));
		board.addBoardEntity(new Boulder(1, 3));
		
		Direction nextMove = Dijkstra.getNextMove(board, strategist, 2, 2);
		assertTrue(nextMove == Direction.LEFT);
	}
	
	@Test
	void testMoveAround2() {
		/*
		 * Board:
		 *   0 1 2 3 4
		 * 0 E B _ _ _
		 * 1 _ B _ B _
		 * 2 _ B _ B _
		 * 3 _ _ _ B _
		 * 4 B B B B X
		 */
		strategist.setX(0);
		strategist.setY(0);
		board.addBoardEntity(new Boulder(1, 0));
		board.addBoardEntity(new Boulder(1, 1));
		board.addBoardEntity(new Boulder(1, 2));
		board.addBoardEntity(new Boulder(0, 4));
		board.addBoardEntity(new Boulder(1, 4));
		board.addBoardEntity(new Boulder(2, 4));
		board.addBoardEntity(new Boulder(3, 4));
		board.addBoardEntity(new Boulder(3, 3));
		board.addBoardEntity(new Boulder(3, 2));
		board.addBoardEntity(new Boulder(3, 1));
		
		for (int i = 0; i < 14; i++) {
			Direction dir = Dijkstra.getNextMove(board, strategist, 4, 4);
			System.out.println("(" + strategist.getX() + ", " + strategist.getY() + ") moving " + dir);
			switch (dir) {
			case DOWN:
				assertTrue(strategist.moveDown(board));
				break;
			case LEFT:
				assertTrue(strategist.moveLeft(board));
				break;
			case NONE:
				// don't move
				break;
			case RIGHT:
				assertTrue(strategist.moveRight(board));
				break;
			case UP:
				assertTrue(strategist.moveUp(board));
				break;
			default:
				// do nothing
				break;
			}
			// check that the strategist is the only entity on its square
			ArrayList<BoardEntity> entities = board.getEntitiesAt(strategist.getX(), strategist.getY());
			System.out.println("on the strategists tile: " + entities);
			assertEquals(entities.size(), 1);
		}
		System.out.println("final position: (" + strategist.getX() + ", " + strategist.getY() + ")");
		assertEquals(strategist.getX(), 4);
		assertEquals(strategist.getY(), 4);
	}
	
	@Test
	void testPathAroundFloorSwitch() {
		/*
		 *   0 1 2 3 4
		 * 0 E S _ _ _
		 * 1 _ B _ B _
		 * 2 _ B _ B _
		 * 3 _ _ _ B _
		 * 4 B B B B X
		 * test to make sure the strategist (E) will move over the floor switch (S) instead of taking the long route
		 */
		strategist.setX(0);
		strategist.setY(0);
		board.addBoardEntity(new FloorSwitch(1, 0));
		board.addBoardEntity(new Boulder(1, 1));
		board.addBoardEntity(new Boulder(1, 2));
		board.addBoardEntity(new Boulder(0, 4));
		board.addBoardEntity(new Boulder(1, 4));
		board.addBoardEntity(new Boulder(2, 4));
		board.addBoardEntity(new Boulder(3, 4));
		board.addBoardEntity(new Boulder(3, 3));
		board.addBoardEntity(new Boulder(3, 2));
		board.addBoardEntity(new Boulder(3, 1));

		Direction dir = Dijkstra.getNextMove(board, strategist, 4, 4);
		assertEquals(dir, Direction.RIGHT);
	}
}