//package project.model;
package model;

public class DungeonCreator {
	
	private DungeonFactory factory;
	
	public DungeonCreator() {
		this.factory = new DungeonFactory();
	}

	/**
	 * This method sets the board for a new game. The board
	 * will be a be set with walls surrounding the edges of
	 * the board.
	 * @param newBoard A new board for the game.
	 */
	public void setIntialBoard(Board newBoard) {
		BoardEntity entity;
		for (int row = 0; row < newBoard.getHeight(); row++) {		
			for (int col = 0; col < newBoard.getWidth(); col++) {
				if (row == 0 || row == (newBoard.getHeight() - 1) || 
						col == 0 || col == (newBoard.getWidth() - 1)) {
					entity = factory.makeBoardEntity(1, col, row);
					newBoard.addBoardEntity(entity);
				}	
			}
		}
	}

	/**
	 * This method places the requested board entity at the
	 * requested location on the board.
	 * @param board The board to modify
	 * @param entity The entity to set
	 * @param x The column coordinate to place the entity  
	 * @param y The row coordinate to place the entity
	 */
	public void setBoardEntity(Board board, int entity, int x, int y) {
		BoardEntity be = factory.makeBoardEntity(entity, x, y);
		board.addBoardEntity(be);
	}
	
	/**
	 * This method removes any board entity at the request
	 * location.
	 * @param board The board to modify
	 * @param x The column coordinate to remove the entity
	 * @param y The row coordinate to remove the entity.
	 */
	public void deleteBoardEntity(Board board, int x, int y) {
		BoardEntity be = board.getEntityAt(x, y);
		board.removeBoardEntity(be);
	}

}
