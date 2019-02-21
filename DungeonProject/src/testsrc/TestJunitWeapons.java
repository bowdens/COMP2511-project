package testsrc;

import project.model.*;
import project.model.enemies.FlyingArrow;
import project.model.enemies.Hunter;
import project.model.items.Arrow;
import project.model.items.Sword;
import project.model.obstacles.Boulder;
import project.model.obstacles.Exit;
import project.model.obstacles.LitBomb;
import project.model.obstacles.Wall;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Ignore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;


public class TestJunitWeapons {
	
	private Board board;

	
	@BeforeEach
	public void setUp() {
		board = new Board("testboard", 5,5);
	}
	
	//tests UC1.9 & UC1.16
	@Test
	public void testSwordKill() {
		/*
		 *   0 1 2 3
		 * 0 P S _ _
		 * 1 _ E _ _
		 * 2 _ E _ _
		 * 3 _ _ _ _
		 */
		board = new Board("test", 5, 5);
		Player player = new Player(0, 0);
		board.addBoardEntity(player);
		Sword sword = new Sword(1, 0);
		board.addBoardEntity(sword);
		Enemy enemy1 = new Hunter(1, 1);
		board.addBoardEntity(enemy1);
		Enemy enemy2 = new Hunter(1, 2);
		board.addBoardEntity(enemy2);
		
		enemy1.moveUp(board);
		enemy1.moveDown(board);
		
		ArrayList<BoardEntity> entities = board.getEntitiesAt(1, 0);
		assertTrue(entities.contains(sword));
		
		player.moveRight(board);
		entities = board.getEntitiesAt(1, 0);
		System.out.println(entities);
		assertEquals(1, entities.size());
		assertTrue(entities.contains(player));
		assertFalse(entities.contains(sword));
		assertEquals(5, player.getSwords());
		
		player.moveDown(board);
		entities = board.getEntitiesAt(1, 1);
		assertEquals(1, entities.size());
		assertTrue(entities.contains(player));
		assertEquals(4, player.getSwords());
		
		enemy2.moveUp(board);
		entities = board.getEntitiesAt(1,1);
		System.out.println(entities);
		assertEquals(1, entities.size());
		assertTrue(entities.contains(player));
		assertEquals(3, player.getSwords());
	}
	
	//tests UC1.15
	@Test
	public void testArrowKill() {
		/*
		 *   0 1 2 3
		 * 0 P A _ E
		 * 1 _ _ _ _
		 * 2 _ W _ _
		 * 3 _ E _ _
		 */
		Board board = new Board("test", 4, 4);
		Player player = new Player(0, 0);
		board.addBoardEntity(player);
		Arrow arrow = new Arrow(1, 0);
		board.addBoardEntity(arrow);
		Enemy enemy1 = new Hunter(3, 0);
		board.addBoardEntity(enemy1);
		Enemy enemy2 = new Hunter(1, 3);
		board.addBoardEntity(enemy2);
		Wall wall = new Wall(1, 2);
		board.addBoardEntity(wall);
		
		assertTrue(player.moveRight(board));
		assertEquals(1, player.getArrows());
		
		ArrayList<BoardEntity> entities = board.getEntitiesAt(1, 0);
		assertTrue(entities.contains(player));
		assertFalse(entities.contains(arrow));
		
		player.shootArrow(board);
		entities = board.getEntitiesAt(2,  0);
		assertTrue(entities.get(0) instanceof FlyingArrow);
		FlyingArrow fArrow = (FlyingArrow) entities.get(0);
		fArrow.update(board);
		assertEquals(3, fArrow.getX());
		assertEquals(0, fArrow.getY());
		
		entities = board.getEntitiesAt(3,  0);
		assertEquals(0, entities.size());
		
		player.setDirection(Direction.DOWN);
		player.addArrows(1);
		player.shootArrow(board);
		entities = board.getEntitiesAt(1, 1);
		assertTrue(entities.get(0) instanceof FlyingArrow);
		fArrow = (FlyingArrow) entities.get(0);
		
		fArrow.update(board);
		assertEquals(1, fArrow.getX());
		assertEquals(2, fArrow.getY());

		entities = board.getEntitiesAt(1, 2);
		assertTrue(entities.contains(wall));
		assertEquals(1, entities.size());
		
		entities = board.getEntitiesAt(1, 3);
		assertTrue(entities.contains(enemy2));
	}
	
	//tests UC1.14
	@Test
	public void testBomb() {
		/*
		 *   0 1 2 3 4         0 1 2 3 4
		 * 0 _ _ _ _ _       0 _ _ _ _ _
		 * 1 _ E W B _       1 _ E _ B _ 
		 * 2 _ E B B _  -->  2 _ _ _ _ _ 
		 * 3 _ _ P _ _       3 _ _ _ _ _
		 * 4 _ _ _ _ _       4 _ _ _ _ _
		 */
		board = new Board("test", 5,5);
		Player player = new Player(2,3);
		LitBomb bomb = new LitBomb(2,2,3);
		Enemy enemy1 = new Hunter(1,1);
		Enemy enemy2 = new Hunter(1,2);
		Boulder boulder1 = new Boulder(3, 1);
		Boulder boulder2 = new Boulder(3, 2);
		Wall wall = new Wall(2, 1);
		board.addBoardEntity(player);
		board.addBoardEntity(bomb);
		board.addBoardEntity(enemy1);
		board.addBoardEntity(enemy2);
		board.addBoardEntity(boulder1);
		board.addBoardEntity(boulder2);
		board.addBoardEntity(wall);
		
		bomb.update(board);
		assertEquals(2, bomb.getTicks());
		bomb.update(board);
		assertEquals(1, bomb.getTicks());
		bomb.update(board);
		assertEquals(0, bomb.getTicks());
		
		ArrayList<BoardEntity> entities = board.getEntitiesAt(2,2);
		System.out.println(entities);
		assertEquals(0, entities.size());
		entities = board.getEntitiesAt(2,3);
		assertEquals(0, entities.size());
		entities = board.getEntitiesAt(1, 2);
		assertEquals(0, entities.size());
		entities = board.getEntitiesAt(2, 1);
		assertTrue(entities.contains(wall));
		entities = board.getEntitiesAt(1,1);
		assertTrue(entities.contains(enemy1));
	}
}
