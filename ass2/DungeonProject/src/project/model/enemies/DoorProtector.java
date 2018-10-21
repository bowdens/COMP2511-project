package project.model.enemies;

import project.model.Enemy;
import project.model.movementBehaviours.GoTowardsDoor;

public class DoorProtector extends Enemy {

	private static final long serialVersionUID = 1L;

	public DoorProtector(int x, int y) {
		super(x, y);
		setMovementBehaviour(new GoTowardsDoor());
	}

}
