package project.model;

import project.model.canMoveOntoDecorators.AllowNone;
import project.model.canMoveOntoDecorators.AllowPlayer;
import project.model.collisionBehaviours.CollideWithEnemy;
import project.model.movementBehaviours.RunAwayBehaviour;

public abstract class Enemy extends MovingEntity {

	private static final long serialVersionUID = 1L;
	private MovementBehaviour movementBehaviour;
	private MovementBehaviour runAway;
	
	public Enemy(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new CollideWithEnemy());
		setCanMoveOnto(new AllowPlayer(new AllowNone()));
		runAway = new RunAwayBehaviour();
	}
	
	@Override
	public void update(Board board) {
		/*
		 * Checks if the player is invincible
		 * if they are, runs away
		 * if not, goes according to their movement behaviour
		 */
		Player player = board.getPlayer();
		if (player == null) {
			// if the player isn't there, do nothing
			return;
		}
		MovementBehaviour behaviourToUse;
		
		if (player.isInvincible()) {
			behaviourToUse = runAway;
		} else {
			behaviourToUse = getMovementBehaviour();
		}
		
		Direction direction = behaviourToUse.nextDirection(board, this);
		switch(direction) {
			case DOWN:
				moveDown(board);
				break;
			case LEFT:
				moveLeft(board);
				break;
			case NONE:
				// don't do anything
				break;
			case RIGHT:
				moveRight(board);
				break;
			case UP:
				moveUp(board);
				break;
		}
	}
	
	public void setMovementBehaviour(MovementBehaviour movementBehaviour) {
		this.movementBehaviour = movementBehaviour;
	}
	
	public MovementBehaviour getMovementBehaviour() {
		return this.movementBehaviour;
	}

}
