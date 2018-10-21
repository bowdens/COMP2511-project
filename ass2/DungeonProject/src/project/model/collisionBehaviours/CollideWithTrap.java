package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Enemy;
import project.model.Player;
import project.model.enemies.FlyingArrow;


public class CollideWithTrap implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		/* Allow the enemy and the player to move onto me
		 * Kill them if they do, then kill me
		 */
	
		if (mover instanceof Player) {
			Player player = (Player) mover;
			if (player.isInvincible()){
				board.removeBoardEntity(me);
			} else if (player.isHover()) {
				// if the player is hovering, don't kill them
				// do nothing, stay armed
			} else {
				// if the player is not invincible, kill them
				board.endGame();
			}
		} else if (mover instanceof Enemy) {
			// kill the enemy and myself
			board.removeBoardEntity(me);
			board.removeBoardEntity(mover);
		} else {
			// do nothing - not an arrow, not a player
		}
		
	}

}
