package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.model.Game;

public class BoardNameController extends Controller {
	
    @FXML
    private TextField boardName;
    
    @FXML
    private Button backBtn;

    @FXML
    private Button gameBtn;

    @FXML
    private Label nameError;

	public BoardNameController(Stage stage, Game game) {
		super(stage, game);
	}


    @FXML
    public void handleGameBtn() {
    	String name = boardName.getText();
    	
    	if (name.isEmpty()) {
    		nameError.setText("No name entered. Please enter name to procceed.");
    		return;
    	} else if (getGame().checkBoardName(name)) {
    		nameError.setText("This name is already taken. Please enter another name to procceed.");
    		return;
    	} else {
        	nameError.setText("");  		
    	}
    	
		Screen dungeonBuilderScreen = new Screen(getStage(), "Dungeons - Dungeon Builder", "../view/DungeonBuilderPage.fxml");
		DungeonBuilderController dungeonBuilderController = new DungeonBuilderController(getStage(), getGame(), name);
		dungeonBuilderScreen.start(dungeonBuilderController);    	
    }
    
    @FXML
    public void handleBackBtn() {
		Screen mainMenuScreen = new Screen(getStage(), "Dungeons - Main Menu", "../view/MainMenuPage.fxml");
		MainMenuController mainMenuController = new MainMenuController(getStage());
		mainMenuScreen.start(mainMenuController);    	
    }
    
}
