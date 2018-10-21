package project.model.obstacles;

import project.model.BoardEntity;
import project.model.canMoveOntoDecorators.AllowArrow;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.canMoveOntoDecorators.AllowPlayer;
import project.model.collisionBehaviours.WinLevelBehaviour;

public class Exit extends BoardEntity {
	
	private static final long serialVersionUID = 1L;

	public Exit(int x, int y) {
		super(x, y, "exit");
		setCollisionBehaviour(new WinLevelBehaviour());
		setCanMoveOnto(new AllowPlayer(new AllowArrow(new AllowNone())));
	}
}
