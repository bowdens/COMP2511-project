package project.model;

public abstract class MovingEntity extends BoardEntity {
	
	private Direction direction;
	private MovementBehaviour movementBehaviour;

	public MovingEntity(int x, int y) {
		super(x, y);
		setDirection(Direction.DOWN);
	}
	
	/**
	 * Moves to x,y
	 * Checks that any instance at x,y will allow this to move onto it
	 * calls any collision behaviour specified by the entity at x,y
	 * @param board The board
	 * @param x The x coordinate to move onto (left=0)
	 * @param y The y coordinate to move onto (up=0)
	 * @return true if it moved, false if it did not
	 * @pre Ensure that moving from the existing position to the new x,y is a valid (ie to an adjacent tile)
	 * @author Tom Bowden
	 */
	public boolean moveTo(Board board, int x, int y) {
		/*
		 * Get enitity at x,y
		 * if entity is null, move there
		 * otherwise check the entities canMoveOnto if false, do not move there
		 * otherwise move there and call entity.collision
		 */
		
		BoardEntity entity = board.getEntityAt(x,y);
		if (entity == null) {
			// move there
			setX(x);
			setY(y);
			return true;
		} else {
			if (entity.canMoveOnto(this, board)) {
				// I can move onto it
				setX(x);
				setY(y);
				// call the collision for this colliding with the entity
				entity.collide(board, this);
				return true;
			} else {
				return false;
			}
		}
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
