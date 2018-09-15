package project.model.items;

import project.model.collisionBehaviours.PickUpInvincibilityPotion;

public class InvincibilityPotion extends Item {

	private static final long serialVersionUID = 1L;

	public InvincibilityPotion(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new PickUpInvincibilityPotion());
	}
}
