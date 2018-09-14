package project.model;

import project.model.CanMoveOntoDecorators.AllowNone;
import project.model.CanMoveOntoDecorators.AllowPlayer;
import project.model.CollisionBehaviours.pickUpTreasure;

public class Treasure extends BoardEntity {
	private static final long serialVersionUID = 1608755959712296850L;

	public Treasure(int x, int y) {
		super(x,y);
		setCollisionBehaviour(new pickUpTreasure());
		setCanMoveOnto(new AllowPlayer(new AllowNone()));
	}
	
	
}
