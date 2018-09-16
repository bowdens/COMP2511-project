package project.model.movementBehaviours;

import java.util.ArrayList;

import project.model.Board;
import project.model.BoardEntity;
import project.model.Direction;
import project.model.MovementBehaviour;
import project.model.MovingEntity;
import project.model.Player;

public class RunTowardsBehaviour implements MovementBehaviour {
	
	private static final long serialVersionUID = 1L;

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
		
		for (BoardEntity entity: board.getBoardEntities()) {
			if (entity instanceof Player) {
				playerX = entity.getX();
				playerY = entity.getY();
			}
		}
		
		int orginalDistY = Math.abs(playerY - me.getY());
		int orginalDistX = Math.abs(playerX - me.getX());
		int newDistY =  Math.abs(playerY - (me.getY() + 1));
		int newDistX = Math.abs(playerX - (me.getX() + 1));
		
		// Goes towards the player in the vertical direction as far as possible
		// After that it will move away in the horizontal direction
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
