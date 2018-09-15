package project.model.obstacles;

import project.model.BoardEntity;
import project.model.canMoveOntoDecorators.AllowNone;

public class ExplodingBomb extends BoardEntity {

	private static final long serialVersionUID = -6117469224835281543L;
	private int ticks;
	
	/**
	 * @param ticksTillExploded the number of turns the bomb willt take to explode
	 */
	public ExplodingBomb(int x, int y, int ticksTillExploded) {
		super(x, y);
		setTicks(ticksTillExploded);
		setCanMoveOnto(new AllowNone());
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
