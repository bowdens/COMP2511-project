package project.model;

import project.model.CollisionBehaviours.NoCollision;

public class FloorSwitch extends BoardEntity {
	public FloorSwitch(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new NoCollision());
	}

	@Override
	public boolean canMoveOnto(BoardEntity entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
