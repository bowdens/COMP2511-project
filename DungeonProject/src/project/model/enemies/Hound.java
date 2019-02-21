package project.model.enemies;

import project.model.Enemy;
import project.model.movementBehaviours.MoveBetweenBehaviour;

public class Hound extends Enemy {

	private static final long serialVersionUID = 1L;

	public Hound(int x, int y) {
		super(x, y, "hound");
		setMovementBehaviour(new MoveBetweenBehaviour());
	}

}
