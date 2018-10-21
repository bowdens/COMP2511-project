package project.model.enemies;

import project.model.Enemy;
import project.model.movementBehaviours.PredictBehaviour;

public class Strategist extends Enemy {
	
	private static final long serialVersionUID = 1L;

	public Strategist(int x, int y) {
		super(x, y, "strategist");
		setMovementBehaviour(new PredictBehaviour());
	}
}
