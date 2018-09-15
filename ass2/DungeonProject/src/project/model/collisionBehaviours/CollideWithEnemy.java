package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;


public class CollideWithEnemy implements CollisionBehaviour {

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if(((Player) mover).isInvincible()){
			board.removeBoardEntity(me);
		}else if(((Player) mover).getSwords() > 0 ) {
			board.removeBoardEntity(me);
			((Player) mover).addSwords(-1);
		}else {
			board.endGame();
		}
		
	}

}
