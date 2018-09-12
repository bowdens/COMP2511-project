package project.model;

public interface CollisionBehaviour {
	/**
	 * Figures out what to do when the player collides with an board entity
	 * @param player The player that is colliding into the board entity
	 * @param me The board entity itself
	 */
	public void collide(Player player, BoardEntity me);
}
