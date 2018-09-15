package project.model.items;

import project.model.collisionBehaviours.PickUpArrows;

public class Arrow extends Weapon {

	private static final long serialVersionUID = 3046909360800506012L;

	public Arrow(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new PickUpArrows());
	}
}
