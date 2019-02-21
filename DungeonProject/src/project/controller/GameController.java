package project.controller;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import project.model.Board;
import project.model.Game;

public class GameController extends Controller {
	
	Board board;
	
    @FXML
    private Button backBtn;

    @FXML
    private Label boardNameLabel;

    @FXML
    private GridPane gameGrid;

	public GameController(Stage stage, Game game, Board board) {
		super(stage, game);
		this.board = board;
	}
	
	@FXML
	public void initialize() {
		boardNameLabel.setText(board.getName());
		
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				Image floorImage = new Image(getClass().getResource("/floor.png").toString());
				ImageView floorTile = new ImageView();
				StackPane sp = new StackPane();
				
				gameGrid.add(sp, j, i);
				
		        int col = GridPane.getColumnIndex(sp);
		        int row = GridPane.getRowIndex(sp);  
		        ArrayList<String> entityTypes = board.getEntityTypesAt(col, row);
				
				floorTile.setImage(floorImage);				
				sp.getChildren().add(floorTile);
				
		        for (String entityType : entityTypes) {
		        	addEntityImages(sp , entityType);
		        }
			}
		}
	}
	
    @FXML
    public void handleBackBtn() {
		Screen mainMenuScreen = new Screen(getStage(), "Dungeons - Main Menu", "../view/MainMenuPage.fxml");
		MainMenuController mainMenuController = new MainMenuController(getStage());
		mainMenuScreen.start(mainMenuController);
    }
    
    @FXML
    public void playerControls(KeyEvent key) {
		KeyCode code = key.getCode();
		String gameStatus = null;
		
		switch (code) {
			case A:
				if (getGame().getPlayer().fireArrow(board)) {
					updateBoard();
					
					while(board.advanceArrow()) {
						updateBoard();
					}
				}
			case D:
				if (getGame().getPlayer().dropBomb(board)) {
					updateBoard();
				};
			case UP:
				System.out.println("up pressed");
				if (getGame().movePlayer("Up")) {
					gameStatus = getGame().newTurn();
				}
				break;
			case DOWN:
				System.out.println("down pressed");				
				if (getGame().movePlayer("Down")) {
					gameStatus = getGame().newTurn();
				}
				break;
			case LEFT:
				System.out.println("left pressed");					
				if (getGame().movePlayer("Left")) {
					gameStatus = getGame().newTurn();
				}
				break;
			case RIGHT:
				System.out.println("right pressed");					
				if (getGame().movePlayer("Right")) {
					gameStatus = getGame().newTurn();
				}
				break;
			default:
				break;
		}
		
		updateBoard();
		checkGameStatus(gameStatus);
    }
	
	private void checkGameStatus(String gameStatus) {
		if (gameStatus == null) {
			return;
		}
		
		String message = null;
		
		if (gameStatus == "won") {
			message = "Congratulations! You Won!";
		} else if (gameStatus == "lost") {
			message = "Game Over! You Lost!";
		}
		
		Screen endGameScreen = new Screen(getStage(), "Dungeons - End Game", "../view/EndGamePage.fxml");
		EndGameController endGameController = new EndGameController(getStage(), message);
		endGameScreen.start(endGameController);
	}

	private void updateBoard() {
	    ObservableList<Node> children = gameGrid.getChildren();

	    for (Node node : children) {
		    if (node instanceof StackPane) {
				Image floorImage = new Image(getClass().getResource("/floor.png").toString());
				ImageView floorTile = new ImageView();
		        StackPane sp = (StackPane) node;
		        int col = GridPane.getColumnIndex(sp);
		        int row = GridPane.getRowIndex(sp);  
		        ArrayList<String> entityTypes = board.getEntityTypesAt(col, row);
		        
		        sp.getChildren().clear();
				floorTile.setImage(floorImage);				
				sp.getChildren().add(floorTile);
				
				String toTopEntity = null;
				
		        for (String entityType : entityTypes) {
		        	if (entityType.equals("player")) {
		        		toTopEntity = "player";	   
		        	} else if (entityType.equals("boulder")) {
		        		toTopEntity = "boulder";
		        	} else if (entityType.equals("flying arrow")) {
		        		toTopEntity = "flying arrow";	        		
			        } else {
		        		addEntityImages(sp , entityType);
		        	}
		        }
		        
		        if (toTopEntity != null) {
		        	addEntityImages(sp, toTopEntity);
		        }
		    }
	    }
	}

	private void addEntityImages(StackPane sp, String entityType) {
		Image entityImage = null;
		ImageView entityIV = new ImageView();
				
        switch (entityType) {
	        case "player":
	        	entityImage = new Image(getClass().getResource("/player.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);	        	
	            break;
	        case "wall":
	        	entityImage = new Image(getClass().getResource("/wall.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	            break;
	        case "exit":
	        	entityImage = new Image(getClass().getResource("/exit.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	        	break;
	        case "pit":
	        	entityImage = new Image(getClass().getResource("/pit.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	            break;
	        case "boulder":
	        	entityImage = new Image(getClass().getResource("/boulder.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	            break;
	        case "floor switch":
	        	entityImage = new Image(getClass().getResource("/switch.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	        	break;
	        case "open door":
	        	entityImage = new Image(getClass().getResource("/open_door.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	            break;
	        case "closed door":
	        	entityImage = new Image(getClass().getResource("/closed_door.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	            break;	      
	        case "key":
	        	entityImage = new Image(getClass().getResource("/key.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	            break;
	        case "treasure":
	        	entityImage = new Image(getClass().getResource("/treasure.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	            break;
	        case "unlit bomb":
	        	entityImage = new Image(getClass().getResource("/unlit_bomb.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);	        	
	            break;	         
	        case "lit bomb 1":
	        	entityImage = new Image(getClass().getResource("/bomb_lit_1.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);	        	
	            break;
	        case "lit bomb 2":
	        	entityImage = new Image(getClass().getResource("/bomb_lit_2.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);	        	
	            break;
	        case "lit bomb 3":
	        	entityImage = new Image(getClass().getResource("/bomb_lit_3.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);	        	
	            break;
	        case "lit bomb 4":
	        	entityImage = new Image(getClass().getResource("/bomb_lit_4.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);	        	
	            break;	            
	        case "sword":
	        	entityImage = new Image(getClass().getResource("/sword.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);        	
	            break;
	        case "arrow":
	        	entityImage = new Image(getClass().getResource("/arrow.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	            break;
	        case "flying arrow":
	        	entityImage = new Image(getClass().getResource("/flying_arrow.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	            break;	
	        case "hunter":
	        	entityImage = new Image(getClass().getResource("/hunter.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	            break;
	        case "hound":
	        	entityImage = new Image(getClass().getResource("/hound.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	            break;
	        case "strategist":
	        	entityImage = new Image(getClass().getResource("/strategist.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	            break;
	        case "coward":
	        	entityImage = new Image(getClass().getResource("/coward.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	            break;	
	        case "invincibility potion":
	        	entityImage = new Image(getClass().getResource("/invincibility_potion.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);
	            break;	
	        case "hover potion":
	        	entityImage = new Image(getClass().getResource("/hover_potion.png").toString());
	        	entityIV.setImage(entityImage);
	        	sp.getChildren().add(entityIV);	        	
	            break;		            
	        default:
	            break;
        }
	}
}
