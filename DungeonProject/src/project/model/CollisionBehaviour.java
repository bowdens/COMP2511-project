package project.model;

import java.io.Serializable;

public interface CollisionBehaviour extends Serializable {
	/**
	 * This method figures out what to do when one BoardEntity 
	 * collides with another BoardEntity.
	 * @param board - the board
	 * @param mover - the board entity that is colliding into the other board entity
	 * @param me - the board entity being collided with
	 */
	public void collide(Board board, BoardEntity mover, BoardEntity me);
}
