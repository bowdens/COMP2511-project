package project.model;

import java.io.Serializable;

import project.model.canMoveOntoDecorators.CanMoveOnto;

public abstract class BoardEntity implements CanMoveOnto, Serializable {
	
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private CollisionBehaviour collisionBehaviour;
	private CanMoveOnto canMoveOnto;
	
	public BoardEntity(int x, int y) {
		setX(x);
		setY(y);
	}
	

	/**
	 * The update function is called once each turn after the player's movement.
	 * By default it does nothing (eg a wall does nothing) but an enemy might call 
	 * its move method or a bomb might explode
	 * @param board The board
	 */
	public void update(Board board) {
		// default implementation - do nothing
	}

	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public CollisionBehaviour getCollisionBehaviour() {
		return collisionBehaviour;
	}

	public void setCollisionBehaviour(CollisionBehaviour collisionBehaviour) {
		this.collisionBehaviour = collisionBehaviour;
	}
	
	/**
	 * Calls the collision behaviour
	 * @param board The board
	 * @param mover The BoardEntity moving onto this entity
	 */
	public void collide(Board board, BoardEntity mover) {
		collisionBehaviour.collide(board, mover, this);
	}
	
	/**
	 * Returns true if the a particular mover can move onto this
	 * @param board The board
	 * @param mover The BoardEntity attempting to move onto this
	 * @return true if it can, false if it cannot
	 */
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		return canMoveOnto.canMoveOnto(board, mover);
	}
	
	/**
	 * Set the canMoveOnto
	 * @param cmo the new CanMoveOnto
	 */
	public void setCanMoveOnto(CanMoveOnto cmo) {
		this.canMoveOnto = cmo;
	}
	
}
