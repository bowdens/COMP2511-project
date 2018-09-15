package project.model;

import project.model.CanMoveOntoDecorators.AllowAll;
import project.model.CanMoveOntoDecorators.AllowNone;
import project.model.CollisionBehaviours.NoCollision;

public class FloorSwitch extends BoardEntity {
	private static final long serialVersionUID = 7418595553809873920L;

	public FloorSwitch(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new NoCollision());
		setCanMoveOnto(new AllowAll(new AllowNone()));
	}

}
