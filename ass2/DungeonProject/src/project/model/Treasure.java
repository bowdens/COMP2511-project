package project.model;

import project.model.CollisionBehaviours.pickUpTreasure;

public class Treasure extends BoardEntity {
	public Treasure(int x, int y) {
		super(x,y);
		setCollisionBehaviour(new pickUpTreasure());
	}
	@Override
	public boolean canMoveOnto(BoardEntity entity) {
		return (entity instanceof Player);
	}
}
