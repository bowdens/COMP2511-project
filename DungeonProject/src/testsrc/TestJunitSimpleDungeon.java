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

	private Game game;
	private GamePlayer gamePlayer;
	private Player player;
	private Board board;
	private Wall wall;
	private Exit exit;
	
	
   @Before
   public void setUp() {
	   this.game = new Game();
	   game.createNewBoard("testBoard1", 5, 5);
	   this.board = game.getCustomDungeonByName("testBoard1");
	   game.setCurrentBoard(board);
	   this.player = new Player(1,1);
	   this.exit = new Exit(3,3);
	   this.wall = new Wall(2,2);
	   board.addBoardEntity(player);
	   board.addBoardEntity(exit);
	   board.addBoardEntity(wall);
	   this.gamePlayer = new GamePlayer(board);
	   game.setGamePlayer(gamePlayer);
	   
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
	   
	   //player.moveDown(board);
	   game.movePlayer("Down");
	   game.newTurn();
	   assertEquals(player.getX(),1);
	   assertEquals(player.getY(),2);
	   
	   //player.moveUp(board);
	   game.movePlayer("Up");
	   game.newTurn();
	   assertEquals(player.getX(),1);
	   assertEquals(player.getY(),1);
	   
	   //player.moveRight(board);
	   game.movePlayer("Right");
	   game.newTurn();
	   assertEquals(player.getX(),2);
	   assertEquals(player.getY(),1);
	   
	   //player.moveLeft(board);
	   game.movePlayer("Left");
	   game.newTurn();
	   assertEquals(player.getX(),1);
	   assertEquals(player.getY(),1);
   }
   //tests US1.2 
   // - player is blocked by walls
   @Test
   public void testWall() {
	   //attempt to move into a wall at the perimeter
	   game.movePlayer("Up");
	   game.newTurn();
	   assertEquals(player.getX(),1);
	   assertEquals(player.getY(),1);
	   
	   game.movePlayer("Down");
	   game.newTurn();
	   //attempt to move into a wall placed by the developer
	   game.movePlayer("Right");
	   game.newTurn();
	   assertEquals(player.getX(),1);
	   assertEquals(player.getY(),2);
   }
   //tests US1.1
   // - walk into the exit
   // - the game is won
   @Test
   public void testExit() {
	   game.movePlayer("Down");
	   game.newTurn();
	   game.movePlayer("Down");
	   game.newTurn();
	   game.movePlayer("Right");
	   game.newTurn();
	   game.movePlayer("Right");
	   assertEquals(player.getX(),3);
	   assertEquals(player.getY(),3);
	   
	   game.newTurn();
	   //winGame() should be called this time, setting gameStatus to 1
	   assertEquals(board.getGameStatus(), 1);
   }
}
