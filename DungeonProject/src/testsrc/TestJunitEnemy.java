package testsrc;

import project.model.*;
import project.model.enemies.*;
import project.model.obstacles.*;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJunitEnemy {
	/*
	private Game game;
	private GamePlayer gamePlayer;
	private Board board;
	private Player player;
	*/
	
	/*@Before
	public void setUp() {
		Game game = new Game();
		game.createNewBoard("testBoard1", 4, 9);
		this.board = game.getCustomDungeonByName("testBoard1");
		game.setCurrentBoard(board);
		board.addBoardEntity(player);`
		this.player = new Player(1,1);
	}*/
	
	//tests UC1.5
	//-the hunter will always travel along the shortest path to the player
	//-when the hunter collides with an unarmed player game over
	@Test
	public void testHuntsmanKillsPlayer() {
		Game game = new Game();
		game.createNewBoard("testBoard1", 6, 6);
		Board board = game.getCustomDungeonByName("testBoard1");
		game.setCurrentBoard(board);
		Player player = new Player(1,2);
		Hunter hunter = new Hunter(4,3);
		board.addBoardEntity(player);
		board.addBoardEntity(hunter);
		Wall w1 = new Wall(2,2);
		Wall w2 = new Wall(3,2);
		Wall w3 = new Wall(3,2);
		Wall w4 = new Wall(3,3);
		board.addBoardEntity(w1);
		board.addBoardEntity(w2);
		board.addBoardEntity(w3);
		board.addBoardEntity(w4);
		GamePlayer gamePlayer = new GamePlayer(board);
		game.setGamePlayer(gamePlayer);
		/*Board looks like:
		   1 2 3 4
		 1 _ _ _ _
		 2 P W W _
		 3 _ W W H
		 4 _ _ _ _
		 */
		//player doesn't move
		game.newTurn();
		assertEquals(player.getX(),1);
		assertEquals(player.getY(),2);
		assertEquals(hunter.getX(),4);
		assertEquals(hunter.getY(),2);
		/*Board looks like:
		   1 2 3 4
		 1 _ _ _ _
		 2 P W W H
		 3 _ W W _
		 4 _ _ _ _
		 */
		//if the player teleports to (3,4), the hunter will change it's path to the shortest path
		player.setX(3);
		player.setY(4);
		game.newTurn();
		assertEquals(player.getX(),3);
		assertEquals(player.getY(),4);
		assertEquals(hunter.getX(),4);
		assertEquals(hunter.getY(),3);
		/*Board looks like:
		   1 2 3 4
		 1 _ _ _ _
		 2 _ W W _
		 3 _ W W H
		 4 _ _ P _
		 */
		game.newTurn();
		assertEquals(hunter.getX(),4);
		assertEquals(hunter.getY(),4);
		game.newTurn();
		
		//the hunter should have now killed the player and gameStatus is set to 2 (endGame)
		assertEquals(board.getGameStatus(), 2);
		assertEquals(hunter.getX(),3);
		assertEquals(hunter.getY(),4);
		/*Board looks like:
		   1 2 3 4
		 1 _ _ _ _
		 2 _ W W _
		 3 _ W W _
		 4 _ _ H _
		 */
	}
	
	//tests UC1.6
	//- The hound must position itself between the hunter and the player
	//- If the player collides with the hound then the game is over
	@Test
	public void testHoundKillsPlayer() {
		Game game = new Game();
		game.createNewBoard("testBoard1", 5, 5);
		Board board = game.getCustomDungeonByName("testBoard1");
		game.setCurrentBoard(board);
		Player player = new Player(1,1);
		Hunter hunter = new Hunter(3,1);
		Hound hound = new Hound(2,1);
		board.addBoardEntity(player);
		board.addBoardEntity(hunter);
		board.addBoardEntity(hound);
		GamePlayer gamePlayer = new GamePlayer(board);
		game.setGamePlayer(gamePlayer);
		/*Board looks like:
		   1 2 3 
		 1 P h H 
		 2 _ _ _ 
		 3 _ _ _ 
		 */
		game.newTurn();
		//hound doesn't move since it's objective is to get between the hunter and the player
		assertEquals(hound.getX(),2);
		assertEquals(hound.getY(),1);
		assertEquals(hunter.getX(),3);
		assertEquals(hunter.getY(),2);
		/*Board looks like:
		   1 2 3 
		 1 P h _ 
		 2 _ _ H 
		 3 _ _ _ 
		 */
		game.newTurn();
		
		//in an effort to get between the hunter and the player, the hound kills the player
		assertEquals(board.getGameStatus(), 2);
		
		assertEquals(hound.getX(),1);
		assertEquals(hound.getY(),1);
		assertEquals(hunter.getX(),2);
		assertEquals(hunter.getY(),2);
		/*Board looks like:
		   1 2 3 
		 1 h _ _ 
		 2 _ H _ 
		 3 _ _ _ 
		 */
	}
	
	//tests UC1.7
	@Test
	public void testStrategistKillsPlayer() {
		Game game = new Game();
		game.createNewBoard("testBoard1", 6, 6);
		Board board = game.getCustomDungeonByName("testBoard1");
		game.setCurrentBoard(board);
		Player player = new Player(1,1);
		Strategist strategist = new Strategist(4,2);
		board.addBoardEntity(player);
		board.addBoardEntity(strategist);
		GamePlayer gamePlayer = new GamePlayer(board);
		game.setGamePlayer(gamePlayer);
		
		gamePlayer.movePlayer("Right");
		gamePlayer.newTurn();
		//System.out.println("Player @ ("+player.getX()+","+player.getY()+")\n");
		//System.out.println("Strategist @ ("+strategist.getX()+","+strategist.getY()+")\n");
		gamePlayer.movePlayer("Right");
		gamePlayer.newTurn();
		//System.out.println("Player @ ("+player.getX()+","+player.getY()+")\n");
		//System.out.println("Strategist @ ("+strategist.getX()+","+strategist.getY()+")\n");
		gamePlayer.movePlayer("Right");
		gamePlayer.newTurn();
		//System.out.println("Player @ ("+player.getX()+","+player.getY()+")\n");
		//System.out.println("Strategist @ ("+strategist.getX()+","+strategist.getY()+")\n");
		
	}
	
	//tests UC1.8
	@Test
	public void testCowardKillsPlayer() {
		Game game = new Game();
		game.createNewBoard("testBoard1",5, 3);
		Board board = game.getCustomDungeonByName("testBoard1");
		game.setCurrentBoard(board);
		Player player = new Player(1,1);
		Coward coward = new Coward(4,1);
		board.addBoardEntity(player);
		board.addBoardEntity(coward);
		GamePlayer gamePlayer = new GamePlayer(board);
		game.setGamePlayer(gamePlayer);
		
		gamePlayer.movePlayer("Right");
		gamePlayer.newTurn();
		System.out.println("Player @ ("+player.getX()+","+player.getY()+")\n");
		System.out.println("Coward @ ("+coward.getX()+","+coward.getY()+")\n");
		gamePlayer.movePlayer("Right");
		gamePlayer.newTurn();
		System.out.println("Player @ ("+player.getX()+","+player.getY()+")\n");
		System.out.println("Coward @ ("+coward.getX()+","+coward.getY()+")\n");
		gamePlayer.movePlayer("Right");
		gamePlayer.newTurn();
		System.out.println("Player @ ("+player.getX()+","+player.getY()+")\n");
		System.out.println("Coward @ ("+coward.getX()+","+coward.getY()+")\n");
		gamePlayer.newTurn();
		System.out.println("Player @ ("+player.getX()+","+player.getY()+")\n");
		System.out.println("Coward @ ("+coward.getX()+","+coward.getY()+")\n");
		gamePlayer.newTurn();
		System.out.println("Player @ ("+player.getX()+","+player.getY()+")\n");
		System.out.println("Coward @ ("+coward.getX()+","+coward.getY()+")\n");
		gamePlayer.newTurn();
		System.out.println("Player @ ("+player.getX()+","+player.getY()+")\n");
		System.out.println("Coward @ ("+coward.getX()+","+coward.getY()+")\n");
		gamePlayer.newTurn();
		System.out.println("Player @ ("+player.getX()+","+player.getY()+")\n");
		System.out.println("Coward @ ("+coward.getX()+","+coward.getY()+")\n");
		gamePlayer.newTurn();
		System.out.println("Player @ ("+player.getX()+","+player.getY()+")\n");
		System.out.println("Coward @ ("+coward.getX()+","+coward.getY()+")\n");
		
	}
}
