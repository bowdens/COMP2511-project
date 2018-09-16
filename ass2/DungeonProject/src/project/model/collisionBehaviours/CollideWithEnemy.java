package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;
import project.model.enemies.FlyingArrow;


public class CollideWithEnemy implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover instanceof Player) {
			Player player = (Player) mover;
			if(player.isInvincible()){
				board.removeBoardEntity(me);
			} else if(player.getSwords() > 0 ) {
				board.removeBoardEntity(me);
				((Player) mover).addSwords(-1);
			} else {
				board.endGame();
			}
		} else if (mover instanceof FlyingArrow) {
			board.removeBoardEntity(me);
			board.removeBoardEntity(mover);
		} else {
			// do nothing - not an arrow, not a player
		}
		
	}

}
