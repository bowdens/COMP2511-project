package project.model.movementBehaviours;

import project.model.Board;
import project.model.Direction;
import project.model.MovementBehaviour;
import project.model.MovingEntity;
import project.model.Player;
import project.model.movementBehaviours.dijkstra.Dijkstra;

public class PredictBehaviour implements MovementBehaviour {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Direction nextDirection(Board board, MovingEntity me) {
		Player player = board.getPlayer();
		if(player == null) {
			return Direction.NONE;
		}
		
		int guessX = player.getX(), guessY = player.getY();
		
		// guess where the player is moving next based on their direction
		switch(player.getDirection()) {
			case DOWN:
				guessY += 1;
				break;
			case LEFT:
				guessX -= 1;
				break;
			case RIGHT:
				guessX += 1;
				break;
			case UP:
				guessY -=1;
				break;
			default:
				// keep as getX and getY
				break;
		}
		
		if (board.canMoveOnto(me, guessX, guessY) == false || board.canMoveOnto(player, guessX, guessY) == false) {
			// wont try to move there if the player can't, just moves towards the player
			// TODO: change this to still predict a different square to move to where the player might go
			guessX = player.getX();
			guessY = player.getY();
			}
		if (board.validX(guessX) == false || board.validY(guessY) == false) {
			// won't try to move somewhere where the x,y is invalid
			guessX = player.getX();
			guessY = player.getY();
		}
		
		Direction next = Dijkstra.getNextMove(board, me, guessX, guessY);
		return next;
	}
}
