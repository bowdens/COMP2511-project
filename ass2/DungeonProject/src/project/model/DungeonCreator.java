package project.model;

public class DungeonCreator {
	
	private DungeonFactory factory;
	

	public DungeonCreator() {
		this.factory = new DungeonFactory();
	}

	
	public void setIntialBoard(Board newBoard) {
		BoardEntity entity;
		for (int i = 0; i < newBoard.getHeight(); i++) {		
			for (int j = 0; j < newBoard.getWidth(); j++) {
				if (i == 0 || i == (newBoard.getHeight() - 1) || 
						j == 0 || j == (newBoard.getWidth() - 1)) {
					entity = factory.makeBoardEntity(1, j, i);
					newBoard.placeBoardEntity(entity); 
				} else {
					entity = factory.makeBoardEntity(2, j, i);
					newBoard.placeBoardEntity(entity); 
				}			
			}
		}
	}
	
	public void setBoardEntity(Board board, int entity, int x, int y) {
		BoardEntity be = factory.makeBoardEntity(entity, x, y);
		board.placeBoardEntity(be);
	}

}
