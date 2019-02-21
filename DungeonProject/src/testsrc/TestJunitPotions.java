package testsrc;

import project.model.*;
import project.model.items.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class TestJunitPotions {
	
	
	private Board board;
	private Player player;
	private InvincibilityPotion invinPotion;
	private HoverPotion hovPotion;
	
	@Before
	public void setUp() {
		Game game = new Game();
		game.createNewBoard("testBoard1", 3, 9);
		this.board = game.getCustomDungeonByName("testPotion");
		board.addBoardEntity(player);
		board.addBoardEntity(invinPotion);
		board.addBoardEntity(hovPotion);
		//place a player on the board
		this.player = new Player(2,2);
	}
	
	//tests UC1.17
	@Test
	public void testInvincibilityPotion() {
		//invincibility potion next to player
		this.invinPotion = new InvincibilityPotion(2,3);
		//moves down a row of all types of enemies
		
		//it runs out and then player gets killed
	}
	
	//tests UC1.18
	@Test
	public void testHoverPotion() {
		//hover potion next to player
		this.hovPotion = new HoverPotion(2,1);
		
		//clears a row of pits until it wears out, then player falls in a pit
	}
}
