package project.model.collisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;

import java.util.ArrayList;

public class MoveAroundCollisionBehaviour implements CollisionBehaviour {

	private static final long serialVersionUID = 1L;

	/**
	 * @pre Have checked that me.canMoveOnto is true for me=Boudler and mover=player
	 */
	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		//this is called when the boulder can move to where the player is moving it to
		/*
		 * Moves the boulder up when a player hits it
		 * does not do anything otherwise
		 */
		if (!(mover instanceof Player)) {
			// if its not a player, don't move the boulder
			return;
		}
			
		Player player = (Player) mover;
		
		int newX = 0, newY = 0;
		switch (player.getDirection()){
			case UP:
				newX = me.getX();
				newY = me.getY()-1;
				break;
			case DOWN:
				newX = me.getX();
				newY = me.getY()+1;
				break;
			case LEFT:
				newX = me.getX()-1;
				newY = me.getY();
				break;
			case RIGHT:
				newX = me.getX()+1;
				newY = me.getY();
				break;
			case NONE:
				break;
			default:
				break;
		}
		
		ArrayList<BoardEntity> ents = board.getEntitiesAt(newX, newY);
		me.setX(newX);
		me.setY(newY);
		//if the boulder is moving onto a pit, call the pit's collide method to destroy the boulder
				if(ents.size() != 0) {
					ents.get(0).collide(board, me);
				}
		
		return;
		
	}

}
