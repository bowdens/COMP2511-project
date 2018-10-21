package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
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
		if (!me.isPlayer()) {
			return;
		}
		
		Player player = (Player) me;
		
		if (mover.isEnemy()) {
			if (player.isInvincible()) {
				board.removeBoardEntity(mover);
				board.setEnemyCount(board.getEnemyCount() - 1);
			} else if (player.getSwords() > 0) {
				board.removeBoardEntity(mover);
				player.addSwords(-1);
				board.setEnemyCount(board.getEnemyCount() - 1);
			} else {
				board.setGameOver(true);
			}
		}
	}

}
