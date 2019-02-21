package project.model.canMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;

public class AllowPlayer extends CMODecorator {
	
	private static final long serialVersionUID = 1L;

	public AllowPlayer(CanMoveOnto cmo) {
		super(cmo);
	}
	
	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		if (mover.isPlayer()) {
			return true;
		} else {
			return super.canMoveOnto(board, mover);
		}
	}

}
