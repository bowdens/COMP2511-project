package project.model;

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
	
	public BoardEntity getEntityAt(int x, int y) {
		for(BoardEntity entity : boardEntities) {
			if(x == entity.getX() && y == entity.getY()) {
				return entity;
			}
		}
		return null;
	}
	
	public void addBoardEntity(BoardEntity be) {
		boardEntities.add(be);
	}
	
	public void removeEntity(BoardEntity be) {
		boardEntities.remove(be);
	}
	
	public void killPlayer() {
		System.out.println("GAME OVER\n");
		//exit(0);
	}
	
	public void winGame() {
		System.out.println("YOU WIN!\n");
		//exit(0);
	}

}
