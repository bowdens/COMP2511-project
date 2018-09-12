package project.model;

import java.util.ArrayList;

import project.model.CollisionBehaviours.NoCollision;

public class Player extends MovingEntity {
	
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
	}
	
	public void moveUp(Board board) {
		int newX = getX();
		int newY = getY()-1;
		moveTo(board, newY, newX);
	}
	
	public void moveDown(Board board) {
		int newX = getX();
		int newY = getY()+1;
		moveTo(board, newY, newX);
	}
	
	public void moveLeft(Board board) {
		int newX = getX()-1;
		int newY = getY();
		moveTo(board, newY, newX);
	}
	
	public void moveRight(Board board) {
		int newX = getX()+1;
		int newY = getY();
		moveTo(board, newY, newX);
	}
	
	public void dropBomb() {
		
	}
	
	public void fireArrow() {
	
	}
	
	@Override
	public boolean canMoveOnto(MovingEntity entity) {
		// everything can move onto this
		return true;
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
