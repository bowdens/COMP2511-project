package project.model.canMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;

public interface CanMoveOnto {
	public boolean canMoveOnto(Board board, BoardEntity mover);
}
