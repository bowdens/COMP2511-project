package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import project.model.Game;

public class MainMenuController extends Controller {
	
	private Game newGame;

	@FXML
	private Button simpleDungeonButton;
	
	@FXML
	private Button advancedDungeonButton;
	
	@FXML
	private Button customDungeonButton;
	
	@FXML
	private Button dungeonBuilderButton;
	
	@FXML
	private Button exitGameButton;
	
	public MainMenuController(Stage stage) {
		super(stage);
		this.newGame = new Game();
	}
	
	@FXML
	public void initialize() {
		//newGame.loadAllBoards
	}
	
	@FXML
	public void handleSimpleDungeonButton() {
		System.out.println("Simple Dungeon Button Pressed");
	}
	
	@FXML
	public void handleAdvancedDungeonButton() {
		System.out.println("Advanced Dungeon Button Pressed");
	}
	
	@FXML
	public void handleCustomDungeonButton() {
		System.out.println("Custom Dungeon Button Pressed");
	}
	
	@FXML
	public void handleDungeonBuilderButton() {
		System.out.println("Dungeon Builder Button Pressed");
		Screen dungeonBuilderScreen = new Screen(getStage(), "Dungeons - Dungeon Builder", "../view/DungeonBuilderPage.fxml");
		DungeonBuilderController dungeonBuilderController = new DungeonBuilderController(getStage(), newGame);
		dungeonBuilderScreen.start(dungeonBuilderController);
	}
	
	@FXML
	public void handleExitGameButton() {
		System.out.println("Exit Game Button Pressed");
		System.exit(0);
	}

}
