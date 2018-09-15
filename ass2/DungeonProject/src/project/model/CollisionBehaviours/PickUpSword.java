package project.model.CollisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;

public class PickUpSword implements CollisionBehaviour {
	private static final long serialVersionUID = 1L;

	//mover is the object colliding with the stationary object me
	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		//adds the sword to the players inventory, removes the sword from the board
	}
}