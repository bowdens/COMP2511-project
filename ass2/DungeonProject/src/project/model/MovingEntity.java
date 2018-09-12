package project.model;

public abstract class MovingEntity extends BoardEntity {
	
	private Direction direction;
	private MovementBehaviour movementBehaviour;

	public MovingEntity(int x, int y) {
		super(x, y);
		setDirection(Direction.DOWN);
	}
	
	protected void moveTo(Board board, int x, int y) {
		//get object at (x,y) if any
		//check if player canMoveOnto();
	}

	public MovementBehaviour getMovementBehaviour() {
		return movementBehaviour;
	}

	public void setMovementBehaviour(MovementBehaviour movementBehaviour) {
		this.movementBehaviour = movementBehaviour;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
