package project.model.canMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;

public class AllowBoulder extends CMODecorator {

	private static final long serialVersionUID = 1L;

	public AllowBoulder(CanMoveOnto cmo) {
		super(cmo);
	}
	
	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		if (mover.getEntityType().equals("boulder")) {
			return true;
		} else {
			return super.canMoveOnto(board, mover);
		}
	}

}
