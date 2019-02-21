package project.model;

import java.util.ArrayList;

public class Game {
	
	private ArrayList<Board> simpleDungeons;
	private ArrayList<Board> advancedDungeons;
	private ArrayList<Board> customDungeons;
	private Board currentBoard;
	private DungeonCreator dungeonCreator;
	private BoardLoader boardLoader;
	private BoardWriter boardWriter;
	private Player player;
	
	public Game() {
		this.simpleDungeons = new ArrayList<Board>();
		this.advancedDungeons = new ArrayList<Board>();
		this.customDungeons = new ArrayList<Board>();
		this.boardLoader = new BoardLoader();
		this.boardWriter = new BoardWriter();
		this.dungeonCreator = new DungeonCreator();
	}
	
	/**
	 * @return the currentBoard
	 */
	public Board getCurrentBoard() {
		return currentBoard;
	}

	/**
	 * @param currentBoard the currentBoard to set
	 */
	public void setCurrentBoard(Board currentBoard) {
		this.currentBoard = currentBoard;
	}

	/**
	 * @return the dungeonCreator
	 */
	public DungeonCreator getDungeonCreator() {
		return dungeonCreator;
	}

	/**
	 * @param dungeonCreator the dungeonCreator to set
	 */
	public void setDungeonCreator(DungeonCreator dungeonCreator) {
		this.dungeonCreator = dungeonCreator;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the simpleDungeons
	 */
	public ArrayList<Board> getSimpleDungeons() {
		return simpleDungeons;
	}

	/**
	 * @return the advancedDungeons
	 */
	public ArrayList<Board> getAdvancedDungeons() {
		return advancedDungeons;
	}

	/**
	 * @return the customDungeons
	 */
	public ArrayList<Board> getCustomDungeons() {
		return customDungeons;
	}

	public boolean boardsEmpty() {
		if (simpleDungeons.isEmpty() 
				&& advancedDungeons.isEmpty() 
				&& customDungeons.isEmpty()) {
			return true;
		}
		
		return false;
	}

	/**
	 * This method creates a new board for the game's creation mode. 
	 * The new board will be added to the game as a custom dungeon.
	 * @param name The name of the new board.
	 * @param height The height of the new board.
	 * @param width The width of the new board.
	 */
	public void createNewBoard(String name, int height, int width) { 
		Board newBoard = new Board(name, height, width); 
		dungeonCreator.setIntialBoard(newBoard);
		customDungeons.add(newBoard);
		setCurrentBoard(newBoard);
		System.out.print(currentBoard);
	}
	
	public boolean checkBoardName(String name) {
		Board board = getCustomDungeonByName(name);
		
		if (board != null) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * This method with a given BoardEntity id and coordinates 
	 * will create a new BoardEntity with the corresponding id and
	 * place it on the current board at the given position.
	 * @param entity An integer id corresponding to desired entity
	 * @param x The column coordinate on the board
	 * @param y The row coordinate on the board
	 */
	public void addEntityToBoard(int entity, int x, int y) {
		dungeonCreator.addBoardEntity(getCurrentBoard(), entity, x, y);
	}
	
	/**
	 * This method given coordinates will remove any
	 * entities in the corresponding board square. 
	 * @param x The column coordinate on the board
	 * @param y The row coordinate on the board
	 */
	public void removeEntitiesAt(int x, int y) {
		ArrayList<BoardEntity> entities = currentBoard.getEntitiesAt(x, y);
		
		for  (BoardEntity entity : entities) {
			currentBoard.removeBoardEntity(entity);
		}
	}
	
	//can change this to get any dungeon by name when we start storing dungeons on disk
	public Board getCustomDungeonByName(String name) {
	   for(Board board : customDungeons) {
	      if(board.getName().equals(name)){
	         return board;
	      }
	   }
	   return null;
	}
	

	//uses dungeonCreator to add an entity (specified by the appropriate enum int) to the current board.
  	
	/**
	 * This method saves the board with corresponding
	 * ID by writing the board to file.
	 * @param customBoardID The ID corresponding to the board
	 */
	public void saveCustomBoard(String boardName) {
		boardWriter.setFilePath("src/customDungeons");
		
		for (Board board : customDungeons) {
			if (board.getName().equals(boardName)) {
				boardWriter.writeBoard(board);
			}
		}
	}
	
	/**
	 * This method loads all saved boards into the game.
	 */
	public void loadAllBoards() {
		loadBoards("src/simpleDungeons", "simple");
		loadBoards("src/advancedDungeons", "advanced");
		loadBoards("src/customDungeons", "custom");		
	}
	
	public void loadBoards(String filepath, String boardType) {
		ArrayList<Board> boards = null;
		
		if (boardType.equals("simple")) {
			boards = simpleDungeons;
		} else if (boardType.equals("advanced")) {
			boards = advancedDungeons;
		} else if (boardType.equals("custom")) {
			boards = customDungeons;
		}
		
		boardLoader.setFilePath(filepath);
		boardLoader.loadBoards(boards);		
	}
	
	
	/**
	 * This method starts the game corresponding to
	 * the given board ID
	 * @param boardType The type of board e.g. simple, advanced, custom
	 * @param boardID A ID corresponding with a given board
	 */
	public void startGame(String boardType, String boardName)  {
		ArrayList<Board> boards = null;
		
		if (boardType.equals("simple")) {
			boards = getSimpleDungeons();
		} else if (boardType.equals("advanced")) {
			boards = getAdvancedDungeons();
		} else if (boardType.equals("custom")) {
			boards = getCustomDungeons();
		}
		
		if (getLevel(boards, boardName)) { 
			setPlayer(currentBoard.getPlayer());
		}
	}
	
	/**
	 * This method gets the board corresponding 
	 * @param boards The list of boards to look through
	 * @param boardID A ID corresponding with a given board
	 * @return True if level is found and false otherwise
	 */
	private boolean getLevel(ArrayList<Board> boards, String boardName) {
		for (Board board : boards) {
			if (board.getName().equals(boardName)) {
				setCurrentBoard(board);
				return true;
			}
		}
		
		return false;
	}

	public boolean movePlayer(String direction) {
		int prevX = player.getX();
		int prevY = player.getY();
		
		switch (direction) {
			case "Up":
				player.moveUp(currentBoard);
				break;
			case "Down":
				player.moveDown(currentBoard);
				break;
			case "Left":
				player.moveLeft(currentBoard);
				break;
			case "Right":
				player.moveRight(currentBoard);
				break;
		}
		
		// If the player has moved return true so that the turn can be played out
		if (player.getX() != prevX || player.getY() != prevY) {
			return true;
		}
		
		return false;
	}
	
	public String newTurn() {
		for (BoardEntity be : currentBoard.getBoardEntities()) {
			be.update(currentBoard);
			
			if (currentBoard.isHasExit()) {
				if (currentBoard.isExitReached()) {
					return "won";
				}
			} else {
				int objectiveCount = currentBoard.getEnemyCount() + currentBoard.getSwitchCount() + currentBoard.getTreasureCount();
				
				if (objectiveCount == 0) {
					return "won";
				}
			}
			
			if (currentBoard.getPlayer() == null || currentBoard.isGameOver()) {
				return "lost";
			}
		}
		
		return null;
	}
	
}
