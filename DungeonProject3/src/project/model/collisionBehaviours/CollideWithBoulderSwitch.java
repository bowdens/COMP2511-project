package project.model.collisionBehaviours;

import java.util.ArrayList;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;
import project.model.obstacles.Boulder;
import project.model.obstacles.FloorSwitch;

public class CollideWithBoulderSwitch implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	/**
	 * @pre have already confirmed that the boulder will be able to spawn wherever it needs to
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
		
		Boulder newBoulder = new Boulder(newX, newY);
		board.addBoardEntity(newBoulder);
		
		ArrayList<BoardEntity> ents = board.getEntitiesAt(newX, newY);
		//if the boulder is moving onto a pit, call the pit's collide method to destroy the boulder
		for (BoardEntity entity : ents) {
			entity.collide(board, newBoulder);
			if (!board.getBoardEntities().contains(me)) {
				break;
			}
		}
		
		FloorSwitch floorSwitch = new FloorSwitch(me.getX(), me.getY());
		
		board.addBoardEntity(floorSwitch);
		board.removeBoardEntity(me);
		
		return;	
	}

}
