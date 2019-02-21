package project.controller;

import javafx.stage.Stage;
import project.model.Game;

public abstract class Controller {

	private Stage stage;
	private Game game;

    public Controller(Stage stage) {
        this.stage = stage;
    }
    
    public Controller(Stage stage, Game game) {
        this.stage = stage;
        this.game = game;
    }

	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * @param stage the stage to set
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}
   

}