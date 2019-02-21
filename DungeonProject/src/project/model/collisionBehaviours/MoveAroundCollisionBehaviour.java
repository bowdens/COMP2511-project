package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;

import java.util.ArrayList;

public class MoveAroundCollisionBehaviour implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	/**
	 * @pre Have checked that me.canMoveOnto is true for me=Boudler and mover=player
	 */
	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover.getEntityType().equals("flying arrow")) {
			board.removeBoardEntity(mover);
			return;
		} else if (!mover.isPlayer()) {
			return;
		}
			
		Player player = (Player) mover;
		int newX = 0, newY = 0;
		
		switch (player.getDirection()){
			case UP:
				newX = me.getX();
				newY = me.getY() - 1;
				break;
			case DOWN:
				newX = me.getX();
				newY = me.getY() + 1;
				break;
			case LEFT:
				newX = me.getX() - 1;
				newY = me.getY();
				break;
			case RIGHT:
				newX = me.getX() + 1;
				newY = me.getY();
				break;
			case NONE:
				break;
			default:
				break;
		}
		
		ArrayList<BoardEntity> entities = board.getEntitiesAt(newX, newY);
		
		me.setX(newX);
		me.setY(newY);
		
		// if the boulder falls into a pit its removed within the collide method
		for (BoardEntity entity : entities) {
			entity.collide(board, me);
			
			// if the boulder has been removed then break
			if (!board.getBoardEntities().contains(me)) {
				break;
			}
		}		
	}
	
}
