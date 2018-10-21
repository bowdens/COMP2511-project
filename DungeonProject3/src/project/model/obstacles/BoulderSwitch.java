package project.model.obstacles;

import project.model.collisionBehaviours.CollideWithBoulderSwitch;

public class BoulderSwitch extends Boulder {

	private static final long serialVersionUID = 1L;

	/*
	 * extends a boulder, meaning that it inherits its canMoveOnto which doesn't use the decorators
	 */

	public BoulderSwitch(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new CollideWithBoulderSwitch());
	}

}
