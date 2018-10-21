package project.model.obstacles;

import project.model.BoardEntity;
import project.model.canMoveOntoDecorators.AllowEnemy;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.canMoveOntoDecorators.AllowPlayer;
import project.model.collisionBehaviours.CollideWithTrap;

public class Trap extends BoardEntity {

	private static final long serialVersionUID = 7304301382530745468L;

	public Trap(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new CollideWithTrap());
		setCanMoveOnto(new AllowPlayer(new AllowEnemy(new AllowNone())));
		
	}

}
