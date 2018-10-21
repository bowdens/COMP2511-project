package project.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Board implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int height;
	private int width;
	private ArrayList<BoardEntity> boardEntities;
	private int switchCount;
	private int enemyCount;
	private int treasureCount;
	private boolean exitReached;
	private boolean hasExit;
	private boolean gameOver;
	
	public Board(String name, int height, int width) {
		this.name = name;
		this.height = height;
		this.width = width;
		this.boardEntities = new ArrayList<BoardEntity>();
		this.switchCount = 0;
		this.enemyCount = 0;
		this.treasureCount = 0;
		this.exitReached = false;
		this.hasExit = false;
		this.gameOver = false;
	}

	/**
	 * @return the switchCount
	 */
	public int getSwitchCount() {
		return switchCount;
	}

	/**
	 * @param switchCount the switchCount to set
	 */
	public void setSwitchCount(int switchCount) {
		this.switchCount = switchCount;
	}

	/**
	 * @return the enemyCount
	 */
	public int getEnemyCount() {
		return enemyCount;
	}

	/**
	 * @param enemyCount the enemyCount to set
	 */
	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}

	/**
	 * @return the treasureCount
	 */
	public int getTreasureCount() {
		return treasureCount;
	}

	/**
	 * @param treasureCount the treasureCount to set
	 */
	public void setTreasureCount(int treasureCount) {
		this.treasureCount = treasureCount;
	}

	/**
	 * @return the exitReached
	 */
	public boolean isExitReached() {
		return exitReached;
	}

	/**
	 * @param exitReached the exitReached to set
	 */
	public void setExitReached(boolean exitReached) {
		this.exitReached = exitReached;
	}

	/**
	 * @return the hasExit
	 */
	public boolean isHasExit() {
		return hasExit;
	}

	/**
	 * @param hasExit the hasExit to set
	 */
	public void setHasExit(boolean hasExit) {
		this.hasExit = hasExit;
	}

	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the boardEntities
	 */
	public ArrayList<BoardEntity> getBoardEntities() {
		return boardEntities;
	}

	/**
	 * This method returns the player from the board
	 * @return the player if there is one and null otherwise
	 */
	public Player getPlayer() {
		for (BoardEntity entity : getBoardEntities()) {
			if (entity instanceof Player) {
				return (Player)entity;
			}
		}
		return null;
	}
	
	/**
	 * This method returns all entities at the given coordinates.
	 * @param x  - the column coordinate
	 * @param y - the row coordinate
	 * @return a list of BoardEntity Objects at the given coordinates
	 */
	public ArrayList<BoardEntity> getEntitiesAt(int x, int y) {
		ArrayList<BoardEntity> entities = new ArrayList<BoardEntity>();
		for(BoardEntity entity : getBoardEntities()) {
			if(x == entity.getX() && y == entity.getY()) {
				entities.add(entity);
			}
		}
		return entities;
	}
	
	/**
	 * This method returns a list of strings representing
	 * the type of entity at the given coordinates.
	 * @param x - the column coordinate
	 * @param y - the row coordinate
	 * @return a list of strings with the types of entities
	 * at the given coordinates
	 */
	public ArrayList<String> getEntityTypesAt(int x, int y) {
		ArrayList<String> entityTypes = new ArrayList<String>();
		ArrayList<BoardEntity> entities = getEntitiesAt(x, y);
		
		for (BoardEntity entity : entities) {
			entityTypes.add(entity.getEntityType());
		}
		
		return entityTypes;
	}
	
	/**
	 * This method adds the given BoardEntity to the board.
	 * @param entity - the BoardEntity to be added
	 */
	public void addBoardEntity(BoardEntity entity) {
		if (entity.getEntityType().equals("exit")) {
			setHasExit(true);
		} else if (entity.isEnemy()) {
			setEnemyCount(getEnemyCount() + 1);
		} else if (entity.getEntityType().equals("floor switch")) {
			setSwitchCount(getSwitchCount() + 1);
		} else if (entity.getEntityType().equals("treasure")) {
			setTreasureCount(getTreasureCount() + 1);
		}
		
		boardEntities.add(entity);
	}

	/**
	 * This method removes an existing board entity from the board.
	 * @param entity - the BoardEntity to be removed
	 */
	public void removeBoardEntity(BoardEntity entity) {
		boardEntities.remove(entity);
	}
	
	/**
	 * This method checks if the given x coordinate is valid on this board
	 * @param x - the row coordinate
	 * @return true if 0 <= x < width
	 */
	public boolean validX(int x) {
		return (x >= 0 && x < getWidth());
	}
	
	/**
	 * This method checks if the given y coordinate is valid on this board
	 * @param y - the column coordinate
	 * @return true if 0 <= y < height
	 */
	public boolean validY(int y) {
		return (y >= 0 && y < getHeight());
	}
	
	public boolean canMoveOnto(BoardEntity entity, int x, int y) {
		ArrayList<BoardEntity> entities = getEntitiesAt(x, y);
		for (BoardEntity otherEntity : entities) {
			// return false if the other entity is not the same as this entity and it cannot be moved onto
			if (entity != otherEntity && !otherEntity.canMoveOnto(this, entity)) {
				return false;
			}
		}
		return true;
	}
   
   public void updateBoard() {
    	for(BoardEntity entity : boardEntities) {
    		  entity.update(this);
      }
   }

public boolean advanceArrow() {
	for(BoardEntity be : boardEntities) {
		if(be.getEntityType().equals("flying arrow")) {
			be.update(this);
			return true;
		}
	}
	
	return false;
}

}
