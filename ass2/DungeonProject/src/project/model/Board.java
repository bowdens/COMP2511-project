package project.model;

public class Board {
	
	private static int boardCount;
	
	private String name;
	private int height;
	private int width;
	private BoardEntity[][] grid;
	private int boardID = 0;
	
	public Board(String name, int height, int width) {
		this.name = name;
		this.height = height;
		this.width = width;
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

	public BoardEntity[][] getGrid() {
		return grid;
	}

	public void setGrid(BoardEntity[][] grid) {
		this.grid = grid;
	}

	public int getBoardID() {
		return boardID;
	}
	
	public int getEntityAt(int x, int y) {
		return getGrid()[y][x].getType().getValue();
	}
	
	public void placeBoardEntity(BoardEntity be) {
		getGrid()[be.getY()][be.getX()] = be;
	}

}
