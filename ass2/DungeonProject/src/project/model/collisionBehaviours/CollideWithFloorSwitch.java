package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.obstacles.Boulder;
import project.model.obstacles.BoulderSwitch;

public class CollideWithFloorSwitch implements CollisionBehaviour {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4401555937628625153L;

	/**
	 * @pre assumes the entity being colided with (me) is a floor switch
	 */
	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		if (mover instanceof Boulder) {
			/*
			 * create a BoulderSwitch at the current position, 
			 * add it to the board, 
			 * remove the boulder & switch
			 */
			BoulderSwitch boulderSwitch = new BoulderSwitch(me.getX(), me.getY());
			board.addBoardEntity(boulderSwitch);
			board.removeBoardEntity(mover);
			board.removeBoardEntity(me);
		}
	}

}
