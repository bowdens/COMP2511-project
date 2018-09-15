package project.model.CollisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;
import project.model.Boulder;

public class PitKillCollisionBehaviour implements CollisionBehaviour{
	
	private static final long serialVersionUID = 1L;

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		/*
		 *  gameover if mover entity is a player
		 *  kills a boulder that falls in the pit
		 */

		if (mover instanceof Player) {
			board.endGame();
		} else if (mover instanceof Boulder) {
			// allow
			board.removeBoardEntity(mover);
		}
		return;
	}
}
