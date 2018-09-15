package project.model.items;

import project.model.canMoveOntoDecorators.AllowNone;
import project.model.canMoveOntoDecorators.AllowPlayer;

public class Arrow extends Weapon {

	private static final long serialVersionUID = 3046909360800506012L;

	public Arrow(int x, int y) {
		super(x, y);
		setCanMoveOnto(new AllowPlayer(new AllowNone()));
	}
}
