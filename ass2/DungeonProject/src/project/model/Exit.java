package project.model;

import project.model.CanMoveOntoDecorators.AllowNone;
import project.model.CanMoveOntoDecorators.AllowPlayer;
import project.model.CollisionBehaviours.winLevelBehaviour;

public class Exit extends BoardEntity {
	private static final long serialVersionUID = 3277584481207809544L;

	public Exit(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new winLevelBehaviour());
		setCanMoveOnto(new AllowPlayer(new AllowNone()));
	}
}
