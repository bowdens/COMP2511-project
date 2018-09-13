package project.model.CollisionBehaviours;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;

public class MoveAroundCollisionBehaviour implements CollisionBehaviour {

	/**
	 * @pre Have checked that me.canMoveOnto is true for me=Boudler and mover=player
	 */
	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
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
		}
		
		me.setX(newX);
		me.setY(newY);
		
		return;
		
	}

}
