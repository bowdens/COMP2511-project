package project.model;

import project.model.canMoveOntoDecorators.AllowNone;
import project.model.canMoveOntoDecorators.AllowPlayer;
import project.model.collisionBehaviours.CollideWithEnemy;

public abstract class Enemy extends MovingEntity {

	private static final long serialVersionUID = 6480653914019596180L;

	public Enemy(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new CollideWithEnemy());
		setCanMoveOnto(new AllowPlayer(new AllowNone()));
	}

}
