//package project.model.CollisionBehaviours;
package model.CollisionBehaviours;


//import project.model.Board;
import model.Board;
//import project.model.BoardEntity;
import model.BoardEntity;
//import project.model.CollisionBehaviour;
import model.CollisionBehaviour;

public class winLevelBehaviour implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		board.winGame();
	}

}
