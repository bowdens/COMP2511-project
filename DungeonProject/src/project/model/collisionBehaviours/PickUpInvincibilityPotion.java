package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;

public class PickUpInvincibilityPotion implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover.isPlayer()) {
			((Player) mover).addInvincibleTime(10);
			board.removeBoardEntity(me);
		}
	}

}
