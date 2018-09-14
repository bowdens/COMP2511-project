package project.model.CanMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;
import project.model.Boulder;

public class AllowBoulder extends CMODecorator {

	public AllowBoulder(CanMoveOnto cmoToBeDecorated) {
		super(cmoToBeDecorated);
	}
	
	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		if (mover instanceof Boulder) {
			return true;
		} else {
			return super.canMoveOnto(board, mover);
		}
	}

}
