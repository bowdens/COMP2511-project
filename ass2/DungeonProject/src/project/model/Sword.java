package project.model;

import project.model.CollisionBehaviours.PickUpSword;
import project.model.DeploymentBehaviours.NoDeployment;

public class Sword extends Weapon {

	private int swings;

	public Sword(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new PickUpSword());
		setDeploymentBehaviour(new NoDeployment());
		this.swings = 5;
	}

	@Override
	public boolean canMoveOnto(Board board, BoardEntity mover) {
		// Allow only the player to move onto and collect the sword
		if (mover instanceof Player) {
			return true;
		}
		
		return false;
	}
	
	public void increaseSwings() {
		this.swings = 5;
	}

}
