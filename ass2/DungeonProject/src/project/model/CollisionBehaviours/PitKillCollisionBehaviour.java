
package project.model.CollisionBehaviours;

import project.model.BoardEntity;

public class PitKillCollisionBehaviour implements CollisionBehaviour{
	
	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		//gameover if mover entity is a player
		if(entity instanceof Player) {
			board.killPlayer();
		}else {
			//remove object from board
			board.removeEntity(mover);
		}
		return;
	}
}
