package testsrc;

import model.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class TestJunitPotions {
	@Before
	public void setUp() {
		Game game = new Game();
		game.createNewBoard("testBoard1", 3, 9);
		//place a player on the board
	}
	
	//tests UC1.17
	@Test
	public void testInvincibilityPotion() {
		//invincibility potion next to player
		//mows down a row of all types of enemies
		//it runs out and then player gets killed
	}
	
	//tests UC1.18
	@Test
	public void testHoverPotion() {
		//hover potion next to player
		//clears a row of pits until it wears out, then player falls in a pit
	}
}
