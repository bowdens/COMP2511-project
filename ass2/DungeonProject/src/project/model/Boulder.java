package project.model;

import project.model.CollisionBehaviours.MoveAroundCollisionBehaviour;

public class Boulder extends BoardEntity {
	public Boulder(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new MoveAroundCollisionBehaviour());
	}

	@Override
	public boolean canMoveOnto(BoardEntity entity) {
		// players can move onto it, noone else
		if (entity instanceof Player) {
			return true;
		} else {
			return false;
		}
	}
}
