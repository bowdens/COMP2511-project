package project.model.enemies;

import project.model.Enemy;
import project.model.movementBehaviours.GoTowardsPlayer;

public class Hunter extends Enemy {

	private static final long serialVersionUID = 1L;

	public Hunter(int x, int y) {
		super(x, y);
		setMovementBehaviour(new GoTowardsPlayer());
	}

}
