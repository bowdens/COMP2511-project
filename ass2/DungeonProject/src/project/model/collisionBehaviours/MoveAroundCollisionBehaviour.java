package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;
import project.model.enemies.FlyingArrow;

import java.util.ArrayList;

public class MoveAroundCollisionBehaviour implements CollisionBehaviour {

	
	private static final long serialVersionUID = 1L;

	/**
	 * @pre Have checked that me.canMoveOnto is true for me=Boudler and mover=player
	 */
	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		//this is called if the boulder can move to where the player is moving it to
		/*
		 * Moves the boulder up if a player hits it
		 * do nothing otherwise
		 */
		if (mover instanceof FlyingArrow) {
			board.removeBoardEntity(mover);
		}
		
		if (!(mover instanceof Player)) {
			// if its not a player, don't move the boulder
			return;
		}
			
		Player player = (Player) mover;
		
		int new_X = 0, new_Y = 0;
		switch (player.getDirection()){
			case UP:
				new_X = me.getX();
				new_Y = me.getY()-1;
				break;
			case DOWN:
				new_X = me.getX();
				new_Y = me.getY()+1;
				break;
			case LEFT:
				new_X = me.getX()-1;
				new_Y = me.getY();
				break;
			case RIGHT:
				new_X = me.getX()+1;
				new_Y = me.getY();
				break;
			case NONE:
				break;
			default:
				break;
		}
		
		ArrayList<BoardEntity> ents = board.getEntitiesAt(new_X, new_Y);
		me.setX(new_X);
		me.setY(new_Y);
		
		//when the boulder is moving onto a pit, call the pit's collide method to destroy the boulder
		for (BoardEntity entity : ents) {
			entity.collide(board, me);
			if (!board.getBoardEntities().contains(me)) {
				
				// when i'm dead (fell down a pit) - that is to say, i'm not in the board's list of entities; then break
				break;
			}
		}
		return;	
	}
}
