//package project.model;
package model;

import java.util.ArrayList;

//import project.model.CanMoveOntoDecorators.AllowAll;
import model.CanMoveOntoDecorators.AllowAll;
//import project.model.CanMoveOntoDecorators.AllowNone;
import model.CanMoveOntoDecorators.AllowNone;
//import project.model.CollisionBehaviours.NoCollision;
import model.CollisionBehaviours.NoCollision;
import model.Inventory;

public class Player extends MovingEntity {
	
	private static final long serialVersionUID = -12691224577134383L;
	private Inventory inventory;
	private boolean hover;
	private boolean invincible;
	private ArrayList<Integer> keys;
	
	
	public Player(int x, int y) {
		super(x, y);
		inventory = new Inventory();
		setHover(false);
		setInvincible(false);
		setCollisionBehaviour(new NoCollision());
		setDirection(Direction.DOWN);
		setCanMoveOnto(new AllowAll(new AllowNone()));
	}
	
	/**
	 * Tries to move the player up (decrement Y)
	 * @param board The board
	 * @author Tom Bowden
	 */
	public void moveUp(Board board) {
		int newX = getX();
		int newY = getY()-1;
		moveTo(board, newY, newX);
	}
	
	/**
	 * Tries to move the player down (increment Y)
	 * @param board The board
	 * @author Tom Bowden
	 */
	public void moveDown(Board board) {
		int newX = getX();
		int newY = getY()+1;
		moveTo(board, newY, newX);
	}
	
	/**
	 * Tries to move the player left (decrement X)
	 * @param board The board
	 * @author Tom Bowden
	 */
	public void moveLeft(Board board) {
		int newX = getX()-1;
		int newY = getY();
		moveTo(board, newY, newX);
	}
	
	/**
	 * Tries to move the player right (increment X)
	 * @param board The board
	 * @author Tom Bowden
	 */
	public void moveRight(Board board) {
		int newX = getX()+1;
		int newY = getY();
		moveTo(board, newY, newX);
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	/**
	 * If the player has a bomb, spawn an exploding bomb enity directly in front of the player
	 * Will only spawn a bomb if the entity allows an exploding bomb to move over them
	 * @author Tom Bowden
	 * @param board the board
	 * @return true if a bomb was dropped, false otherwise
	 */
	public boolean dropBomb(Board board) {
		if (inventory.getNumBombs() <= 0) {
			return false;
		}
		
		int newX = 0, newY = 0;
		switch (getDirection()) {
		case UP:
			newX = getX();
			newY = getY() - 1;
			break;
		case DOWN:
			newX = getX();
			newY = getY() + 1;
			break;
		case LEFT:
			newX = getX() - 1;
			newY = getY();
			break;
		case RIGHT:
			newX = getX() + 1;
			newY = getY();
			break;
		default:
			// enum - should never happen
			break;
		}
		BoardEntity entity = board.getEntityAt(newX, newY);
		BoardEntity bomb = new ExplodingBomb(newX, newY, 3);
		if (entity == null || entity.canMoveOnto(board, bomb)) {
			// put the bomb there
			board.addBoardEntity(bomb);
			inventory.removeBomb();
			return true;
		}
		return false;
	}
	
	public void fireArrow() {
	
	}


	/**
	 * @return the hover
	 */
	public boolean isHover() {
		return hover;
	}

	/**
	 * @param hover the hover to set
	 */
	public void setHover(boolean hover) {
		this.hover = hover;
	}


	/**
	 * @return the invincible
	 */
	public boolean isInvincible() {
		return invincible;
	}

	/**
	 * @param invincible the invincible to set
	 */
	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	/**
	 * @return the keys
	 */
	public ArrayList<Integer> getKeys() {
		return keys;
	}

	/**
	 * @param keyID the key to add
	 */
	public void addKey(int keyID) {
		this.keys.add(keyID);
	}
	
	/**
	 * @param keyID the ID of the key
	 * @return True if the key exists, false otherwise
	 */
	public boolean hasKey(int keyID) {
		return keys.contains(keyID);
	}

}
