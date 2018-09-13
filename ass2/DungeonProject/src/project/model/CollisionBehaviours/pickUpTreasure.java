package project.model.CollisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;

public class pickUpTreasure implements CollisionBehaviour {

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		// TODO see if the game has been won (no more treasure left) and win it
	}

}
