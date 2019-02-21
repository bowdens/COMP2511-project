package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;

public class NoCollision implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	/**
	 * Does nothing when the player collides with it
	 */
	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover.getEntityType().equals("flying arrow")) {
			board.removeBoardEntity(mover);
		}
	}

}
