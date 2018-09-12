
package project.model.CollisionBehaviours;

import project.model.BoardEntity;

public class PitKillCollisionBehaviour implements CollisionBehaviour{
	
	@Override
	public void collide(Player player, BoardEntity me) {
		//destroys the object that collides with it
		//Can easily remove player, but how can we remove a boulder that is pushed down the pit??
		
		return;
	}
}
