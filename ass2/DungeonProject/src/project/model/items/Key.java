package project.model.items;

import project.model.canMoveOntoDecorators.AllowAll;
import project.model.canMoveOntoDecorators.AllowNone;
import project.model.collisionBehaviours.PickUpKey;

public class Key extends Item {
	
	private static final long serialVersionUID = 5817064896147817665L;
	
	private int keyID;
	
	/**
	 * 
	 * @param x the x coorinate (left=0)
	 * @param y the y coordinate (top=0)
	 * @param keyID the ID of the key. Should have a door to match it
	 */
	public Key(int x, int y, int keyID) {
		super(x, y);
		setKeyID(keyID);
		setCollisionBehaviour(new PickUpKey());
		setCanMoveOnto(new AllowAll(new AllowNone()));
	}
	
	/**
	 * @return the keyID
	 */
	public int getKeyID() {
		return keyID;
	}
	
	/**
	 * @param keyID the keyID to set
	 */
	public void setKeyID(int keyID) {
		this.keyID = keyID;
	}

}
