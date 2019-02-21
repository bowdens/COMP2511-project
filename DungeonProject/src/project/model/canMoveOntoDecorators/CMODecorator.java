package project.model.canMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;

public abstract class CMODecorator implements CanMoveOnto {
	
	private static final long serialVersionUID = 1L;
	
	private CanMoveOnto cmo;
	
	public CMODecorator(CanMoveOnto cmo) {
		this.cmo = cmo;
	}
	
	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		return cmo.canMoveOnto(board, mover);
	}

}
