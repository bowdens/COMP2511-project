package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;
import project.model.items.Key;

public class PickUpKey implements CollisionBehaviour {

	private static final long serialVersionUID = -4299818626971317574L;

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (! (mover instanceof Player)) {
			// do nothing
			return;
		}
		if (!(me instanceof Key)) {
			// do nothing - shouldn't happen
			return;
		}

		/*
		 * remove the key from the field
		 * add the key to the player
		 */
		
		board.removeBoardEntity(me);
		
		
		Player player = (Player) mover;
		Key key = (Key) me;
		
		player.addKey(key.getKeyID());
	
	}

}
