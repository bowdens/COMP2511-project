//package project.model;
package model;

//import project.model.CollisionBehaviours.pickUpTreasure;
import model.CollisionBehaviours.pickUpTreasure;

public class Treasure extends BoardEntity {
	public Treasure(int x, int y) {
		super(x,y);
		setCollisionBehaviour(new pickUpTreasure());
	}
	@Override
	public boolean canMoveOnto(BoardEntity entity, Board board) {
		return (entity instanceof Player);
	}
}
