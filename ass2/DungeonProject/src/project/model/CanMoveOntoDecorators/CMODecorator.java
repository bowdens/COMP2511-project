package project.model.CanMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;

public abstract class CMODecorator implements CanMoveOnto {
	private CanMoveOnto cmoToBeDecorated;
	
	public CMODecorator(CanMoveOnto cmoToBeDecorated) {
		this.cmoToBeDecorated = cmoToBeDecorated;
	}
	
	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		return cmoToBeDecorated.canMoveOnto(board, mover);
	}

}
