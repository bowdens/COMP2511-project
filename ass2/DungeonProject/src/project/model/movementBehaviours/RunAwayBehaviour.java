package project.model.movementBehaviours;

import java.util.Hashtable;
import java.util.Map;

import project.model.Board;
import project.model.Direction;
import project.model.MovementBehaviour;
import project.model.MovingEntity;
import project.model.Player;
import project.model.movementBehaviours.dijkstra.Dijkstra;

public class RunAwayBehaviour implements MovementBehaviour {

	private static final long serialVersionUID = 1L;

	@Override
	public Direction nextDirection(Board board, MovingEntity me) {
		/*
		 * TODO:
		 * 1. get players location
		 * 2. see if moving in each direction will result in a lower distance
		 */
		Player player = board.getPlayer();
		if (player == null) {
			return Direction.NONE;
		}
		int playerX = player.getX(), playerY = player.getY();
		int meX = me.getX(), meY = me.getY();
		int currentDist = Dijkstra.distance(board, me, meX, meY, playerX, playerY);
		Map<Direction, Integer> nextDistances = new Hashtable<Direction, Integer>();
		// if we were to not move, the distance would be current
		nextDistances.put(Direction.NONE, currentDist);
		// add each distance for every possible move into the map
		// if we can't move there, treat the distance to the player as 0
		int upX = meX, upY = meY-1;
		if (board.canMoveOnto(me, upX, upY) && board.validX(upX) && board.validY(upY)) {
			int upDist = Dijkstra.distance(board, me, upX, upY, playerX, playerY);
			nextDistances.put(Direction.UP, upDist);
		} else {
			nextDistances.put(Direction.UP, 0);
		}
		
		int downX = meX, downY = meY+1;
		if (board.canMoveOnto(me, downX, downY) && board.validX(downX) && board.validY(downY)) {
			int downDist = Dijkstra.distance(board, me, downX, downY, playerX, playerY);
			nextDistances.put(Direction.DOWN, downDist);
		} else {
			nextDistances.put(Direction.DOWN, 0);
		}
		
		int leftX = meX-1, leftY = meY;
		if (board.canMoveOnto(me, leftX, leftY) && board.validX(leftX) && board.validY(leftY)) {
			int leftDist = Dijkstra.distance(board, me, leftX, leftY, playerX, playerY);
			nextDistances.put(Direction.LEFT, leftDist);
		} else {
			nextDistances.put(Direction.LEFT, 0);
		}
		
		int rightX = meX+1, rightY = meY;
		if (board.canMoveOnto(me, rightX, rightY) && board.validX(rightX) && board.validY(rightY)) {
			int rightDist = Dijkstra.distance(board, me, rightX, rightY, playerX, playerY);
			nextDistances.put(Direction.RIGHT, rightDist);
		} else {
			nextDistances.put(Direction.RIGHT, 0);
		}
		
		Direction best = Direction.NONE;
		for (Direction dir : nextDistances.keySet()) {
			if (nextDistances.get(dir) > nextDistances.get(best)) {
				best = dir;
			}
		}
		
		//System.out.println(nextDistances + " best is " + best);
		
		return best;
	}

}
