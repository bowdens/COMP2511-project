package project.model;

import project.model.canMoveOntoDecorators.AllowArrow;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.canMoveOntoDecorators.AllowPlayer;
import project.model.collisionBehaviours.CollideWithEnemy;
import project.model.movementBehaviours.RunAwayBehaviour;

public abstract class Enemy extends MovingEntity {

	private static final long serialVersionUID = 1L;
	
	public Enemy(int x, int y, String entityType) {
		super(x, y,  entityType);
		setCollisionBehaviour(new CollideWithEnemy());
		setCanMoveOnto(new AllowPlayer(new AllowArrow(new AllowNone())));
	}
	
	@Override
	public boolean isEnemy() {
		return true;
	}
	
	@Override
	public void update(Board board) {
		Player player = board.getPlayer();
		
		if (player.isInvincible()) {
			setMovementBehaviour(new RunAwayBehaviour());
		}
		
		Direction enemyDirection = getMovementBehaviour().nextDirection(board, this);
		
		switch(enemyDirection) {
			case UP:
				moveUp(board);
				break;
			case DOWN:
				moveDown(board);
				break;
			case LEFT:
				moveLeft(board);
				break;
			case RIGHT:
				moveRight(board);
				break;
			default:
				break;
		}
	}

}
