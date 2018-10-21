package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import project.model.Game;

public class MainMenuController extends Controller {

	@FXML
	private Button simpleDungeonBtn;
	
	@FXML
	private Button advancedDungeonBtn;
	
	@FXML
	private Button customDungeonBtn;
	
	@FXML
	private Button dungeonBuilderBtn;
	
	@FXML
	private Button exitGameBtn;
	
	public MainMenuController(Stage stage) {
		super(stage, new Game());
	}
	
	@FXML
	public void initialize() {	
		if (getGame().boardsEmpty()) {
			getGame().loadAllBoards();
		}
	}
	
	@FXML
	public void handleSimpleDungeonBtn() {
		System.out.println("Simple Dungeon Button Pressed");
    	Screen levelsScreen = new Screen(getStage(), "Dungeons - Level Select", "../view/LevelSelectPage.fxml");
    	LevelSelectController levelSelectController = new LevelSelectController(getStage(), getGame(), getGame().getSimpleDungeons(), 0, "simple");
    	levelsScreen.start(levelSelectController);
	}
	
	@FXML
	public void handleAdvancedDungeonBtn() {
		System.out.println("Advanced Dungeon Button Pressed");
    	Screen levelsScreen = new Screen(getStage(), "Dungeons - Level Select", "../view/LevelSelectPage.fxml");
    	LevelSelectController levelSelectController = new LevelSelectController(getStage(), getGame(), getGame().getAdvancedDungeons(),0, "advanced");
    	levelsScreen.start(levelSelectController);
	}
	
	@FXML
	public void handleCustomDungeonBtn() {
		System.out.println("Custom Dungeon Button Pressed");
    	Screen levelsScreen = new Screen(getStage(), "Dungeons - Level Select", "../view/LevelSelectPage.fxml");
    	LevelSelectController levelSelectController = new LevelSelectController(getStage(), getGame(), getGame().getCustomDungeons(), 0, "custom");
    	levelsScreen.start(levelSelectController);
	}
	
	@FXML
	public void handleDungeonBuilderBtn() {
		System.out.println("Dungeon Builder Button Pressed");
		Screen boardNameScreen = new Screen(getStage(), "Dungeons - Board Name", "../view/BoardNamePage.fxml");
		BoardNameController boardNameController = new BoardNameController(getStage(), getGame());
		boardNameScreen.start(boardNameController);
	}
	
	@FXML
	public void handleExitGameBtn() {
		System.out.println("Exit Game Button Pressed");
		System.exit(0);
	}

}
