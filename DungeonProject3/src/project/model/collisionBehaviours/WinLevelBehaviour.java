package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;

public class WinLevelBehaviour implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover.getEntityType().equals("flying arrow")) {
			board.removeBoardEntity(mover);
		} else {
			board.setExitReached(true);
		}
	}

}
