package project.model.obstacles;

import java.util.ArrayList;

import project.model.Board;
import project.model.BoardEntity;
import project.model.Direction;
import project.model.Player;
import project.model.canMoveOntoDecorators.AllowArrow;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.collisionBehaviours.MoveAroundCollisionBehaviour;

public class Boulder extends BoardEntity {

	private static final long serialVersionUID = 1L;

	public Boulder(int x, int y) {
		super(x, y, "boulder");
		setCanMoveOnto(new AllowArrow(new AllowNone()));
		setCollisionBehaviour(new MoveAroundCollisionBehaviour());
	}

	// Overriding canMoveOnto from AllowPlayerToPush allows us
	// to use getX() and getY() without having to pass in extra parameters
	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		
		// if the mover entity isn't a player call the next canMoveOnto
		if (!mover.getEntityType().equals("player")) {
			return super.canMoveOnto(board, mover);
		}
		
		Direction boulderDirection = ((Player)mover).getDirection();
		int newX = -1;
		int newY = -1;
		
		// get the coordinates of the block in front of the boulder
		switch (boulderDirection) {
			case UP:
				newX = getX();
				newY = getY() - 1;
				break;
			case DOWN:
				newX = getX();
				newY = getY() + 1;
				break;
			case LEFT:
				newX = getX() - 1;
				newY = getY();
				break;
			case RIGHT:
				newX = getX() + 1;
				newY = getY();
				break;
			case NONE:
				return false;				
		}
		
		// get object that is on this coordinate
		ArrayList<BoardEntity> newSpaceEntities = board.getEntitiesAt(newX, newY);
		
		// if there's nothing there, then you can move onto it
		if(newSpaceEntities.size() == 0) {
			return true;
		}
		
		// check though each entity in the space to make sure all will let this boulder move onto them
		for (BoardEntity entity : newSpaceEntities) {
			if (entity.canMoveOnto(board, this) == false) {
				return false;
			}
		}
		
		return true;
	}
}
