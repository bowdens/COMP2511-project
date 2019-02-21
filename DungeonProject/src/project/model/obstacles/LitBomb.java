package project.model.obstacles;

import java.util.ArrayList;

import project.model.Board;
import project.model.BoardEntity;
import project.model.canMoveOntoDecorators.AllowArrow;
import project.model.canMoveOntoDecorators.AllowNone;

public class LitBomb extends BoardEntity {

	private static final long serialVersionUID = 1L;
	
	private int ticks;
	
	/**
	 * @param ticksTillExploded the number of turns the bomb will take to explode
	 */
	public LitBomb(int x, int y, int ticksTillExploded) {
		super(x, y, "lit bomb 1");
		setTicks(ticksTillExploded);
		setCanMoveOnto(new AllowArrow(new AllowNone()));
	}
	
	@Override
	public void update(Board board) {
		setTicks(getTicks()-1);
		
		if (getTicks() == 3) {
			setEntityType("lit bomb 1");
		} else if (getTicks() == 2) {
			setEntityType("lit bomb 2");
		} else if (getTicks() == 1) {
			setEntityType("lit bomb 3");			
		}
		
		if(getTicks() <= 0) {
			setEntityType("lit bomb 4");
			explode(board);
		}
	}
	
	private void explode(Board board) {
		ArrayList<BoardEntity> entitiesToKill = new ArrayList<BoardEntity>();
		entitiesToKill.addAll(board.getEntitiesAt(getX() + 1, getY()));
		entitiesToKill.addAll(board.getEntitiesAt(getX() - 1, getY()));
		entitiesToKill.addAll(board.getEntitiesAt(getX(), getY() + 1));
		entitiesToKill.addAll(board.getEntitiesAt(getX(), getY() - 1));
		entitiesToKill.addAll(board.getEntitiesAt(getX(), getY()));
		
		System.out.println(entitiesToKill);
		// kill player, enemies and boulders
		for (BoardEntity entity : entitiesToKill) {
			if (entity.getEntityType().equals("player")) {
				// if it's a player, end the game
				board.removeBoardEntity(entity);
				board.setGameOver(true);
			} else if ((entity.isEnemy()) || (entity.getEntityType().equals("boulder")) || (entity.getEntityType().equals("lit bomb"))) {
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
