package project.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import project.model.Game;
import project.model.Player;

public class DungeonBuilderController extends Controller {
	
	private String draggedSprite;
	private String boardName;

	@FXML
	private GridPane boardGridPane;
	
    @FXML
    private Button backBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Label boardNameLabel;

    @FXML
    private Label saveMessage;
	
	@FXML
	private ImageView binIcon;

    @FXML
    private ImageView playerSprite;

    @FXML
    private ImageView wallSprite;

    @FXML
    private ImageView exitSprite;

    @FXML
    private ImageView pitSprite;

    @FXML
    private ImageView boulderSprite;

    @FXML
    private ImageView switchSprite;

    @FXML
    private ImageView doorSprite;

    @FXML
    private ImageView keySprite;

    @FXML
    private ImageView treasureSprite;

    @FXML
    private ImageView bombSprite;

    @FXML
    private ImageView swordSprite;

    @FXML
    private ImageView arrowSprite;

    @FXML
    private ImageView hunterSprite;

    @FXML
    private ImageView houndSprite;

    @FXML
    private ImageView strategistSprite;

    @FXML
    private ImageView cowardSprite;

    @FXML
    private ImageView ipSprite;

    @FXML
    private ImageView hpSprite;
	
	public DungeonBuilderController(Stage stage, Game game, String boardName) {
		super(stage, game);
		this.boardName = boardName;
	}
	
	/**
	 * @return the draggedSprite
	 */
	public String getDraggedSprite() {
		return draggedSprite;
	}

	/**
	 * @param draggedSprite the draggedSprite to set
	 */
	public void setDraggedSprite(String draggedSprite) {
		this.draggedSprite = draggedSprite;
	}
	
	private void dragDetected(MouseEvent event, ImageView sprite) {
	    Dragboard db = sprite.startDragAndDrop(TransferMode.ANY);
	    ClipboardContent cb = new ClipboardContent();
	    cb.putImage(sprite.getImage());
	    db.setContent(cb);	    
	    event.consume();
	}
	
	private void dragPlacedItem(MouseEvent event, ImageView sprite) {
	    dragDetected(event, sprite);
	    StackPane sp = (StackPane) sprite.getParent();
		Integer col = GridPane.getColumnIndex(sp);
		Integer row = GridPane.getRowIndex(sp);
		
	    sp.getChildren().remove(sprite);
	    getGame().removeEntitiesAt(col.intValue(), row.intValue());
	}

	private void addEntity(ImageView iv, StackPane sp) {
		Integer col = GridPane.getColumnIndex(sp);
		Integer row = GridPane.getRowIndex(sp);
		int entityNumber = 0;
		
		// Check to make sure only one player is placed
		if (draggedSprite == "player") { 
			Player player = getGame().getCurrentBoard().getPlayer();
			
			if (player != null) {
				return;
			}
		}
		
		sp.getChildren().add(iv);
		System.out.println(col.intValue() + " " + row.intValue());
		
        switch (draggedSprite) {
	        case "player":
	        	entityNumber = 1;
	            break;
	        case "wall":
	        	entityNumber = 2;
	            break;
	        case "exit":
	        	entityNumber = 3;
	        	break;
	        case "pit":
	        	entityNumber = 4;
	            break;
	        case "boulder":
	        	entityNumber = 5;
	            break;
	        case "floor switch":
	        	entityNumber = 6;
	        	break;
	        case "door":
	        	entityNumber = 7;
	            break;
	        case "key":
	        	entityNumber = 8;
	            break;
	        case "treasure":
	        	entityNumber = 9;
	            break;
	        case "unlit bomb":
	        	entityNumber = 10;
	            break;
	        case "sword":
	        	entityNumber = 11;
	            break;
	        case "arrow":
	        	entityNumber = 12;
	            break;		 	            
	        case "hunter":
	        	entityNumber = 13;
	            break;
	        case "hound":
	        	entityNumber = 14;
	            break;
	        case "strategist":
	        	entityNumber = 15;
	            break;
	        case "coward":
	        	entityNumber = 16;
	            break;	
	        case "invincibility potion":
	        	entityNumber = 17;
	            break;	
	        case "hover potion":
	        	entityNumber = 18;
	            break;		            
	        default:
	            break;
        }
        
        if (entityNumber > 0) {
        	getGame().addEntityToBoard(entityNumber, col.intValue(), row.intValue());
        }
	}

	@FXML
	public void initialize() {
		Image floorImage = new Image(getClass().getResource("/floor.png").toString());
		Image wallImage = new Image(getClass().getResource("/wall.png").toString());
	    ObservableList<Node> children = boardGridPane.getChildren();

	    for (Node node : children) {
			ImageView floorTile = new ImageView();
			floorTile.setImage(floorImage);
			
		    if (node instanceof StackPane){
		        StackPane sp = (StackPane) node;
		        sp.getChildren().add(floorTile);
		    }
	    }
		
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				ImageView wallTile = new ImageView();
				wallTile.setImage(wallImage);
				
				if (i == 0 || j == 0 || i == 16 || j == 16) {
					boardGridPane.add(wallTile, j, i);
				}
			}
		}
		
		boardNameLabel.setText(boardName);
		getGame().createNewBoard(boardName, 17, 17);
	}
	
	@FXML
	public void handleSaveBtn() {
		getGame().saveCustomBoard(boardName);
		saveMessage.setText("Board saved");
	}
	
	@FXML
	public void handleBackBtn() {
		Screen mainMenuScreen = new Screen(getStage(), "Dungeons - Main Menu", "../view/MainMenuPage.fxml");
		MainMenuController mainMenuController = new MainMenuController(getStage());
		mainMenuScreen.start(mainMenuController); 		
	}
    
    @FXML
    public void dragOver(DragEvent event) {
    	StackPane sp = (StackPane) event.getSource();
    	int size = sp.getChildren().size();
    	
    	if (size == 1 && event.getDragboard().hasImage()) {
    		event.acceptTransferModes(TransferMode.ANY);
    	}
    	
    	event.consume();
    }
    
    @FXML
    public void dragDropped(DragEvent event) {
    	StackPane sp = (StackPane) event.getSource();
    	System.out.println("Drag Dropped");
    	ImageView iv = new ImageView(event.getDragboard().getImage());
    	int size = sp.getChildren().size();
    	
    	if (size == 1 && event.getDragboard().hasImage()) {
    		addEntity(iv, sp);
    		iv.setOnDragDetected(newEvent -> dragPlacedItem(newEvent, iv));
    	}
    	
    	event.consume();
    }
    
    @FXML 
    public void dragOverBin(DragEvent event) {
    	if (event.getDragboard().hasImage()) {
    		event.acceptTransferModes(TransferMode.ANY);
    	}
    }
    
    @FXML 
    public void dragDroppedBin(DragEvent event) {
    	event.consume();   	
    }
    
    @FXML
    public void dragDetectedArrow(MouseEvent event) {
    	setDraggedSprite("arrow");
    	dragDetected(event, arrowSprite);
    }

    @FXML
    public void dragDetectedBomb(MouseEvent event) {
    	setDraggedSprite("unlit bomb");
    	dragDetected(event, bombSprite);
    }

    @FXML
    public void dragDetectedBoulder(MouseEvent event) {
    	setDraggedSprite("boulder");
    	dragDetected(event, boulderSprite);
    }

    @FXML
    public void dragDetectedCoward(MouseEvent event) {
    	setDraggedSprite("coward");
    	dragDetected(event, cowardSprite);
    }

    @FXML
    public void dragDetectedDoor(MouseEvent event) {
    	setDraggedSprite("door");
    	dragDetected(event, doorSprite);
    }

    @FXML
    public void dragDetectedExit(MouseEvent event) {
    	setDraggedSprite("exit");
    	dragDetected(event, exitSprite);
    }

    @FXML
    public void dragDetectedHound(MouseEvent event) {
    	setDraggedSprite("hound");
    	dragDetected(event, houndSprite);
    }

    @FXML
    public void dragDetectedHp(MouseEvent event) {
    	setDraggedSprite("hover potion");
    	dragDetected(event, hpSprite);
    }

    @FXML
    public void dragDetectedHunter(MouseEvent event) {
    	setDraggedSprite("hunter");
    	dragDetected(event, hunterSprite);
    }

    @FXML
    public void dragDetectedIp(MouseEvent event) {
    	setDraggedSprite("invincibility potion");
    	dragDetected(event, ipSprite);
    }

    @FXML
    public void dragDetectedKey(MouseEvent event) {
    	setDraggedSprite("key");
    	dragDetected(event, keySprite);
    }

    @FXML
    public void dragDetectedPit(MouseEvent event) {
    	setDraggedSprite("pit");
    	dragDetected(event, pitSprite);
    }

    @FXML
    public void dragDetectedPlayer(MouseEvent event) {
    	setDraggedSprite("player");
    	dragDetected(event, playerSprite);
    }

    @FXML
    public void dragDetectedStrat(MouseEvent event) {
    	setDraggedSprite("strategist");
    	dragDetected(event, strategistSprite);
    }

    @FXML
    public void dragDetectedSwitch(MouseEvent event) {
    	setDraggedSprite("floor switch");
    	dragDetected(event, switchSprite);
    }

    @FXML
    void dragDetectedSword(MouseEvent event) {
    	setDraggedSprite("sword");
    	dragDetected(event, swordSprite);
    }

    @FXML
    void dragDetectedTreasure(MouseEvent event) {
    	setDraggedSprite("treasure");
    	dragDetected(event, treasureSprite);
    }

    @FXML
    void dragDetectedWall(MouseEvent event) {
    	setDraggedSprite("wall");
    	dragDetected(event, wallSprite);
    }
    
}