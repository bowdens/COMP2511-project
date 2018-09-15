package project.model.canMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;

public class AllowAll extends CMODecorator {

	public AllowAll(CanMoveOnto cmoToBeDecorated) {
		super(cmoToBeDecorated);
	}

	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		return true;
	}

}
