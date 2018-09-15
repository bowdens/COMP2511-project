package testsrc;

import project.model.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class TestJunitObstacles {
	@Before
	public void setUp() {
		Game game = new Game();
		game.createNewBoard("testBoard1", 4, 9);
		//place a player on the board
	}
	
	@Test
	public void testDoorAndKey() {
		//player attempt to access door without key - doesn't work
		//player gets key and opens door
		//repeat again with another door
	}
	
	//tests US1.11 AND US1.19
	@Test
	public void testPitFall() {
		//player falls down a pit
		//player respawns 
	}
	
	//test US1.13
	@Test
	public void testBoulderSwitch() {
		//push boulder onto switch
		//win level
	}
	
	@Test
	public void testBoulderPit() {
		//push boulder downt the pit
		//jump down the pit
	}
	
	
}
