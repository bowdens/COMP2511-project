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
		 * move to the point between the player and hunter
		 * when the entity cannot move to that location, go towards the hunter (if there is one) instead
		 */
		Hunter hunter = null;
		Player player = board.getPlayer();
		for (BoardEntity entity : board.getBoardEntities()) {
			if (entity instanceof Hunter) {
				hunter = (Hunter) entity;
			}
		}
		
		int target_X = 0, target_Y = 0;
		
		if (hunter != null && player != null) {
			target_X = (hunter.getX() + player.getX())/2;
			target_Y = (hunter.getY() + player.getY())/2;
			
			if (!board.canMoveOnto(me, target_X, target_Y) || !board.validX(target_X) || !board.validY(target_Y)) {
				// I can't get to the mid point - go to the hunter instead (make the hunter null)
				hunter = null;
			}
		}
		
		if ( player == null && hunter == null) {
			// just go towards the player instead
			return Direction.NONE;
		} 
		
		if (hunter == null) {
			// go to the player if hunter is null
			target_X = player.getX();
			target_Y = player.getY();
		}
		
		if (player == null) {
			// go to the hunter if player is null
			target_X = hunter.getX();
			target_Y = hunter.getY();
		} 
		
		// we now have a valid target_X and target_Y - whether its the midpoint, the player, or the hunter (or we have already returned)
		
		Direction next = Dijkstra.getNextMove(board, me, target_X, target_Y);
		
		return next;
	}
}
