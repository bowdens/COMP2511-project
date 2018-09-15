package project.model.obstacles;

import project.model.BoardEntity;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.canMoveOntoDecorators.AllowPlayer;
import project.model.collisionBehaviours.WinLevelBehaviour;

public class Exit extends BoardEntity {
	private static final long serialVersionUID = 3277584481207809544L;

	public Exit(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new WinLevelBehaviour());
		setCanMoveOnto(new AllowPlayer(new AllowNone()));
	}
}
