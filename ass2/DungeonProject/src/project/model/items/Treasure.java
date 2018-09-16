package project.model.items;

import project.model.BoardEntity;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.canMoveOntoDecorators.AllowPlayer;
import project.model.collisionBehaviours.PickUpTreasure;

public class Treasure extends BoardEntity {

	private static final long serialVersionUID = 1L;

	public Treasure(int x, int y) {
		super(x,y);
		setCollisionBehaviour(new PickUpTreasure());
		setCanMoveOnto(new AllowPlayer(new AllowNone()));
	}
}
