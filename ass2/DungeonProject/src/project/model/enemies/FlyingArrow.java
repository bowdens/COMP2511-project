package project.model.enemies;

import project.model.Board;
import project.model.MovingEntity;

public class FlyingArrow extends MovingEntity {

	private static final long serialVersionUID = 1L;

	public FlyingArrow(int x, int y) {
		super(x, y);
	}

	/* (non-Javadoc)
	 * @see project.model.BoardEntity#update(project.model.Board)
	 */
	@Override
	public void update(Board board) {
		switch (getDirection()) {
		case UP:
			moveUp(board);
			break;
		case DOWN:
			moveDown(board);
			break;
		case LEFT:
			moveLeft(board);
			break;
		case RIGHT:
			moveRight(board);
			break;
		default:
			break;
		}
	}

}
