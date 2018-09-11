package project.model;

public abstract class BoardEntity {
	
	private int x;
	private int y;
	private EntityType type;
	private CollisionBehaviour collisionBehaviour;
	
	public BoardEntity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public CollisionBehaviour getCollisionBehaviour() {
		return collisionBehaviour;
	}

	public void setCollisionBehaviour(CollisionBehaviour collisionBehaviour) {
		this.collisionBehaviour = collisionBehaviour;
	}

	public EntityType getType() {
		return type;
	}
	
}