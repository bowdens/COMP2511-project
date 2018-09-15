package project.model;

import java.util.ArrayList;

public abstract class MovingEntity extends BoardEntity {
	
	private static final long serialVersionUID = -9191910443455788417L;
	private Direction direction;
	private MovementBehaviour movementBehaviour;

	public MovingEntity(int x, int y) {
		super(x, y);
		setDirection(Direction.DOWN);
	}
	
	/**
	 * Tries to move the player up (decrement Y)
	 * @param board The board
	 * @author Tom Bowden
	 */
	public void moveUp(Board board) {
		int newX = getX();
		int newY = getY()-1;
		moveTo(board, newY, newX);
	}
	
	/**
	 * Tries to move the player down (increment Y)
	 * @param board The board
	 * @author Tom Bowden
	 */
	public void moveDown(Board board) {
		int newX = getX();
		int newY = getY()+1;
		moveTo(board, newY, newX);
	}
	
	/**
	 * Tries to move the player left (decrement X)
	 * @param board The board
	 * @author Tom Bowden
	 */
	public void moveLeft(Board board) {
		int newX = getX()-1;
		int newY = getY();
		moveTo(board, newY, newX);
	}
	
	/**
	 * Tries to move the player right (increment X)
	 * @param board The board
	 * @author Tom Bowden
	 */
	public void moveRight(Board board) {
		int newX = getX()+1;
		int newY = getY();
		moveTo(board, newY, newX);
	}
	
	/**
	 * Moves to x,y
	 * Checks that any instance at x,y will allow this to move onto it
	 * calls any collision behaviour specified by the entity at x,y
	 * @param board The board
	 * @param x The x coordinate to move onto (left=0)
	 * @param y The y coordinate to move onto (up=0)
	 * @return true if it moved, false if it did not
	 * @pre Ensure that moving from the existing position to the new x,y is a valid (ie to an adjacent tile)
	 * @author Tom Bowden
	 */
	public boolean moveTo(Board board, int x, int y) {
		/*
		 * Get enitity at x,y
		 * if entity is null, move there
		 * otherwise check the entities canMoveOnto if false, do not move there
		 * otherwise move there and call entity.collision
		 */
		ArrayList<BoardEntity> entities = board.getEntitiesAt(x,  y);
		for (BoardEntity entity : entities) {
			if (entity.canMoveOnto(board, this) == false) {
				// we cannot move onto an entity on the tile
				return false;
			}
		}
		
		// we can move onto the tile
		setX(x);
		setY(y);
		// call the collision for this colliding with the entities on the tile
		for (BoardEntity entity : entities) {
			entity.collide(board, this);
		}
		return true;		
	}

	public MovementBehaviour getMovementBehaviour() {
		return movementBehaviour;
	}

	public void setMovementBehaviour(MovementBehaviour movementBehaviour) {
		this.movementBehaviour = movementBehaviour;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
