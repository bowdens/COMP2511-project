package project.model.items;

import project.model.collisionBehaviours.PickUpSword;
import project.model.deploymentBehaviours.NoDeployment;

public class Sword extends Weapon {

	private static final long serialVersionUID = -6665361744167017152L;
	private int swings;

	public Sword(int x, int y) {
		super(x, y);
		setCollisionBehaviour(new PickUpSword());
		setDeploymentBehaviour(new NoDeployment());
		this.swings = 5;
	}
	
	public void increaseSwings() {
		this.swings = 5;
	}

}
