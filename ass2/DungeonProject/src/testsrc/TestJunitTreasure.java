package testsrc;

import project.model.Game;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class TestJunitTreasure {
	@Before
	public void setUp() {
		Game game = new Game();
		game.createNewBoard("testBoard1", 5, 5);
		//place a player on the board
		//place treasure
	}
	
	//Tests US1.4
	@Test
	public void testCollectTreasure() {	
		//collect treasure
		//win game
	}
}
