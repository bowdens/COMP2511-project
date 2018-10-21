package project.model;

import java.util.ArrayList;

public class Game {
	
	private ArrayList<Board> simpleDungeons;
	private ArrayList<Board> advancedDungeons;
	private ArrayList<Board> customDungeons;
	private Board currentBoard;
	private DungeonCreator dungeonCreator;
	private GamePlayer gamePlayer;
	private BoardLoader boardLoader;
	private BoardWriter boardWriter;
	
	public Game() {
		this.simpleDungeons = new ArrayList<Board>();
		this.advancedDungeons = new ArrayList<Board>();
		this.customDungeons = new ArrayList<Board>();
		this.dungeonCreator = new DungeonCreator();
	}
	
	/**
	 * @return the simpleDungeons
	 */
	public ArrayList<Board> getSimpleDungeons() {
		return simpleDungeons;
	}

	/**
	 * @param simpleDungeons the simpleDungeons to set
	 */
	public void setSimpleDungeons(ArrayList<Board> simpleDungeons) {
		this.simpleDungeons = simpleDungeons;
	}

	/**
	 * @return the advancedDungeons
	 */
	public ArrayList<Board> getAdvancedDungeons() {
		return advancedDungeons;
	}

	/**
	 * @param advancedDungeons the advancedDungeons to set
	 */
	public void setAdvancedDungeons(ArrayList<Board> advancedDungeons) {
		this.advancedDungeons = advancedDungeons;
	}

	/**
	 * @return the customDungeons
	 */
	public ArrayList<Board> getCustomDungeons() {
		return customDungeons;
	}

	/**
	 * @param customDungeons the customDungeons to set
	 */
	public void setCustomDungeons(ArrayList<Board> customDungeons) {
		this.customDungeons = customDungeons;
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
	 * @return the gamePlayer
	 */
	public GamePlayer getGamePlayer() {
		return gamePlayer;
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
		dungeonCreator.setBoardEntity(getCurrentBoard(), entity, x, y);
	}
	
	/**
	 * This method given coordinates will remove any
	 * entities in the corresponding board square. 
	 * @param x The column coordinate on the board
	 * @param y The row coordinate on the board
	 */
	public void removeEntitiesAt(int x, int y) {
		dungeonCreator.deleteBoardEntities(currentBoard, x, y);
	}
	
	//can change this to get any dungeon by name when we start storing dungeons on disk
	public Board getCustomDungeonByName(String name){
	   for(Board board : customDungeons){
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
	public void saveCustomBoard(int customBoardID) {
		for (Board board : customDungeons) {
			if (board.getBoardID() == customBoardID) {
				boardWriter.writeBoard(board);
			}
		}
	}
	
	/**
	 * This method loads all saved boards into the game.
	 */
	public void loadAllBoards() {
		boardLoader.setFilePath("src/simpleDungeons");
		boardLoader.loadBoards(simpleDungeons);
		boardLoader.setFilePath("src/advancedDungeons");
		boardLoader.loadBoards(advancedDungeons);
		boardLoader.setFilePath("src/customDungeons");
		boardLoader.loadBoards(customDungeons);
	}
	
	
	/**
	 * This method starts the game corresponding to
	 * the given board ID
	 * @param boardType The type of board e.g. simple, advanced, custom
	 * @param boardID A ID corresponding with a given board
	 */
	public void startGame(String boardType, int boardID)  {
		ArrayList<Board> boards = null;
		
		if (boardType == "simple") {
			boards = getSimpleDungeons();
		} else if (boardType == "advanced") {
			boards = getAdvancedDungeons();
		} else if (boardType == "custom") {
			boards = getCustomDungeons();
		}
		
		if (getLevel(boards, boardID)) { 
			gamePlayer = new GamePlayer(currentBoard);
		}
	}
	
	
	/**
	 * This method gets the board corresponding 
	 * @param boards The list of boards to look through
	 * @param boardID A ID corresponding with a given board
	 * @return True if level is found and false otherwise
	 */
	private boolean getLevel(ArrayList<Board> boards, int boardID) {
		for (Board board : simpleDungeons) {
			if (board.getBoardID() == boardID) {
				setCurrentBoard(board);
				return true;
			}
		}
		
		return false;
	}
	
	
	public boolean shootArrow() {
		return gamePlayer.shootArrow();
	}
	
	public void dropBomb() {
		gamePlayer.dropBomb();
	}

	public boolean movePlayer(String direction) {
		return gamePlayer.movePlayer(direction);
	}

	public void newTurn() {
		//checks if any of the game's objectives have been met
		if((currentBoard.howManyEnemiesLeft() == 0) && (gamePlayer.getNumEnemyObjectives() != -1)) {
			currentBoard.winGame();
			return;
		}
		if((currentBoard.howMuchTreasureLeft() == 0) && (gamePlayer.getNumTreasureObjectives() != -1)) {
			currentBoard.winGame();
			return;
		}
		/*
		if((currentBoard.howManySwitchesLeft() == 0) && (gamePlayer.getNumSwitchObjectives() != -1)) {
			currentBoard.winGame();
			return;
		}*/
		currentBoard.updateBoard();
	}


	
}
