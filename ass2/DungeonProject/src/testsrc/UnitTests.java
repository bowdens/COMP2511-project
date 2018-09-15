package testsrc;

import project.model.*;
import project.controller.*;

//import static junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import junit.framework.*;

public class UnitTests {
	
	//for each class you have 
	protected void setUp(){
		//which sets up the game
	}
	
	@Test
	public final void playerWalksIntoWall() {
		
	}
	
	
	public static void main(String[] args) {
		
	}
}

//TestJunitTreasure -- 1 test: collect treasure
//TestJunitWeapons -- test1: pick up sword, kill 5 enemies -- test2: pick up arrow, kill enemy
//TestJunitHuntsman -- test1: huntsman kills player -- test2: hound gets between? -- test3: kill huntsman with sword -- test4: kill hound with sword

//US1.19 - test that the player respawns when they die
//US1.18 - test that the player can hover over pits when they consume the potion
//US1.17 - test that the player doesn't die/kills the enemies when they consume an invincibility potion
//US1.16 - pick up arrows, check if in inventory, kill enemy with arrow
//US1.15 - pick up a sword, check if in inventory, kill 5 enemies
//US1.14 - pick up a bomb, check if in inventory, blow up an enemy, a boulder and yourself
//US1.13 & 12 - push a boulder, push into a wall, push onto a switch, win the level (push off switch?)
//US1.11 - push a boulder down a pit, jump down the pit
//US1.10 - 2 keys, 2 doors - pick up and open
//US1.9 - one enemy - one sword - kill the enemy win
//US1.4 - collect treasure, win the level
//--US1.3 - walk around, bump into walls
//--US1.2 - walk to an exit