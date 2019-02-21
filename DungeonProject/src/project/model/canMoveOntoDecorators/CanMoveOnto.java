package project.model.canMoveOntoDecorators;

import java.io.Serializable;

import project.model.Board;
import project.model.BoardEntity;

public interface CanMoveOnto extends Serializable {
	public boolean canMoveOnto(Board board, BoardEntity mover);
}
