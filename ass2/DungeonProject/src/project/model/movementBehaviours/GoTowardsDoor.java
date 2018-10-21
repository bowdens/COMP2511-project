package project.model.movementBehaviours;

import java.util.ArrayList;

import project.model.Board;
import project.model.BoardEntity;
import project.model.Direction;
import project.model.MovementBehaviour;
import project.model.MovingEntity;
import project.model.movementBehaviours.dijkstra.Dijkstra;
import project.model.obstacles.Exit;

public class GoTowardsDoor implements MovementBehaviour {

	private static final long serialVersionUID = 1510429275543454539L;

	@Override
	public Direction nextDirection(Board board, MovingEntity me) {
		ArrayList<BoardEntity> entities = board.getBoardEntities();
		Exit exit = null;
		for (BoardEntity entity : entities) {
			if (entity instanceof Exit) {
				exit = (Exit) entity;
				break;
			}
		}
		
		// if the exit is null that means there isn't one - we will just stand still
		if (exit == null) {
			return Direction.NONE;
		} else {
			return Dijkstra.getNextMove(board, me, exit.getX(), exit.getY());
		}
	}

}
