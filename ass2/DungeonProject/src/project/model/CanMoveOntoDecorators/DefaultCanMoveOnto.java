package project.model.CanMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;

public class DefaultCanMoveOnto implements CanMoveOnto {

	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		return false;
	}

}
