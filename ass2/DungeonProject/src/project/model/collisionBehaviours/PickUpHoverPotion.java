package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;

public class PickUpHoverPotion implements CollisionBehaviour {

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover instanceof Player) {
			//if the player isn't already hovering, activate this effect
			if(!((Player) mover).isHover()){
				((Player) mover).setHover(true);
			}
			board.removeBoardEntity(me);
			return;
		} else {
			return;
		}

	}

}
