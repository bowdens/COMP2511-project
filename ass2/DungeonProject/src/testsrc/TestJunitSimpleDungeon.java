package testsrc;

import project.model.*;
import project.model.obstacles.Exit;
import project.model.obstacles.Wall;

//import org.junit.jupiter.api.Test;
//import org.junit.Ignore;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestJunitSimpleDungeon {

	private Player player;
	private Board board;
	private Wall wall;
	private Exit exit;
	
   @Before
   public void setUp() {
	   Game game = new Game();
	   game.createNewBoard("testBoard1", 5, 5);
	   this.board = game.getCustomDungeonByName("testBoard1");
	   this.player = new Player(1,1);
	   this.exit = new Exit(3,3);
	   this.wall = new Wall(2,2);
	   board.addBoardEntity(player);
	   board.addBoardEntity(exit);
	   board.addBoardEntity(wall);
	   
	   /*
		 * board looks like this:
		 *   1 2 3
		 * 1 P _ _
		 * 2 _ W _
		 * 3 _ _ E
		 */
   }
   
   
   //tests US1.3
   // - player can move in all directions
   @Test
   public void testWalking() {	
	   assertEquals(player.getX(),1);
	   assertEquals(player.getY(),1);
	   
	   player.moveDown(board);
	   assertEquals(player.getX(),1);
	   assertEquals(player.getY(),2);
	   
	   player.moveUp(board);
	   assertEquals(player.getX(),1);
	   assertEquals(player.getY(),1);
	   
	   player.moveRight(board);
	   assertEquals(player.getX(),2);
	   assertEquals(player.getY(),1);
	   
	   player.moveLeft(board);
	   assertEquals(player.getX(),1);
	   assertEquals(player.getY(),1);
   }
   //tests US1.2 
   // - player is blocked by walls
   @Test
   public void testWall() {
	   //attempt to move into a wall at the perimeter
	   player.moveUp(board);
	   assertEquals(player.getX(),1);
	   assertEquals(player.getY(),1);
	   
	   player.moveDown(board);
	   //attempt to move into a wall placed by the developer
	   player.moveRight(board);
	   assertEquals(player.getX(),1);
	   assertEquals(player.getY(),2);
   }
   //tests US1.1
   // - walk into the exit
   // - the game is won
   @Test
   public void testExit() {
	   player.moveDown(board);
	   player.moveDown(board);
	   player.moveRight(board);
	   player.moveRight(board);
	   assertEquals(player.getX(),3);
	   assertEquals(player.getY(),3);
	   //winGame() is called
	   assertEquals(board.getGameStatus(), 1);
   }
}
