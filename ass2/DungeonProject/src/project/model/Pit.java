//package project.model;
package model;

//import project.model.CollisionBehaviours.PitKillCollisionBehaviour;
import model.CollisionBehaviours.PitKillCollisionBehaviour;

public class Pit extends BoardEntity {
	
	public Pit(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new PitKillCollisionBehaviour());
	}

	@Override
	public boolean canMoveOnto(BoardEntity entity, Board board) {
		/*
		 * players can move onto the pit, others cannot
		 */
		if (entity instanceof Player) {
			return true;
		}
		return false;
	}

}
