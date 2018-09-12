package src.project.model;

public class Treasure extends BoardEntity implements CollisionBehaviour {
	public Treasure(int x, int y) {
		this.setX(x);
		this.setY(y);
		collisionBehaviour = new winLevelBehaviour();
	}
	
	@Override
	public boolean canMoveOnto() {
		return false;
	}
}
