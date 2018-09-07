
public abstract class BoardObject {
	private int x;
	private int y;
	private UpdateStrategy updateStrategy;
	private CollisionStrategy collisionStrategy;
	
	public BoardObject() {
		// TODO
	}
	
	public void update(Board board) {
		this.updateStrategy.update(board);
	}

	public void setUpdateStrategy(UpdateStrategy updateStrategy) {
		this.updateStrategy = updateStrategy;
	}
	
	public void setCollisionStrategy(CollisionStrategy collisionStrategy) {
		this.collisionStrategy = collisionStrategy;
	}
	
	public void collision(Player player, BoardObject me) {
		this.collisionStrategy.collision(player, me);
	}
	
	/**
	 * @param board The current board
	 * @param mover The BoardObject that is moving on to this object's tile
	 * @return true if the mover can move 
	 */
	public abstract boolean canMoveOnto(Board board, BoardObject mover);
	

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		// TODO: might need to check that the x is valid?
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		// TODO: might need to check that the y is valid?
		this.y = y;
	}
}
