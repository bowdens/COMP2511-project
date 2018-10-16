package project.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
	
	private Game game;
	private String draggedSprite;

	@FXML
	private GridPane boardGridPane;
	
	@FXML
	private ImageView binIcon;
	
	@FXML
	private ImageView playerSprite;
	
	@FXML
	private ImageView wallSprite;
	
	@FXML
	private ImageView exitSprite;
	
	public DungeonBuilderController(Stage stage, Game game) {
		super(stage);
		this.game = game;
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
	    game.removeEntitiesAt(col.intValue(), row.intValue());
	}

	private void addEntity(ImageView iv, StackPane sp) {
		Integer col = GridPane.getColumnIndex(sp);
		Integer row = GridPane.getRowIndex(sp);
		int entityNumber = -1;
		
		// Check to make sure only one player is placed
		if (draggedSprite == "player") { 
			Player player = game.getCurrentBoard().getPlayer();
			
			if (player != null) {
				return;
			}
		}
		
		sp.getChildren().add(iv);
		System.out.println(col.intValue() + " " + row.intValue());
		
        switch (draggedSprite) {
	        case "player":
	        	System.out.println("Player");
	        	entityNumber = 4;
	            break;
	        case "wall":
	        	System.out.println("Wall");
	        	entityNumber = 2;
	            break;
	        case "exit":
	        	System.out.println("Exit");
	        	entityNumber = 3;
	            break;	            
	        default:
	            break;
        }
        
        if (entityNumber >= 0) {
        	game.addEntityToBoard(entityNumber, col.intValue(), row.intValue());
        }
	}

	@FXML
	public void initialize() {
		Image floorImage = new Image(getClass().getResource("/floor.png").toString());
		Image wallImage = new Image(getClass().getResource("/wall.png").toString());
	    ObservableList<Node> childrens = boardGridPane.getChildren();

	    for (Node node : childrens) {
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
		
		// place holder till I make the screen or pop up to enter board name
		game.createNewBoard("new_game", 17, 17);
	}
	
    @FXML
    public void dragDetectedPlayer(MouseEvent event) {
    	setDraggedSprite("player");
        dragDetected(event, playerSprite);
    }
    
    @FXML
    public void dragDetectedWall(MouseEvent event) {
    	setDraggedSprite("wall");
        dragDetected(event, wallSprite);
    }
    
    @FXML
    public void dragDetectedExit(MouseEvent event) {
    	setDraggedSprite("exit");
        dragDetected(event, exitSprite);
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
    
}