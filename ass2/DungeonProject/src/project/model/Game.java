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
	}
	
	
	public void createNewBoard(String name, int height, int width) { 
		Board newBoard = new Board(name, height, width); 
		dungeonCreator.setIntialBoard(newBoard);
		customDungeons.add(newBoard);
	}
	
	public void saveCustomBoard(int customBoardID) {
		for (Board board : customDungeons) {
			if (board.getBoardID() == customBoardID) {
				boardWriter.writeBoard(board);
			}
		}
	}
	
	public void loadAllBoards() {
		boardLoader.setFilePath("src/simpleDungeons");
		boardLoader.loadBoards(simpleDungeons);
		boardLoader.setFilePath("src/advancedDungeons");
		boardLoader.loadBoards(advancedDungeons);
		boardLoader.setFilePath("src/customDungeons");
		boardLoader.loadBoards(customDungeons);
	}
	
}