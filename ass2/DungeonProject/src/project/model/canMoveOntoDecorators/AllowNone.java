package project.model.canMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;
import project.model.enemies.FlyingArrow;

public class AllowNone implements CanMoveOnto {

	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		if (mover instanceof FlyingArrow) {
			return true;
		}
		
		return false;
	}

}
