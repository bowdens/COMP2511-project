package src.project.model;

public class Boulder implements CollisionBehaviour {
	public Boulder(int x, int y) {
		this.setX(x);
		this.setY(y);
		collisionBehaviour = new moveAroundBehaviour();
	}
	
	@Override
	public boolean canMoveOnto() {
		return false;
	}
}
