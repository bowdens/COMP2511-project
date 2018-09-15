package project.model;

import project.model.DeploymentBehaviours.NoDeployment;

public class Arrow extends Weapon {

	private static final long serialVersionUID = 1L;
	private Direction direction;

	public Arrow(int x, int y) {
		super(x, y);
		setDeploymentBehaviour(new NoDeployment());
		
	}

	@Override
	public boolean canMoveOnto(BoardEntity entity) {
		// Allow only the player to move onto and collect the arrow
		if (entity instanceof Player) {
			return true;
		}
		
		return false;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
