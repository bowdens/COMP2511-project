package project.controller;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import project.model.Board;
import project.model.Game;

public class LevelSelectController extends Controller {
	
	private ArrayList<Board> boards;
	private int startIndex;
	private String boardType;
	
    @FXML
    private Pane levelsPane;
    
    @FXML
    private GridPane levelsGrid;
    
    @FXML
    private Button backBtn;

    @FXML
    private Button nextBtn;

	public LevelSelectController(Stage stage, Game game, ArrayList<Board> boards, int startIndex, String boardType) {
		super(stage, game);
		this.boards = boards;
		this.startIndex = startIndex;
		this.boardType = boardType;
	}
	
	@FXML
	public void initialize() {
		int remainingLevels = startIndex + 9;
		
		System.out.println(boards);
		System.out.println(getGame().getCurrentBoard());
		System.out.println(boardType);
		
		if (boards.size() <= remainingLevels) {
			remainingLevels = boards.size();
			levelsPane.getChildren().remove(nextBtn);
		}
		
	    ObservableList<Node> children = levelsGrid.getChildren();
	    
	    for (int i = startIndex; i < remainingLevels; i++) {
		    Node node = children.get(i - startIndex);
		    Button levelButton = new Button();
		    String css = getClass().getResource("/project/view/menuButton.css").toExternalForm();
		    System.out.println(css);
		    levelButton.getStylesheets().add(css);
		    levelButton.setText(boards.get(i).getName());
		    levelButton.setOnAction(event -> handleLevelBtn(event));
		    
		    if (node instanceof StackPane){
		        StackPane sp = (StackPane) node;
		        sp.getChildren().add(levelButton);
		    }
	    }
	    
	}
	
    @FXML
    public void handleBackBtn() {
    	if (startIndex == 0) {
			Screen mainMenuScreen = new Screen(getStage(), "Dungeons - Main Menu", "../view/MainMenuPage.fxml");
			MainMenuController mainMenuController = new MainMenuController(getStage());
			mainMenuScreen.start(mainMenuController);
    	} else {
        	Screen prevLevelsScreen = new Screen(getStage(), "Dungeons - Level Select", "../view/LevelSelectPage.fxml");
        	LevelSelectController levelSelectController = new LevelSelectController(getStage(), getGame(), boards, (startIndex - 9), boardType);
        	prevLevelsScreen.start(levelSelectController);   	    		
    	}
    }
    
    @FXML
    public void handleNextBtn() {
    	Screen nextLevelsScreen = new Screen(getStage(), "Dungeons - Level Select", "../view/LevelSelectPage.fxml");
    	LevelSelectController levelSelectController = new LevelSelectController(getStage(), getGame(), boards, (startIndex + 9), boardType);
    	nextLevelsScreen.start(levelSelectController);   	
    }

	public void handleLevelBtn(ActionEvent event) {
		System.out.println("Level button pressed");
		String boardName = ((Button) event.getSource()).getText();
		getGame().startGame(boardType, boardName);
    	Screen gameScreen = new Screen(getStage(), "Dungeons - " + ((Button) event.getSource()).getText(), "../view/GamePage.fxml");
    	GameController gameController = new GameController(getStage(), getGame(), getGame().getCurrentBoard());
    	gameScreen.start(gameController);
		event.consume();
	}

}
