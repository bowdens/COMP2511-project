//package project.model.CollisionBehaviours;
package model.CollisionBehaviours;

//import project.model.Board;
import model.Board;
//import project.model.BoardEntity;
import model.BoardEntity;
//import project.model.CollisionBehaviour;
import model.CollisionBehaviour;

public class pickUpTreasure implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		// TODO see if the game has been won (no more treasure left) and win it
	}

}
