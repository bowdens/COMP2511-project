//package project.model;
package model;

//import project.model.CollisionBehaviours.NoCollision;
import model.CollisionBehaviours.NoCollision;

public class FloorSwitch extends BoardEntity {
	public FloorSwitch(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new NoCollision());
	}

	@Override
	public boolean canMoveOnto(BoardEntity entity, Board board) {
		// TODO Auto-generated method stub
		return false;
	}

}
