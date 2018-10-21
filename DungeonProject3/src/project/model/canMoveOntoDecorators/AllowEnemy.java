package project.model.canMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;

public class AllowEnemy extends CMODecorator {
	
	private static final long serialVersionUID = 1L;

	public AllowEnemy(CanMoveOnto cmo) {
		super(cmo);
	}
	
	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		if (mover.isEnemy()) {
			return true;
		} else {
			return super.canMoveOnto(board, mover);
		}
	}

}
