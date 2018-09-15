package project.model.canMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;

public class AllowNone implements CanMoveOnto {

	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		return false;
	}

}
