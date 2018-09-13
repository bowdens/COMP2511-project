package src.project.model;

public class Exit implements CollisionBehaviour {
	public Exit(int x, int y) {
		this.setX(x);
		this.setY(y);
		collisionBehaviour = new winLevelBehaviour();
	}
	
	@Override
	public boolean canMoveOnto() {
		return false;
	}
}
