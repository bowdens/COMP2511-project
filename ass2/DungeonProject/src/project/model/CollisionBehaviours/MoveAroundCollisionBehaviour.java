package project.model.CollisionBehaviours;

import project.model.BoardEntity;

public class MoveAroundCollisionBehaviour implements CollisionBehaviour{
	@Override
	public void collide(Player player, BoardEntity me) {
		//should move the boulder entity infront of the player
		switch: (player.getdirection()){
			case UP:
				int newX = me.getX();
				int newY = me.getY()-1;
				moveTo(board, newY, newX);
				break;
			case DOWN:
				int newX = me.getX();
				int newY = me.getY()+1;
				moveTo(board, newY, newX);
				break;
			case LEFT:
				int newX = me.getX()-1;
				int newY = me.getY();
				moveTo(board, newY, newX);
				break;
			case RIGHT:
				
				break;
		}
		return;
	}

}
