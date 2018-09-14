package project.model;

import project.model.CollisionBehaviours.MoveAroundCollisionBehaviour;

public class Boulder extends BoardEntity {
	private static final long serialVersionUID = 7898209246840234989L;

	public Boulder(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new MoveAroundCollisionBehaviour());
	}

	@Override
	public boolean canMoveOnto(BoardEntity entity) {
		//if entity isn't a player return false
		if(!(entity instanceof Player)) {
			return false;
		}
		int newX;
		int newY;
		//get the coords of the block which the boulder will be moving onto
		Direction entDirection = ((Player)entity).getDirection();
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
