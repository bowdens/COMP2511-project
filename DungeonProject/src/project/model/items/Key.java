package project.model.items;

import project.model.collisionBehaviours.PickUpKey;

public class Key extends Item {
	
	private static final long serialVersionUID = 1L;
	
	private static int keyCount;
	
	private int keyID;
	
	/**
	 * 
	 * @param x the x coorinate (left=0)
	 * @param y the y coordinate (top=0)
	 * @param keyID the ID of the key. Should have a door to match it
	 */
	public Key(int x, int y) {
		super(x, y, "key");
		setKeyID(++keyCount);
		setCollisionBehaviour(new PickUpKey());
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
