package project.model;

public abstract class MovingEntity extends BoardEntity {
	
	private MovementBehaviour movementBehaviour;

	public MovingEntity(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public MovementBehaviour getMovementBehaviour() {
		return movementBehaviour;
	}

	public void setMovementBehaviour(MovementBehaviour movementBehaviour) {
		this.movementBehaviour = movementBehaviour;
	}

}
