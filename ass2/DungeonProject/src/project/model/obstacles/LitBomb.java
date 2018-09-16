package project.model.obstacles;

import java.util.ArrayList;

import project.model.Board;
import project.model.BoardEntity;
import project.model.Enemy;
import project.model.Player;
import project.model.canMoveOntoDecorators.AllowNone;

public class LitBomb extends BoardEntity {

	private static final long serialVersionUID = 1L;
	private int ticks;
	
	/**
	 * @param ticksTillExploded the number of turns the bomb willt take to explode
	 */
	public LitBomb(int x, int y, int ticksTillExploded) {
		super(x, y);
		setTicks(ticksTillExploded);
		setCanMoveOnto(new AllowNone());
	}
	
	@Override
	public void update(Board board) {
		setTicks(getTicks()-1);
		if(getTicks() <= 0) {
			explode(board);
		}
	}
	
	private void explode(Board board) {
		ArrayList<BoardEntity> entitiesToKill = new ArrayList<BoardEntity>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				// get each object around this
				entitiesToKill.addAll(board.getEntitiesAt(getX() + i, getY() + j));
			}
		}
		// kill player, enemies and boulders
		for (BoardEntity entity : entitiesToKill) {
			if (entity instanceof Player) {
				// if it's a player, end the game
				board.endGame();
			} else if ((entity instanceof Enemy) || (entity instanceof Boulder)) {
				board.removeBoardEntity(entity);
			}
		}
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
