package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;

public class PickUpTreasure implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover.isPlayer()) {
			board.removeBoardEntity(me);
			board.setTreasureCount(board.getTreasureCount() - 1);
		}
	}

}
