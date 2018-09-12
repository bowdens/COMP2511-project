package project.model.CollisionBehaviours;

import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;

public class NoCollision implements CollisionBehaviour {

	/**
	 * Does nothing when the player collides with it
	 */
	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		// do nothing
		return;
	}

}
