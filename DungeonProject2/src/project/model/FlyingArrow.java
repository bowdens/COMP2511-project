package project.model;

public class FlyingArrow extends MovingEntity {

	private static final long serialVersionUID = 1L;

	public FlyingArrow(int x, int y, Direction direction) {
		super(x, y, "flying arrow");
		setDirection(direction);
	}

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
