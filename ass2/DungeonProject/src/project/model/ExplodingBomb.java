package project.model;

public class ExplodingBomb extends BoardEntity {
	private int ticks;
	
	/**
	 * @param ticksTillExploded the number of turns the bomb willt take to explode
	 */
	public ExplodingBomb(int x, int y, int ticksTillExploded) {
		super(x, y);
		setTicks(ticksTillExploded);
	}

	@Override
	public boolean canMoveOnto(BoardEntity entity) {
		// nothing can move onto a bomb
		return false;
	}

	/**
	 * @return the ticks
	 */
	public int getTicks() {
		return ticks;
	}

	/**
	 * @param ticks the ticks to set
	 */
	public void setTicks(int ticks) {
		if(ticks < 0) {
			ticks = 0;
		}
		this.ticks = ticks;
	}

}
