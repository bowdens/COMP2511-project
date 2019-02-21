package testsrc;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.model.Board;
import project.model.obstacles.*;
import project.model.Direction;
import project.model.Player;
import project.model.enemies.Strategist;

public class TestMovement {
	private Board board;
	
	@BeforeEach
	void setup() {
		board = new Board("test board", 5, 5);
	}
	
	@Test
	void testSetX() {
		Player player = new Player(0,0);
		player.setX(2);
		assertEquals(player.getX(), 2);
	}
	
	@Test
	void testSetY() {
		Player player = new Player(0,0);
		player.setY(2);
		assertEquals(player.getY(), 2);
	}
	
	@Test
	void testMoveToEmptySquare() {
		Player player = new Player(0,0);
		assertTrue(player.moveTo(board, 1, 1));
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);
		
	}
	
	@Test
	void testPlayerMoveDown() {
		Player player = new Player(0,0);
		assertTrue(player.moveDown(board));
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);
	}
}
