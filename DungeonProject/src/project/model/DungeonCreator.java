package project.model;

public class DungeonCreator {
	
	private DungeonFactory factory;
	
	public DungeonCreator() {
		this.factory = new DungeonFactory();
	}

	/**
	 * This method sets the board for a new game. The board
	 * will be a be set with walls surrounding the edges of
	 * the board.
	 * @param newBoard - a new board for the game
	 */
	public void setIntialBoard(Board newBoard) {
		BoardEntity entity;
		
		for (int row = 0; row < newBoard.getHeight(); row++) {		
			for (int col = 0; col < newBoard.getWidth(); col++) {
				if (row == 0 || row == (newBoard.getHeight() - 1) || 
						col == 0 || col == (newBoard.getWidth() - 1)) {
					entity = factory.makeBoardEntity(EntityType.WALL.getValue(), col, row);
					newBoard.addBoardEntity(entity);
				}	
			}
		}
	}

	/**
	 * This method places the requested board entity at the
	 * requested location on the board.
	 * @param board - the board to modify
	 * @param entity - the entity to set
	 * @param x - the column coordinate to place the entity  
	 * @param y - the row coordinate to place the entity
	 */
	public void addBoardEntity(Board board, int entity, int x, int y) {
		BoardEntity be = factory.makeBoardEntity(entity, x, y);
		board.addBoardEntity(be);
	}

}
