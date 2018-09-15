package project.model.items;

import project.model.collisionBehaviours.PickUpSword;

public class Sword extends Weapon {

	private static final long serialVersionUID = -6665361744167017152L;

	public Sword(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new PickUpSword());
	}

}
