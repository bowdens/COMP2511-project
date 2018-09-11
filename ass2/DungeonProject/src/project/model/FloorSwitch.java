package src.project.model;

public class FloorSwitch extends BoardEntity implements CollisionBehaviour {

	public FloorSwitch(int x, int y) {
		this.setX(x);
		this.setY(y);
		collisionBehaviour = new activateSwitchBehaviour();
	}
	
	@Override
	public boolean canMoveOnto() {
		return true;
	}

}
