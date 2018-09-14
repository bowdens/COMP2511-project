//package project.model;
package model;

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
