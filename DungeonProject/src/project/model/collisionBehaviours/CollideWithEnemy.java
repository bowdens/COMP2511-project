package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;

public class CollideWithEnemy implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover.isPlayer()) {
			Player player = (Player) mover;
			
			if(player.isInvincible()){
				board.removeBoardEntity(me);
				board.setEnemyCount(board.getEnemyCount() - 1);
			} else if(player.getSwords() > 0 ) {
				board.removeBoardEntity(me);
				((Player) mover).addSwords(-1);
				board.setEnemyCount(board.getEnemyCount() - 1);
			} else {
				board.setGameOver(true);
			}
		} else if (mover.getEntityType().equals("flying arrow")) {
			board.removeBoardEntity(me);
			board.removeBoardEntity(mover);
			board.setEnemyCount(board.getEnemyCount() - 1);
		}
	}

}
