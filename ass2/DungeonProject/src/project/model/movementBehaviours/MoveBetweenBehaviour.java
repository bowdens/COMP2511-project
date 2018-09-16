package project.model.movementBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.Direction;
import project.model.MovementBehaviour;
import project.model.MovingEntity;
import project.model.Player;
import project.model.enemies.Hunter;
import project.model.movementBehaviours.dijkstra.Dijkstra;

public class MoveBetweenBehaviour implements MovementBehaviour {

	private static final long serialVersionUID = 1L;

	@Override
	public Direction nextDirection(Board board, MovingEntity me) {
		/*
		 * move to the point between the hunter and the player
		 * if the entity cannot move to that location, go towards the hunter (if there is one) instead
		 */
		Hunter hunter = null;
		Player player = board.getPlayer();
		for (BoardEntity entity : board.getBoardEntities()) {
			if (entity instanceof Hunter) {
				hunter = (Hunter) entity;
			}
		}
		
		int targetX = 0, targetY = 0;
		
		if (hunter != null && player != null) {
			targetX = (hunter.getX() + player.getX())/2;
			targetY = (hunter.getY() + player.getY())/2;
			
			if (!board.canMoveOnto(me, targetX, targetY) || !board.validX(targetX) || !board.validY(targetY)) {
				// I can't get to the mid point - go to the hunter instead (make the hunter null)
				hunter = null;
			}
		}
		
		if (hunter == null && player == null) {
			// just go towards the player instead
			return Direction.NONE;
		} 
		
		if (player == null) {
			// go to the hunter
			targetX = hunter.getX();
			targetY = hunter.getY();
		} 
		if (hunter == null) {
			// go to the player
			targetX = player.getX();
			targetY = player.getY();
		}
		
		// we now have a valid targetX and targetY - whether its the midpoint, the player, or the hunter (or we have already returned)
		
		Direction next = Dijkstra.getNextMove(board, me, targetX, targetY);
		
		return next;
	}
}
