package project.model;

public abstract class BoardEntity {
	//not sure about this:
	//might change x and y to public since it's being accessed by the entities constructor
	//although, would there be a problem with using setX and setY in the constructors? In that case I could keep them private
	private int x;
	private int y;
	//private EntityType type;
	private CollisionBehaviour collisionBehaviour;
	
<<<<<<< HEAD
	
	public abstract boolean canMoveOnto();
=======
	public abstract boolean canMoveOnto(MovingEntity entity);
	
>>>>>>> master
	
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
	
	public void collide(Player player) {
		collisionBehaviour.collide(player, this);
	}

	public EntityType getType() {
		return type;
	}
	
}
