package testsrc;

import model.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class TestJunitSimpleDungeon {

   @Before
   public void setUp() {
	   Game game = new Game();
	   game.createNewBoard("testBoard1", 3, 3);
	   //place a player on the board
	   //place an exit on the board
   }
   
   @Test
   public void testSimpleDungeon() {	
	   //walk each direction, assert it's coordinates are correct
   }
   //tests US1.2
   @Test
   public void testWall() {
	 //walk into walls at the peremiter
   }
   //tests US1.3
   @Test
   public void testExit() {
	// walk into the exit - win the game
   }
}