package project.model.items;

import project.model.collisionBehaviours.PickUpHoverPotion;

public class HoverPotion extends Item {

	private static final long serialVersionUID = 1L;

	public HoverPotion(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new PickUpHoverPotion());
	}
}
