package project.model.obstacles;

import project.model.Board;
import project.model.BoardEntity;
import project.model.Player;
import project.model.canMoveOntoDecorators.AllowAll;
import project.model.canMoveOntoDecorators.AllowArrow;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.collisionBehaviours.NoCollision;

public class Door extends BoardEntity {

	private static final long serialVersionUID = 1L;
	
	private static int doorCount;
	
	private int keyId;
	private boolean open;
	
	public Door(int x, int y) {
		super(x, y, "closed door");
		setCanMoveOnto(new AllowArrow(new AllowNone()));
		setCollisionBehaviour(new NoCollision());
		setKeyId(++doorCount);
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
	
	/**
	 * This method returns true if the door is open
	 * and false if the it is closed.
	 * @return true if open and false otherwise
	 */
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
		
		if (player != null) {
			if (player.hasKey(getKeyId())) {
				open = true;
				setEntityType("open door");
				setCanMoveOnto(new AllowAll(new AllowNone()));
			} else {
				open = false;
				setCanMoveOnto(new AllowNone());
			}
		}
	}

}
