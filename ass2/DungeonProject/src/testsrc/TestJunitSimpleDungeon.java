package testsrc;

import project.model.*;
import project.model.obstacles.Exit;
import project.model.obstacles.Wall;

import org.junit.jupiter.api.Test;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class TestJunitSimpleDungeon {

	private Player player;
	private Board board;
	private Wall wall;
	private Exit exit;
	
   @BeforeEach
   public void setup() throws Exception {
	   Game game = new Game();
	   game.createNewBoard("testBoard1", 5, 5);
	   Board board = game.getCustomDungeonByName("testBoard1");
	   Player player = new Player(1,1);
	   Exit exit = new Exit(3,3);
	   Wall wall = new Wall(2,2);
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
	   //assert player wins?
   }
}
