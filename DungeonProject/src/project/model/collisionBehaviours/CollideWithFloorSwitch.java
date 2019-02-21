package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.obstacles.FloorSwitch;

public class CollideWithFloorSwitch implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	/**
	 * @pre assumes the entity being collided with (me) is a floor switch
	 */
	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover.getEntityType().equals("boulder")) {
			((FloorSwitch) me).setSwitchActivated(true);
			board.setSwitchCount(board.getSwitchCount() - 1);
		}
	}

}
