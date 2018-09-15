package project.model.movementBehaviours;

import project.model.Board;
import project.model.Direction;
import project.model.MovementBehaviour;
import project.model.MovingEntity;

public class RunAwayBehaviour implements MovementBehaviour {

	@Override
	public Direction nextDirection(Board board, MovingEntity me) {
		/*
		 * TODO:
		 * 1. get players location
		 * 2. find appropriate tile that is distant from player
		 * 3. use dijkstra's to go to that tile
		 * 4. return the direction that would lead to the next tile being the first move from dijkstra
		 */
		return Direction.NONE;
	}

}
