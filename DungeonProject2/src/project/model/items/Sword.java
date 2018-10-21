package project.model.items;

import project.model.collisionBehaviours.PickUpSword;

public class Sword extends Item {

	private static final long serialVersionUID = 1L;

	public Sword(int x, int y) {
		super(x, y, "sword");
		setCollisionBehaviour(new PickUpSword());
	}

}
