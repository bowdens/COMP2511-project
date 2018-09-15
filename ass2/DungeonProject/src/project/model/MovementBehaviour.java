package project.model;

public interface MovementBehaviour {
	Direction nextDirection(Board board, MovingEntity me);
}
