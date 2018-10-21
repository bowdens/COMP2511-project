package project.model.obstacles;

import project.model.BoardEntity;
import project.model.canMoveOntoDecorators.AllowAll;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.collisionBehaviours.CollideWithFloorSwitch;

public class FloorSwitch extends BoardEntity {
	
	private static final long serialVersionUID = 1L;
	
	private boolean switchActivated;

	public FloorSwitch(int x, int y) {
		super(x, y, "floor switch");
		setSwitchActivated(false);
		setCollisionBehaviour(new CollideWithFloorSwitch());
		setCanMoveOnto(new AllowAll(new AllowNone()));
	}

	/**
	 * @return the switchActivated
	 */
	public boolean isSwitchActivated() {
		return switchActivated;
	}

	/**
	 * @param switchActivated the switchActivated to set
	 */
	public void setSwitchActivated(boolean switchActivated) {
		this.switchActivated = switchActivated;
	}

}
