//package project.model;
package model;

import project.model.CanMoveOntoDecorators.AllowPlayer;
import project.model.CanMoveOntoDecorators.AllowBoulder;
import project.model.CanMoveOntoDecorators.AllowNone;
import project.model.CollisionBehaviours.PitKillCollisionBehaviour;

public class Pit extends BoardEntity {
	
	private static final long serialVersionUID = 2747927405051690137L;

	public Pit(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new PitKillCollisionBehaviour());
		setCanMoveOnto(new AllowPlayer(new AllowBoulder(new AllowNone())));
	}

}
