package project.model.canMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;

public class AllowNone implements CanMoveOnto {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {		
		return false;
	}

}
