package project.model.obstacles;

import project.model.canMoveOntoDecorators.AllowPlayer;
import project.model.BoardEntity;
import project.model.canMoveOntoDecorators.AllowBoulder;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.collisionBehaviours.PitKillCollisionBehaviour;

public class Pit extends BoardEntity {
	
	private static final long serialVersionUID = 2747927405051690137L;

	public Pit(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new PitKillCollisionBehaviour());
		setCanMoveOnto(new AllowPlayer(new AllowBoulder(new AllowNone())));
	}

}
