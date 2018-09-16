package project.model.movementBehaviours;

import project.model.Board;
import project.model.Direction;
import project.model.MovementBehaviour;
import project.model.MovingEntity;
import project.model.Player;
import project.model.movementBehaviours.dijkstra.Dijkstra;

public class GoTowardsPlayer implements MovementBehaviour {

	private static final long serialVersionUID = 1510429275543454539L;

	@Override
	public Direction nextDirection(Board board, MovingEntity me) {
		Player player = board.getPlayer();
		if (player == null) {
			return Direction.NONE;
		}
		
		Direction next = Dijkstra.getNextMove(board, me, player.getX(), player.getY());
		
		return next;
	}

}
