package project.model;

import java.io.Serializable;

public interface MovementBehaviour extends Serializable {
	Direction nextDirection(Board board, MovingEntity me);
}
