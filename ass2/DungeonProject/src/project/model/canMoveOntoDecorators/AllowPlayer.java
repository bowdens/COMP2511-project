package project.model.canMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;
import project.model.Player;

public class AllowPlayer extends CMODecorator {

	public AllowPlayer(CanMoveOnto cmoToBeDecorated) {
		super(cmoToBeDecorated);
	}
	
	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		if (mover instanceof Player) {
			return true;
		} else {
			return super.canMoveOnto(board, mover);
		}
	}

}
