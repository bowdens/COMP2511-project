package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Enemy;
import project.model.Player;

public class CollideWithPlayer implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	/**
	 * @pre me must be a player
	 */
	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		// if the mover is an enemy, kill the player unless they are invincible
		// cast me to a player ... if its not do nothing
		if (!(me instanceof Player)) {
			return;
		}
		
		Player player = (Player) me;
		if (mover instanceof Enemy) {
			if (player.isInvincible()) {
				// kill the mover (the enemy)
				board.removeBoardEntity(mover);
			} else if (player.getSwords() > 0) {
				board.removeBoardEntity(mover);
				player.addSwords(-1);
			} else {
				board.endGame();
			}
		}
	}

}
