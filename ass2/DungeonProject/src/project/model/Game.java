package project.model;

import java.util.ArrayList;

public class Game {
	
	private ArrayList<Board> simpleDungeons;
	private ArrayList<Board> advancedDungeons;
	private ArrayList<Board> customDungeons;
	private DungeonCreator dungeonCreator;
	//private GamePlayer gamePlayer; // Not implemented yet
	private BoardLoader boardLoader;
	private BoardWriter boardWriter;
	
	public Game() {
		this.simpleDungeons = new ArrayList<Board>();
		this.advancedDungeons = new ArrayList<Board>();
		this.customDungeons = new ArrayList<Board>();
		this.dungeonCreator = new DungeonCreator();
	}
	
	/**
	 * This method creates a new board for the game's
	 * creation mode. The new board will be added to the
	 * game as a custom dungeon.
	 * @param name The name of the new board.
	 * @param height The height of the new board.
	 * @param width The width of the new board.
	 */
	public void createNewBoard(String name, int height, int width) { 
		Board newBoard = new Board(name, height, width); 
		dungeonCreator.setIntialBoard(newBoard);
		customDungeons.add(newBoard);
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
	
	//uses dungeonCreator to add an entity (specified by the appropriate enum int) to the board (specified by a String)
	public void addEntityToBoard(String boardName, int entity, int x, int y){
	   Board b = getCustomDungeonByName(boardName);
	   dungeonCreator.setBoardEntity(b, entity, x, y);
	}
	
	
	/**
	 * This method saves the board with corresponding
	 * ID by writing the board to file.
	 * @param customBoardID The ID corresponding to the board.
	 */
	public void saveCustomBoard(int customBoardID) {
		for (Board board : customDungeons) {
			if (board.getBoardID() == customBoardID) {
				boardWriter.writeBoard(board);
			}
		}
	}
	
	/**
	 * This method loads all boards from their directories.
	 */
	public void loadAllBoards() {
		boardLoader.setFilePath("src/simpleDungeons");
		boardLoader.loadBoards(simpleDungeons);
		boardLoader.setFilePath("src/advancedDungeons");
		boardLoader.loadBoards(advancedDungeons);
		boardLoader.setFilePath("src/customDungeons");
		boardLoader.loadBoards(customDungeons);
	}
	
}
