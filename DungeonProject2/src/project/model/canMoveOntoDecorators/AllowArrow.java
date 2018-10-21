package project.model.canMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;

public class AllowArrow extends CMODecorator {

	private static final long serialVersionUID = 1L;

	public AllowArrow(CanMoveOnto cmo) {
		super(cmo);
	}

	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		if (mover.getEntityType().equals("flying arrow")) {
			return true;
		} else {
			return super.canMoveOnto(board, mover);
		}
	}
	
	

}
