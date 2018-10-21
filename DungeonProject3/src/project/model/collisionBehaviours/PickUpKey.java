package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;
import project.model.items.Key;

public class PickUpKey implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover.isPlayer()) {
			Player player = (Player) mover;
			Key key = (Key) me;
			
			board.removeBoardEntity(me);
			player.addKey(key.getKeyID());
		}
	}

}
