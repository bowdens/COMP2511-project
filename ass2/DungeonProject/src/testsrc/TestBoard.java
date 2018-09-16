package testsrc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.model.Board;
import project.model.BoardEntity;
import project.model.obstacles.*;
import project.model.Player;
import project.model.enemies.Strategist;

public class TestBoard {
	private Board board;
	
	@BeforeEach
	void setup() {
		board = new Board("test board", 5, 5);
	}
	
	@Test
	void widthHeight() {
		assertEquals(board.getWidth(), 5);
		assertEquals(board.getHeight(), 5);
	}
	
	@Test
	void startsBlank() {
		ArrayList<BoardEntity> allEntities = board.getBoardEntities();
		assertEquals(allEntities.size(), 0);
		for (int x = 0; x < board.getWidth(); x++) {
			for (int y = 0; y < board.getHeight(); y++) {
				ArrayList<BoardEntity> entities = board.getEntitiesAt(x, y);
				assertEquals(entities.size(), 0);
			}
		}
	}
	
	@Test
	void addSingleEntity() {
		Player player = new Player(1, 1);
		assertTrue(board.addBoardEntity(player));
		assertFalse(board.addBoardEntity(player));
		ArrayList<BoardEntity> entities = board.getEntitiesAt(1, 1);
		assertEquals(entities.size(), 1);
		assertEquals(entities.get(0), player);
		ArrayList<BoardEntity> noEntities = board.getEntitiesAt(0, 0);
		assertEquals(noEntities.size(), 0);
	}
	
	@Test
	void addMultipleEntities() {
		Player player = new Player(1,1);
		Strategist strategist = new Strategist(1,1);
		FloorSwitch floorSwitch = new FloorSwitch(1,1);
		assertTrue(board.addBoardEntity(player));
		assertTrue(board.addBoardEntity(strategist));
		assertTrue(board.addBoardEntity(floorSwitch));
		
		ArrayList<BoardEntity> entities = board.getEntitiesAt(1, 1);
		assertEquals(entities.size(), 3);
		assertTrue(entities.contains(player));
		assertTrue(entities.contains(strategist));
		assertTrue(entities.contains(floorSwitch));
	}
	
	@Test
	void validX() {
		assertTrue(board.validX(1));
		assertFalse(board.validX(-1));
		assertFalse(board.validX(5));
	}
	
	@Test
	void validY() {
		assertTrue(board.validY(1));
		assertFalse(board.validY(-1));
		assertFalse(board.validY(5));
	}
	
	@Test
	void canMoveOnto() {
		Player player = new Player(1, 1);
		FloorSwitch floorSwitch = new FloorSwitch(1, 2);
		board.addBoardEntity(player);
		board.addBoardEntity(floorSwitch);
		// player can walk onto floor switch
		assertTrue(board.canMoveOnto(player, 1, 2));
		
		// player can move onto empty space
		assertTrue(board.canMoveOnto(player, 0, 0));
		
		// player can not move onto wall
		
		// add wall on top of floor switch
		Wall wall = new Wall(1, 2);
		board.addBoardEntity(wall);
		assertFalse(board.canMoveOnto(player, 1, 2));
		
	}
}
