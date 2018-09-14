package project.model.CanMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;
import project.model.Enemy;

public class AllowEnemy extends CMODecorator {

	public AllowEnemy(CanMoveOnto cmoToBeDecorated) {
		super(cmoToBeDecorated);
	}
	
	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		if (mover instanceof Enemy) {
			return true;
		} else {
			return super.canMoveOnto(board, mover);
		}
	}

}
