package project.model;

import java.util.ArrayList;
import project.model.items.Treasure;

public class Board {
	
	private static int boardCount;
	
	private String name;
	private int height;
	private int width;
	private ArrayList<BoardEntity> boardEntities;
	private int boardID = 0;
	
	//currently for testing purposes only
	//0 means in play, 1 means the game has been won, 2 means the game has been lost
	//this value is changed by the winGame and endGame methods
	private int gameStatus;
	
	public Board(String name, int height, int width) {
		this.name = name;
		this.height = height;
		this.width = width;
		this.boardEntities = new ArrayList<BoardEntity>();
		this.boardID = ++boardCount;
		this.gameStatus = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public ArrayList<BoardEntity> getBoardEntities() {
		return boardEntities;
	} 

   //can do a treasure count in the update method to check if the player has won
   public int howMuchTreasureLeft(){
      int treas = 0;
      for(BoardEntity entity : boardEntities){
         if(entity instanceof Treasure){
            treas++;
         }
      }
      return treas;
   }
   
   public int howManyEnemiesLeft(){
      int enems = 0;
      for(BoardEntity entity: boardEntities){
         if(entity instanceof Enemy){
            enems++;
         }
      }
      return enems;
   }
   /*
   public int howManySwitchesLeft() {
	   int switches = 0;
	      for(BoardEntity entity: boardEntities){
	         if(entity instanceof Switch){
	            switches++;
	         }
	      }
	   return switches;
   }*/

	public int getBoardID() {
		return boardID;
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
	
	/**
	 * returns the entities at a given x,y. Returns empty list if there is no entity at that x,y
	 * @param x the x coordinate (left=0)
	 * @param y the y coordinate (up=0)
	 * @return The list of entities at the x,y (empty if there are none)
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
	 * Adds a board entity to the board. Will not add duplicates (that is same entity at the same memory location
	 * @param entity The board entity to add to the list of entities
	 */
	public boolean addBoardEntity(BoardEntity entity) {
		if(!boardEntities.contains(entity)) {
			boardEntities.add(entity);
			return true;
		} 
		return false;
	}

	/**
	 * Removes an existing board entity from the board
	 * @param entity the entity to be removed
	 */
	public void removeBoardEntity(BoardEntity entity) {
		boardEntities.remove(entity);
	}
	
	/**
	 * ends the game
	 */
	public void endGame() {
		this.gameStatus = 2;
	}

	/**
	 * wins the game
	 */
	public void winGame() {
		this.gameStatus = 1;
	}

	public int getGameStatus() {
		return this.gameStatus;
	}
	
	/**
	 * @pre Ensure that the board only has one player
	 * @return the player if there is one, null otherwise
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
	 * checks if an x coordinate is valid on this board
	 * @param x an x coordinate
	 * @return true if 0 <= x < width
	 */
	public boolean validX(int x) {
		return (x >= 0 && x < getWidth());
	}
	
	/**
	 * checks if a y coordinate is valid on this board
	 * @param y a y position
	 * @return true if 0 <= y < height
	 */
	public boolean validY(int y) {
		return (y >= 0 && y < getHeight());
	}

   
   public void updateBoard(){
      //update all the entities
    	for(BoardEntity ent : boardEntities) {
    		  ent.update(this);
      }
   }

}
