package src.project.model;

public class Boulder implements CollisionBehaviour {
	public Boulder(int x, int y) {
		this.setX(x);
		this.setY(y);
		collisionBehaviour = new moveAroundBehaviour();
	}
	
	@Override
	public boolean canMoveOnto(MovingEntity entity, Board board) {
		//if entity isn't a player return false
		if(!(entity instanceof Player)) {
			return false;
		}
		
		//get the coords of the block which the boulder will be moving onto
		Direction entDirection = entity.getDirection();
		switch (entDirection) {
			case UP:
				int newX = this.getX();
				int newY = this.getY()-1;
				break;
			case DOWN:
				int newX = this.getX();
				int newY = this.getY()+1;
				break;
			case LEFT:
				int newX = this.getX()-1;
				int newY = this.getY();
				break;
			case RIGHT:
				int newX = this.getX()+1;
				int newY = this.getY();
				break;
		}
		
		//get object that is on this coordinate
		BoardEntity movinOnto = board.getEntityAt(newX, newY);
	    //check if the boulder can move onto this object
		if(movinOnto.canMoveOnto(this, board)) {
			this.setX(newX);
			this.setY(newY);
			return true;
		}else {
			return false;
		}
	}
}
