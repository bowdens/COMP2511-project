package project.model.obstacles;

import project.model.BoardEntity;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.collisionBehaviours.NoCollision;

public class Wall extends BoardEntity {
	
private static final long serialVersionUID = 1L;

	public Wall(int x, int y) {
		super(x, y);
		setCanMoveOnto(new AllowNone());
		setCollisionBehaviour(new NoCollision());
	}

}
