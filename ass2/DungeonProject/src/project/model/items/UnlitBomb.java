package project.model.items;

import project.model.collisionBehaviours.PickUpUnlitBomb;

public class UnlitBomb extends Item {

	private static final long serialVersionUID = 1L;

	public UnlitBomb(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new PickUpUnlitBomb());
	}

}
