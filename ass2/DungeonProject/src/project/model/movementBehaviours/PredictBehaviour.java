package project.model.movementBehaviours;

import java.util.ArrayList;

import project.model.Board;
import project.model.BoardEntity;
import project.model.Direction;
import project.model.Enemy;
import project.model.MovementBehaviour;
import project.model.MovingEntity;
import project.model.Player;
import project.model.obstacles.Wall;

public class PredictBehaviour implements MovementBehaviour {
	
	@Override
	public Direction nextDirection(Board board, MovingEntity me) {
		Direction direction = Direction.NONE;
		
		// Makes sure if the enemy is stuck it will move even if that means getting
		// further from to the player initially (not sure if you would want this)
		if (checkDirection(board, me, me.getX(), (me.getY() + 1))) {
			direction = Direction.UP;
		} else if (checkDirection(board, me, (me.getX() + 1), me.getY())) {
			direction = Direction.RIGHT;
		}
		
		int playerX = 0;
		int playerY = 0;
		int objectiveX = 0;
		int objectiveY = 0;
		
		for (BoardEntity entity: board.getBoardEntities()) {
			if (entity instanceof Player) {
				playerX = entity.getX();
				playerY = entity.getY();
			} else if (!(entity instanceof Wall) && !(entity instanceof Enemy)) {
				objectiveX = entity.getX();
				objectiveY = entity.getY();
			}
		}
		
		int objectiveDist = Math.abs(playerX - objectiveX) + Math.abs(playerY - objectiveY);
		int newObjectiveDist = 0;
		
		// find the closest non trivial board entity from the player
		for (BoardEntity entity: board.getBoardEntities()) {
			if (!(entity instanceof Wall) && !(entity instanceof Enemy)) {
				newObjectiveDist = Math.abs(playerX - objectiveX) + Math.abs(playerY - objectiveY);
				if (newObjectiveDist < objectiveDist) {
					objectiveX = entity.getX();
					objectiveY = entity.getY();
					objectiveDist = newObjectiveDist;
				}
			}
		}
		
		int orginalDistY = Math.abs(objectiveY - me.getY());
		int orginalDistX = Math.abs(objectiveX - me.getX());
		int newDistY =  Math.abs(objectiveY - (me.getY() + 1));
		int newDistX = Math.abs(objectiveX - (me.getX() + 1));
		
		// Goes towards the closest non trivial board entity
		if (checkDirection(board, me, me.getX(), (me.getY() + 1)) && (newDistY < orginalDistY)) {
			direction = Direction.UP;
		} else if (checkDirection(board, me, me.getX(), (me.getY() - 1))) {
			direction = Direction.DOWN;
		} else if (checkDirection(board, me, (me.getX() + 1), me.getY()) && (newDistX < orginalDistX)) {
			direction = Direction.RIGHT;
		} else if (checkDirection(board, me, (me.getX() - 1), me.getY())) {
			direction = Direction.LEFT;
		}
		
		return direction;
	}
	
	private boolean checkDirection(Board board, BoardEntity me, int x, int y) {
		ArrayList<BoardEntity> entities = board.getEntitiesAt(x, y);
		
		for (BoardEntity entity : entities) {
			if (entity != null && entity.canMoveOnto(board, me) == false) {
				return false;
			}
		}
		
		return true;
	}	

}
