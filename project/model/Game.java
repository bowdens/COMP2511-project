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
   
   //stores all the levels into the different board ArrayLists	
	public void loadAllBoards() {
		boardLoader.setFilePath("src/simpleDungeons");
		boardLoader.loadBoardFromDir(simpleDungeons, dungeonCreator);
		boardLoader.setFilePath("src/advancedDungeons");
		boardLoader.loadBoardFromDir(advancedDungeons, dungeonCreator);
		boardLoader.setFilePath("src/customDungeons");
		boardLoader.loadBoardFromDir(customDungeons, dungeonCreator);
	}
	

}
