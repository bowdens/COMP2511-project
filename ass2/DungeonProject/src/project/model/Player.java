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
	
	@Override
	public void setDirection(Direction direction) {
		// override it to never let the player face in no direction
		if (direction == Direction.NONE) direction = Direction.DOWN;
		super.setDirection(direction);
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
		case NONE:
			newX = getX();
			newY = getY();
		default:
			// enum - should never happen
			break;
		}
		ArrayList<BoardEntity> entities = board.getEntitiesAt(newX, newY);
		BoardEntity bomb = new ExplodingBomb(newX, newY, 3);
		for (BoardEntity e : entities) {
			if (e.canMoveOnto(board, bomb) == false) {
				// we cannot place a bomb here
				return false;
			}
		}
		// all entities on the tile will let us put a bomb there
		board.addBoardEntity(bomb);
		addBombs(-1);
		return true;
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
