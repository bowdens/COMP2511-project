
public class Player extends BoardObject {
	// TODO: implement the rest of Player
	
	@Override
	public boolean canMoveOnto(Board board, BoardObject mover) {
		// the player can always be moved onto
		return true;
	}
}
