package project.model.collisionBehaviours;

import java.util.ArrayList;

import project.model.Board;
import project.model.BoardEntity;
import project.model.CollisionBehaviour;
import project.model.Player;
import project.model.enemies.FlyingArrow;
import project.model.obstacles.Boulder;
import project.model.obstacles.FloorSwitch;

public class CollideWithBoulderSwitch implements CollisionBehaviour {

	private static final long serialVersionUID = 3145762904749920690L;

	/**
	 * @pre have already confirmed that the boulder will be able to spawn wherever it needs to
	 */
	@Override
	public void collide(Board board, BoardEntity mover, BoardEntity me) {
		//this is called as the boulder can move to where the player is moving it to
		/*
		 * when a player hits it, moves the boulder up 
		 * do nothing otherwise
		 */
		if (mover instanceof FlyingArrow) {
			board.removeBoardEntity(mover);
		}
		
		if (!(mover instanceof Player)) {
			// if its not a player, don't do anything
			return;
		}
			
		Player player = (Player) mover;
		
		int newX = 0, newY = 0;
		switch (player.getDirection()){
			case LEFT:
				newX = me.getX()-1;
				newY = me.getY();
				break;
			case RIGHT:
				newX = me.getX()+1;
				newY = me.getY();
				break;
			case UP:
				newX = me.getX();
				newY = me.getY()-1;
				break;
			case DOWN:
				newX = me.getX();
				newY = me.getY()+1;
				break;
			case NONE:
				break;
			default:
				break;
		}
		
		Boulder newBoulder = new Boulder(newX, newY);
		board.addBoardEntity(newBoulder);
		
		ArrayList<BoardEntity> ents = board.getEntitiesAt(newX, newY);
		//when the boulder is moving onto a pit, call the pit's collide method to destroy the boulder
		for (BoardEntity entity : ents) {
			entity.collide(board, newBoulder);
			if (!board.getBoardEntities().contains(me)) {
				// if i'm dead (fell down a pit) - that is to say, i'm not in the board's list of entities; then break
				break;
			}
		}
		
		FloorSwitch floorSwitch = new FloorSwitch(me.getX(), me.getY());
		board.addBoardEntity(floorSwitch);
		
		board.removeBoardEntity(me);
		
		return;	
	}

}
