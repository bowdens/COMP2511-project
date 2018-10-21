package project.model.items;

import project.model.BoardEntity;
import project.model.canMoveOntoDecorators.AllowArrow;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.canMoveOntoDecorators.AllowPlayer;

public abstract class Item extends BoardEntity {

	private static final long serialVersionUID = 1L;

	public Item(int x, int y, String entityType) {
		super(x, y, entityType);
		setCanMoveOnto(new AllowPlayer(new AllowArrow(new AllowNone())));
	}

}
