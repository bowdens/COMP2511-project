package testsrc;

import project.model.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;


public class TestJunitWeapons {
	@Before
	public void setUp() {
		Game game = new Game();
		game.createNewBoard("testBoard1", 4, 9);
		//place a player on the board
	}
	
	//tests UC1.9 & UC1.16
	@Test
	public void testArrowKill() {
		//one arrow next to player
		//huntsman at other end of dungeon
		//player kills huntsman and wins level
	}
	
	//tests UC1.15
	@Test
	public void testSwordKill() {
		//one sword next to player
		//6 different kinds of enemies
		//kills 5 of the 6, 6th one kills the player
	}
	
	//tests UC1.14
	@Test
	public void testBomb() {
		//maybe make a very small map with a coward and a boulder
		//check if everything blows up except the player
	}
}
