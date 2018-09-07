
public class Enemy extends BoardObject {

	@Override
	public boolean canMoveOnto(Board board, BoardObject mover) {
		// anything can always move onto an enemy
		return true;
	}

}
