package project.model.collisionBehaviours;
import project.model.Player;
import project.model.enemies.FlyingArrow;
import project.model.Board;
import project.model.BoardEntity;
import project.model.obstacles.Boulder;
import project.model.CollisionBehaviour;

public class PitKillCollisionBehaviour implements CollisionBehaviour {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		/*
		 *  gameover if mover entity is a player
		 *  kills a boulder that falls in the pit
		 */
		
		if (mover instanceof FlyingArrow) {
			board.removeBoardEntity(mover);
		}

		if (mover instanceof Player) {
			board.endGame();
		} else if (mover instanceof Boulder) {
			//allow
			board.removeBoardEntity(mover);
		}
	}
}
