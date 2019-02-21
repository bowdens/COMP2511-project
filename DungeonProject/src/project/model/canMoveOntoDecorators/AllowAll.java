package project.model.canMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;

public class AllowAll extends CMODecorator {
	
	private static final long serialVersionUID = 1L;

	public AllowAll(CanMoveOnto cmo) {
		super(cmo);
	}

	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		return true;
	}

}
