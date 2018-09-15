package project.model;

import java.util.ArrayList;

import project.model.canMoveOntoDecorators.AllowAll;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.collisionBehaviours.NoCollision;
import project.model.obstacles.ExplodingBomb;

public class Player extends MovingEntity {
	
	private static final long serialVersionUID = -12691224577134383L;
	private int bombs;
	private int arrows;
	private int swords;
	private boolean hover;
	private boolean invincible;
	private ArrayList<Integer> keys;
	
	
	public Player(int x, int y) {
		super(x, y);
		bombs = 0;
		arrows = 0;
		swords = 0;
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
	
	/**
	 * If the player has a bomb, spawn an exploding bomb enity directly in front of the player
	 * Will only spawn a bomb if the entity allows an exploding bomb to move over them
	 * @author Tom Bowden
	 * @param board the board
	 * @return true if a bomb was dropped, false otherwise
	 */
	public boolean dropBomb(Board board) {
		if (getBombs() <= 0) {
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
			addBombs(-1);
			return true;
		}
		return false;
	}
	
	public void fireArrow() {
	
	}

	/**
	 * @return the bombs
	 */
	public int getBombs() {
		return bombs;
	}

	/**
	 * @param num the bombs to increment by
	 */
	public void addBombs(int num) {
		this.bombs += num;
	}

	/**
	 * @return the arrows
	 */
	public int getArrows() {
		return arrows;
	}

	/**
	 * @param num the arrows to increment by
	 */
	public void addArrows(int num) {
		this.arrows += num;
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
	 * @return the swords
	 */
	public int getSwords() {
		return swords;
	}

	/**
	 * @param num the swords to increment by
	 */
	public void addSwords(int num) {
		this.swords += num;
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
