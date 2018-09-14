//package project.model;
package model;

import java.util.ArrayList;

public class Board {
	
	private static int boardCount;
	
	private String name;
	private int height;
	private int width;
	private ArrayList<BoardEntity> boardEntities;
	private int boardID = 0;
	
	public Board(String name, int height, int width) {
		this.name = name;
		this.height = height;
		this.width = width;
		this.boardEntities = new ArrayList<BoardEntity>();
		this.boardID = ++boardCount;
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

	public int getBoardID() {
		return boardID;
	}
	
	/**
	 * returns the entity at a given x,y. Returns null if there is no entity at that x,y
	 * @param x the x coordinate (left=0)
	 * @param y the y coordinate (up=0)
	 * @return null if none exists, the entity otherwise
	 */
	public BoardEntity getEntityAt(int x, int y) {
		for(BoardEntity entity : boardEntities) {
			if(x == entity.getX() && y == entity.getY()) {
				return entity;
			}
		}
		return null;
	}
	
	/**
	 * Adds a board entity to the board. Will not add duplicates (that is same entity at the same memory location
	 * @param entity The board entity to add to the list of entities
	 */
	public void addBoardEntity(BoardEntity entity) {
		if(!boardEntities.contains(entity)) {
			boardEntities.add(entity);
		}
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
		// TODO end the game
	}

	/**
	 * wins the game
	 */
	public void winGame() {
		// TODO win the game
	}

}
