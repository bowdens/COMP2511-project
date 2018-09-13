package src.project.model;

public class Pit extends BoardEntity implements CollisionBehaviour {
	
	public Pit(int x, int y) {
		this.setX(x);
		this.setY(y);
		collisionBehaviour = new PitKillCollisionBehaviour();
	}
	
	@Override
	public boolean canMoveOnto() {
		return true;
	}

}
