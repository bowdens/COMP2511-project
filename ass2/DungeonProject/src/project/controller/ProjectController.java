package project.controller;
import project.model.Game;

public class ProjectController {
	
	private Game game;
	
	public ProjectController() {
		this.game = new Game();
	}

	public Game getGame() {
		return game;
	}

	public void start() { 
		getGame().loadAllBoards();
	}

}
