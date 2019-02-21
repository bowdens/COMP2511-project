package project.model.items;

import project.model.collisionBehaviours.PickUpTreasure;

public class Treasure extends Item {

	private static final long serialVersionUID = 1L;

	public Treasure(int x, int y) {
		super(x, y, "treasure");
		setCollisionBehaviour(new PickUpTreasure());
	}
}
