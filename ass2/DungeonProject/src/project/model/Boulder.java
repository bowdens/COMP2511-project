//package project.model;
package model;

//import project.model.CollisionBehaviours.MoveAroundCollisionBehaviour;
import model.CollisionBehaviours.MoveAroundCollisionBehaviour;


public class Boulder extends BoardEntity {
	public Boulder(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new MoveAroundCollisionBehaviour());
	}

	@Override
	public boolean canMoveOnto(BoardEntity entity, Board board) {
		//if entity isn't a player return false
		if(!(entity instanceof Player)) {
			return false;
		}
		int newX = 0;
		int newY = 0;
		//get the coords of the block which the boulder will be moving onto
		MovingEntity mover = (MovingEntity) entity;
		Direction entDirection = mover.getDirection();
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
		}
		
		//get object that is on this coordinate
		BoardEntity movinOnto = board.getEntityAt(newX, newY);
	    //check if the boulder can move onto this object
		if(movinOnto.canMoveOnto(this, board)) {
			return true;
		}else {
			return false;
		}
	}
}
