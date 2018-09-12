package project.model;

public class DungeonCreator {
	
	private DungeonFactory factory;
	

	public DungeonCreator() {
		this.factory = new DungeonFactory();
	}

	//initially we create all floor type entities and add them to the board
   //however, we place walls around the perimeter
	public void setIntialBoard(Board newBoard) {
		BoardEntity entity;
		for (int i = 0; i < newBoard.getHeight(); i++) {		
			for (int j = 0; j < newBoard.getWidth(); j++) {
				if (i == 0 || i == (newBoard.getHeight() - 1) || 
						j == 0 || j == (newBoard.getWidth() - 1)) {
					//adds wall to the peremeter
					entity = factory.makeBoardEntity(2, j, i);
					//placeboardEntity will probably now just add the entity to the array list in board
					newBoard.placeBoardEntity(entity); 
				//might not even treat the floor as an entity anymore
				} else {
				   //add floor to anywhere else
					entity = factory.makeBoardEntity(1, j, i);
					newBoard.placeBoardEntity(entity); 
				}			
			}
		}
	}
	
	//will be called when the designer is adding entities to their board
	public void setBoardEntity(Board board, int entity, int x, int y) {
		BoardEntity be = factory.makeBoardEntity(entity, x, y);
		board.placeBoardEntity(be);
	}

}
