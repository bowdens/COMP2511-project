package project.model;

public class Wall extends BoardEntity {
	
private static final long serialVersionUID = 1L;

	public Wall(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean canMoveOnto(BoardEntity entity) {
		return false;
	}

}
