package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;

public class PickUpHoverPotion implements CollisionBehaviour {

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover instanceof Player) {
			//if the player is already hovering, then extend the hover time
			if(((Player) mover).isHover()){
				((Player) mover).addHover(((Player)mover).potionSpan);
			}else {
				((Player) mover).setHover(true);
			}
			board.removeBoardEntity(me);
		} else {
			return;
		}

	}

}
