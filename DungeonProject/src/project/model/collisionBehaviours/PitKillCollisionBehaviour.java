package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;

public class PitKillCollisionBehaviour implements CollisionBehaviour {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover.isPlayer()) {
			board.removeBoardEntity(mover);
			board.setGameOver(true);
		} else if (mover.getEntityType().equals("boulder")) {
			board.removeBoardEntity(mover);
		}
	}
}
