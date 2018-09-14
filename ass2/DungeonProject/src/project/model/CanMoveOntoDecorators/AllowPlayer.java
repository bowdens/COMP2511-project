package project.model.CanMoveOntoDecorators;

import project.model.Board;
import project.model.BoardEntity;
import project.model.Player;

public class AllowPlayer extends CMODecorator {

	public AllowPlayer(CanMoveOnto cmoToBeDecorated) {
		super(cmoToBeDecorated);
	}
	
	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		return (mover instanceof Player);
	}

}
