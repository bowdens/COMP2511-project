package project.model;

import project.model.CollisionBehaviours.winLevelBehaviour;

public class Exit extends BoardEntity {
	public Exit(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new winLevelBehaviour());
	}
	@Override
	public boolean canMoveOnto(BoardEntity entity) {
		/*
		 * player can move onto it, nothing else
		 */
		if (entity instanceof Player) {
			return true;
		} else {
			return false;
		}
	}
}
