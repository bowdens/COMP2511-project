package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;

public class PickUpSword implements CollisionBehaviour {

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover instanceof Player) {
			((Player) mover).addSwords(5);
		} else {
			return;
		}
	}

}
