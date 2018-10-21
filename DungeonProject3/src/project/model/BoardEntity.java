package project.model;

import java.io.Serializable;

import project.model.canMoveOntoDecorators.CanMoveOnto;

public abstract class BoardEntity implements CanMoveOnto, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int x;
	private int y;
	private String entityType;
	private CollisionBehaviour collisionBehaviour;
	private CanMoveOnto canMoveOnto;
	
	public BoardEntity(int x, int y, String entityType) {
		this.x = x;
		this.y = y;
		this.entityType = entityType;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the collisionBehaviour
	 */
	public CollisionBehaviour getCollisionBehaviour() {
		return collisionBehaviour;
	}

	/**
	 * @param collisionBehaviour the collisionBehaviour to set
	 */
	public void setCollisionBehaviour(CollisionBehaviour collisionBehaviour) {
		this.collisionBehaviour = collisionBehaviour;
	}

	/**
	 * @return the canMoveOnto
	 */
	public CanMoveOnto getCanMoveOnto() {
		return canMoveOnto;
	}

	/**
	 * @param canMoveOnto the canMoveOnto to set
	 */
	public void setCanMoveOnto(CanMoveOnto canMoveOnto) {
		this.canMoveOnto = canMoveOnto;
	}

	/**
	 * @param entityType the entityType to set
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	/**
	 * @return the entityType
	 */
	public String getEntityType() {
		return entityType;
	}

	/**
	 * This method calls the collision behaviour of this BoardEntity.
	 * @param board The board
	 * @param mover The BoardEntity moving onto this entity
	 */
	public void collide(Board board, BoardEntity mover) {
		collisionBehaviour.collide(board, mover, this);
	}
	
	/**
	 * This method returns true if the mover BoardEntity 
	 * can move onto this BoardEntity.
	 * @param board - the board
	 * @param mover - the BoardEntity attempting to move onto this BoardEntity
	 * @return true if it can and false otherwise
	 */
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		return canMoveOnto.canMoveOnto(board, mover);
	}
	
	/**
	 * This method is called on each BoardEntity after the player moves to update
	 * the state of board in ways specific to the effect of each entity. The default
	 * implementation of this method does nothing.
	 * @param board The board
	 */
	public void update(Board board) {
		
	}
	
	public boolean isEnemy() {
		return false;
	}
	
	public boolean isPlayer() {
		return false;	
	}
	
}
