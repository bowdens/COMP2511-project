package src.project.model;

public class FloorSwitch extends BoardEntity implements CollisionBehaviour {
	
	private boolean activated;
	public FloorSwitch(int x, int y) {
		this.setX(x);
		this.setY(y);
		this.activated = false;
		collisionBehaviour = new activateSwitchCollisionBehaviour();
	}
	
	@Override
	public boolean canMoveOnto() {
		return true;
	}

}
