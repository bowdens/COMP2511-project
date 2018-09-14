package project.model;

import project.model.CollisionBehaviours.MoveAroundCollisionBehaviour;

public class Boulder extends BoardEntity {
	private static final long serialVersionUID = 7898209246840234989L;

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
		}
		
		//get object that is on this coordinate
		BoardEntity movingOnto = board.getEntityAt(newX, newY);
		
		// if there's nothing there, then you can move onto it
		if(movingOnto == null) {
			return true;
		}
		
	    //check if the boulder can move onto this object
		if (movingOnto.canMoveOnto(board, this)) {
			return true;
		} else {
			return false;
		}
	}
}
