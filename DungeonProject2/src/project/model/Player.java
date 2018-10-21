package project.model;

import java.util.ArrayList;

import project.model.canMoveOntoDecorators.AllowAll;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.collisionBehaviours.CollideWithPlayer;
import project.model.obstacles.LitBomb;

public class Player extends MovingEntity {
	
	private static final long serialVersionUID = 1L;
	
	private int bombs;
	private int arrows;
	private int swords;
	private boolean hover;
	private int invincibleTime;
	private ArrayList<Integer> keys;

	public Player(int x, int y) {
		super(x, y, "player");
		bombs = 0;
		arrows = 0;
		swords = 0;
		hover = false;
		invincibleTime = 0;
		keys = new ArrayList<Integer>();
		setCollisionBehaviour(new CollideWithPlayer());
		setDirection(Direction.DOWN);
		setCanMoveOnto(new AllowAll(new AllowNone()));
	}
	
	/**
	 * If the player has a bomb, spawn an exploding bomb entity directly in front of the player
	 * Will only spawn a bomb if the entity allows an exploding bomb to move over them
	 * @author Tom Bowden
	 * @param board the board
	 * @return true if a bomb was dropped, false otherwise
	 */
	public boolean dropBomb(Board board) {
		if (getBombs() <= 0) {
			return false;
		}
		
		int newX = dispatchX();
		int newY = dispatchY();
		BoardEntity bomb = new LitBomb(newX, newY, 3);
		ArrayList<BoardEntity> entities = board.getEntitiesAt(newX, newY);
		
		for (BoardEntity entity : entities) {
			if (entity.canMoveOnto(board, bomb) == false) {
				return false;
			}
		}
		
		board.addBoardEntity(bomb);
		addBombs(-1);
		return true;
	}
	
	public boolean fireArrow(Board board) {
		if (getArrows() <= 0) {
			return false;
		}
		
		int newX = dispatchX();
		int newY = dispatchY();
		FlyingArrow arrow = new FlyingArrow(newX, newY, getDirection()); 
		ArrayList<BoardEntity> entities = board.getEntitiesAt(newX, newY);
		
		for (BoardEntity entity : entities) {
			if (entity.canMoveOnto(board, arrow) == false) {
				return false;
			}
		}
		
		board.addBoardEntity(arrow);
		addArrows(-1);
		return true;
	}
	
	private int dispatchX() {
		int newX;
		
		switch(getDirection()) {
			case LEFT:
				newX = getX() - 1;
				break;
			case RIGHT:
				newX = getX() + 1;
				break;
			default:
				newX = getX();
		}
		
		return newX;
	}
	
	private int dispatchY() {
		int newY;
		
		switch(getDirection()) {
			case LEFT:
				newY = getY() - 1;
				break;
			case RIGHT:
				newY = getY() + 1;
				break;
			default:
				newY = getY();
		}
		
		return newY;
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
	public void startHovering() {
		this.hover = true;
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
	 * @return whether the player is invincible
	 */
	public boolean isInvincible() {
		return (invincibleTime > 0);
	}
	
	/*
	 * @return the invincible time remaining
	 */
	public int getInvincibleTime() {
		return this.invincibleTime;
	}

	/**
	 * @param invincible the invincible to add
	 */
	public void addInvincibleTime(int invincible) {
		this.invincibleTime += invincible;
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

	
	public void update(Board board) {
		//clock back if invincibility is in effect
		if(isInvincible()) {
			this.addInvincibleTime(-1);
		}
	}
	
	@Override
	public boolean isPlayer() {
		return true;
	}
}


