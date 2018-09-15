package project.model.items;

import project.model.BoardEntity;
import project.model.canMoveOntoDecorators.AllowEnemy;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.canMoveOntoDecorators.AllowPlayer;

public abstract class Item extends BoardEntity {

	private static final long serialVersionUID = 1L;

	public Item(int x, int y) {
		super(x, y);
		setCanMoveOnto(new AllowPlayer(new AllowEnemy(new AllowNone())));
	}

}
