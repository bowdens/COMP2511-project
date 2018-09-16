package project.model.items;

import project.model.collisionBehaviours.PickUpArrows;

public class Arrow extends Item {

	private static final long serialVersionUID = 1L;

	public Arrow(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new PickUpArrows());
	}
}
