package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;
import project.model.enemies.FlyingArrow;

public class PickUpTreasure implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover instanceof FlyingArrow) {
			board.removeBoardEntity(mover);
		}
		
		if (mover instanceof Player) {
			board.removeBoardEntity(me);
		}
	}

}
