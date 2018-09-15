package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;

public class PickUpHoverPotion implements CollisionBehaviour {

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover instanceof Player) {
			((Player) mover).addHover(((Player)mover).potionSpan);
			board.removeBoardEntity(me);
		} else {
			return;
		}

	}

}
