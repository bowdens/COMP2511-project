package src.project.model;

public class Boulder implements CollisionBehaviour {
	public Boulder(int x, int y) {
		this.setX(x);
		this.setY(y);
		collisionBehaviour = new moveAroundBehaviour();
	}
	
	@Override
	public boolean canMoveOnto(MovingEntity entity) {
		//(assuming entity is a player) - not sure if this is true all the time -- are we gonna use this function for other MovingEntities?
		Direction entDirection = entity.getDirection();
		//get the coords of the block which the boulder will be moving onto
		switch (entDirection):
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
		//get object that is on this coordinate
	    //check if the boulder can move onto this object
		//(boulder shouldn't be able to move into pickup items/treasure/exits)
		
	    return false;
	}
}
