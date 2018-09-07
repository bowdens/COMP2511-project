
public class Obstacle extends BoardObject {

	@Override
	public boolean canMoveOnto(Board board, BoardObject mover) {
		// by default obstacles cannot be moved onto. subclasses may override that
		return false;
	}

}
