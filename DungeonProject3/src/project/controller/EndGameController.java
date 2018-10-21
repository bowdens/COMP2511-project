package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EndGameController extends Controller {
	
	private String message;
	
    @FXML
    private Label endGameMessage;

    @FXML
    private Button returnToMain;

	public EndGameController(Stage stage, String message) {
		super(stage);
		this.message = message;
	}
	
	@FXML
	public void initialize() {	
		endGameMessage.setText(message);
	}
	
    @FXML
    public void returnToMain() {
		Screen mainMenuScreen = new Screen(getStage(), "Dungeons - Main Menu", "../view/MainMenuPage.fxml");
		MainMenuController mainMenuController = new MainMenuController(getStage());
		mainMenuScreen.start(mainMenuController);    	
    }

}
