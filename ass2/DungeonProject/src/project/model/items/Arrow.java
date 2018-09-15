package project.model.items;

import project.model.Direction;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.canMoveOntoDecorators.AllowPlayer;
import project.model.deploymentBehaviours.NoDeployment;

public class Arrow extends Weapon {

	private static final long serialVersionUID = 1L;
	private Direction direction;

	public Arrow(int x, int y) {
		super(x, y);
		setDeploymentBehaviour(new NoDeployment());
		setCanMoveOnto(new AllowPlayer(new AllowNone()));
	}
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
