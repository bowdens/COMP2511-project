package project.model;

public interface CollisionBehaviour {
	/**
	 * Figures out what to do when the player collides with an board entity
	 * @param player The player that is colliding into the board entity
	 * @param me The board entity itself
	 */
	//possibly just ue two boardEntities
	public void collide(Board board, BoardEntity mover, BoardEntity me);
}
