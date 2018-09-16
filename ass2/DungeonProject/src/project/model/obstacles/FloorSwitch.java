package project.model.obstacles;

import project.model.BoardEntity;
import project.model.canMoveOntoDecorators.AllowAll;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.collisionBehaviours.CollideWithFloorSwitch;
public class FloorSwitch extends BoardEntity {
	
	private static final long serialVersionUID = 1L;

	public FloorSwitch(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new CollideWithFloorSwitch());
		setCanMoveOnto(new AllowAll(new AllowNone()));
	}

}
