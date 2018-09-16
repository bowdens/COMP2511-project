package project.model;

import java.io.Serializable;

public interface CollisionBehaviour extends Serializable {
	/**
	 * Figures out what to do when one board entity collides with another board entity
	 * @param board The board (used for deleting objects from the board, ending the game etc)
	 * @param mover The board entity that is colliding into the other board entity
	 * @param me The board entity itself
	 */
	public void collide(Board board, BoardEntity mover, BoardEntity me);
}
