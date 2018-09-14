package project.model.CanMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;

public interface CanMoveOnto {
	public boolean canMoveOnto(Board board, BoardEntity mover);
}
