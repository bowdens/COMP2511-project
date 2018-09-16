package project.model.obstacles;

import project.model.Board;
import project.model.BoardEntity;
import project.model.Player;
import project.model.canMoveOntoDecorators.AllowAll;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.collisionBehaviours.NoCollision;

public class Door extends BoardEntity {

	private static final long serialVersionUID = -2797313009743890169L;
	private int keyId;
	private boolean open;
	
	public Door(int x, int y, int keyId) {
		super(x, y);
		setCollisionBehaviour(new NoCollision());
		setCanMoveOnto(new AllowNone());
		setKeyId(keyId);
		open = false;
	}

	/**
	 * @return the keyId
	 */
	public int getKeyId() {
		return keyId;
	}

	/**
	 * @param keyId the keyId to set
	 */
	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	@Override
	public void update(Board board) {
		/*
		 * when it updates, it checks to see if the player has the key
		 * if it does, use the allowAll cmo, otherwise the allowNone
		 */
		Player player = board.getPlayer();
		if (player == null) {
			// keep it as nocollision
			return;
		}
		if (player.hasKey(getKeyId())) {
			open = true;
			setCanMoveOnto(new AllowAll(new AllowNone()));
		} else {
			open = false;
			setCanMoveOnto(new AllowNone());
		}
	}

}
