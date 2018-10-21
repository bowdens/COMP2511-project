package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;

public class PickUpHoverPotion implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {		
		if (mover.isPlayer()) {
			((Player) mover).startHovering();
			board.removeBoardEntity(me);
		}
	}

}
