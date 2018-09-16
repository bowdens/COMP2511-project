package project.model;
import project.model.obstacles.*;
import project.model.items.*;

public class GamePlayer {
	
	private Board board;
	private Player player;
	private int numEnemyObjectives = -1;
	private int numTreasureObjectives = -1;
	private int numSwitchObjectives = -1;
	

	public GamePlayer(Board board) {
		this.board = board;
		this.player = board.getPlayer();
		//assumes that there is always either an exit or atleast one of the objective entities
		for(BoardEntity ent: board.getBoardEntities()) {
			if(ent instanceof Exit) {
				numEnemyObjectives = -1;
				numTreasureObjectives = -1;
				numSwitchObjectives = -1;
				break;
			}
			if(ent instanceof Enemy) { numEnemyObjectives++;}
			else if(ent instanceof Treasure) { numTreasureObjectives++;}
			//else if(ent instanceof Switch) { numSwitchObjectives++;}
		}
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public boolean shootArrow() {
		return player.shootArrow(board);
	}
	
	public void dropBomb() {
		if (!(player.dropBomb(board))) {
			System.out.println("Can't Drop Bomb!");
		}
	}

	public boolean movePlayer(String direction) {
		int prevX = player.getX();
		int prevY = player.getY();
		
		switch (direction) {
			case "Up":
				player.moveUp(board);
				break;
			case "Down":
				player.moveDown(board);
				break;
			case "Left":
				player.moveLeft(board);
				break;
			case "Right":
				player.moveRight(board);
				break;
		}
		// If the player has moved return true so that the turn can be played out
		if (player.getX() != prevX || player.getY() != prevY) {
			return true;
		}
		
		return false;
	}
	
	public int getNumEnemyObjectives() { return numEnemyObjectives;}
	public int getNumTreasureObjectives() { return numTreasureObjectives;}
	public int getNumSwitchObjectives() { return numSwitchObjectives;}
	
	
	public void newTurn() {
		//TODO
	}

}
