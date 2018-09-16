package project.model.obstacles;

import java.util.ArrayList;

import project.model.Board;
import project.model.BoardEntity;
import project.model.Direction;
import project.model.Player;
import project.model.collisionBehaviours.MoveAroundCollisionBehaviour;

public class Boulder extends BoardEntity {

	private static final long serialVersionUID = 1L;

	public Boulder(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new MoveAroundCollisionBehaviour());
	}

	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		/*
		 * since the boulder has a more complex canMoveOnto, 
		 * i've overridden it and not use the decorators
		 */
		
		// if entity isn't a player return false
		if (!(mover instanceof Player)) {
			return false;
		}
		int newX = -1;
		int newY = -1;
		//get the coords of the block which the boulder will be moving onto
		Direction entDirection = ((Player)mover).getDirection();
		switch (entDirection) {
			case UP:
				newX = this.getX();
				newY = this.getY()-1;
				break;
			case DOWN:
				newX = this.getX();
				newY = this.getY()+1;
				break;
			case LEFT:
				newX = this.getX()-1;
				newY = this.getY();
				break;
			case RIGHT:
				newX = this.getX()+1;
				newY = this.getY();
				break;
			case NONE:
				// this doesn't make sense - the player should never be able to move onto a boulder
				// going in no direction so we'll just return false
				return false;				
		}
		
		//get object that is on this coordinate
		ArrayList<BoardEntity> movingOnto = board.getEntitiesAt(newX, newY);
		
		// if there's nothing there, then you can move onto it
		if(movingOnto.size() == 0) {
			return true;
		}
		
		// check though each entity in the space to make sure all will let this boulder move onto them
		for (BoardEntity entity : movingOnto) {
			if (entity.canMoveOnto(board, this) == false) {
				return false;
			}
		}
		return true;
	}
}
