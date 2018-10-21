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
		
		int guess_X = player.getX(), guess_Y = player.getY();
		
		// guess where the player is moving in the next step based on their direction
		switch(player.getDirection()) {
			case DOWN:
				guess_Y += 1;
				break;
			case LEFT:
				guess_X -= 1;
				break;
			case RIGHT:
				guess_X += 1;
				break;
			case UP:
				guess_Y -=1;
				break;
			default:
				// keep as getx and gety
				break;
		}
		
		if (board.canMoveOnto(me, guess_X, guess_Y) == false || board.canMoveOnto(player, guess_X, guess_Y) == false) {
			// wont try to move there when the player can't, just moves towards the player
			// TODO: change this to still predict a different square to move where the player might go
			guess_X = player.getX();
			guess_Y = player.getY();
			}
		if (board.validX(guess_X) == false || board.validY(guess_Y) == false) {
			// won't try to move the place where the x,y is invalid
			guess_X = player.getX();
			guess_Y = player.getY();
		}
		
		Direction next = Dijkstra.getNextMove(board, me, guess_X, guess_Y);
		return next;
	}
}
