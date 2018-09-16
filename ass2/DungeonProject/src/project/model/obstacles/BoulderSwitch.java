package project.model.obstacles;

import project.model.collisionBehaviours.CollideWithBoulderSwitch;

public class BoulderSwitch extends Boulder {

	/*
	 * extends a boulder, meaning that it inherits its canMoveOnto which doesn't use the decorators
	 */
	
	private static final long serialVersionUID = 8346495222931762844L;

	public BoulderSwitch(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new CollideWithBoulderSwitch());
	}

}
