
public class Player implements GameObject {
    private Sprite s; // sprite representing player
    private Animation a = null;
    private int dx; // speed of the player

    private Frame playerFrame;
    private Frame collisionCheck;

    private Direction d = Direction.DOWN;
    private boolean moved = false;

    private final int collisionOffsetX = 20;
    private final int collisionOffsetY = 50;

    private int completedGoals = 0;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT 
    }     
 
    Player(Sprite s, int xScale, int yScale, int playerPosX, int playerPosY) {
        this.s = s;
        // check if sprite is animated then sets it
        if(s instanceof Animation)
            this.a = (Animation)this.s;
        this.playerFrame = new Frame(playerPosX*16*4, playerPosY*16*4, 25, 30);
        this.playerFrame.generateFrameGraphics(0xFFF4DF42, 1);
        this.dx = 5;
        this.collisionCheck = new Frame(0, 0 , 10*xScale, 50*yScale); 
//        this.collisionCheck.generateFrameGraphics(0xFFF4DF42, 1);
    }

    public void render(ImageRenderer screen, int xScale, int yScale) {
        // if we are rendering an animation
        if (this.a != null && this.moved) {
            screen.renderSprite(this.a, this.playerFrame.getX(), this.playerFrame.getY(), xScale, yScale);
        // if we are in the starting position
        }else if(this.a != null && !this.moved){
            screen.renderSprite(this.a.sprites[0], this.playerFrame.getX(), this.playerFrame.getY(), xScale, yScale);
        // if we just want to render a non-animated sprite
        } else {
            screen.renderSprite(this.s, this.playerFrame.getX(), this.playerFrame.getY(), xScale, yScale);
        }
//        screen.renderFrame(this.playerFrame, xScale, yScale);
 //       screen.renderFrame(this.collisionCheck, xScale, yScale);
    }

    public void update(InteractivePuzzleGame ipg) {
        // get/update keyboard inputs from user
        InputController ic = ipg.getInputController();
        ic.update();
        // position of frame before a change in direction
        int prevY = playerFrame.getY();
        int prevX = playerFrame.getX();
        this.collisionCheck.setY(prevY);
        this.collisionCheck.setX(prevX);

//        this.collisionCheck.setX(player) 
        Direction newD = this.d; // init new direction as curr direction
        // whether or not the player has moved
        this.moved = ic.pressedUp() || ic.pressedDown() || ic.pressedRight() || ic.pressedLeft();

        // Update player frame when key is pressed
        if (ic.pressedUp()) {
            this.collisionCheck.setY(prevY-dx);
            newD = Direction.UP;
        }

        if (ic.pressedDown()) {
            this.collisionCheck.setY(prevY+dx); 
            newD = Direction.DOWN;
        }

        if (ic.pressedRight()) {
            this.collisionCheck.setX(prevX+dx);
            newD = Direction.RIGHT;
        }
        
        if (ic.pressedLeft()) {
            this.collisionCheck.setX(prevX-dx);
            newD = Direction.LEFT;
        }
        // if there has been a change in direction update animationDirection
        if(this.d != newD) {
            this.d = newD;
            animationDirection();
        }

        TileType tile = null; 
        int mappedX = -1;
        int mappedY = -1;

        if (moved){ 
            this.collisionCheck.setX(this.collisionCheck.getX() + collisionOffsetX);
            this.collisionCheck.setY(this.collisionCheck.getY() + collisionOffsetY);
            boolean collisionLeftX = false;
            boolean collisionRightX = false;
            boolean collisionY = false;

            mappedX = (int)Math.floor((collisionCheck.getX())*1.0/64);
            mappedY = (int)Math.floor((collisionCheck.getY())*1.0/64);
            tile = ipg.getGrid().getMappedTile(mappedX, mappedY); // current tile
            if(tile != null){
                System.out.println(tile.getName());
                if(tile.getCollision() == TileType.Collision.ALL) {
                    if( ic.pressedRight())
                        collisionRightX = true;
                    else if(ic.pressedLeft())
                        collisionLeftX = true;
                    else if(ic.pressedUp())
                        collisionY = true;
                    else if(ic.pressedDown())
                        collisionY = true;
                }
            }
            if(collisionRightX) {
                if(collisionCheck.getY() < tile.getFrame().getY()+60 && collisionCheck.getX() < tile.getFrame().getX()+3)
                    this.playerFrame.setY(this.collisionCheck.getY() - collisionOffsetY);
            } else if(collisionLeftX) {
                if(collisionCheck.getY() < tile.getFrame().getY()+60 && collisionCheck.getX() > tile.getFrame().getX() + 59)
                    this.playerFrame.setY(this.collisionCheck.getY() - collisionOffsetY);
            } else if(collisionY){
            } else {
                this.playerFrame.setX(this.collisionCheck.getX() - collisionOffsetX);
                this.playerFrame.setY(this.collisionCheck.getY() - collisionOffsetY);        
            }
       
        }
            int mappedUpX = (int)Math.floor((collisionCheck.getX())*1.0/64);
            int mappedUpY = (int)Math.floor((collisionCheck.getY())*1.0/64);

            int mappedDownX = (int)Math.floor((collisionCheck.getX() + 20)*1.0/64);
            int mappedDownY = (int)Math.floor((collisionCheck.getY())*1.0/64) + 1;

            int mappedLeftX = (int)Math.floor((collisionCheck.getX())*1.0/64);
            int mappedLeftY = (int)Math.floor((collisionCheck.getY() + 35)*1.0/64);

            int mappedRightX = (int)Math.floor((collisionCheck.getX() + 40)*1.0/64);
            int mappedRightY = (int)Math.floor((collisionCheck.getY()+ 35)*1.0/64);

            TileType twoInFront = null;// 2 tiles above of player
            TileType twoDown = null;// 2 tiles below of player
            TileType twoLeft = null;// 2 tiles left of player
            TileType twoRight = null;// 2 tiles right of player

            TileType tileInFront = ipg.getGrid().getMappedTile(mappedUpX, mappedUpY); // tile infront of player
            TileType tileDown = ipg.getGrid().getMappedTile(mappedDownX, mappedDownY); // tile infront of player
            TileType tileLeft = ipg.getGrid().getMappedTile(mappedLeftX, mappedLeftY); // tile infront of player
            TileType tileRight = ipg.getGrid().getMappedTile(mappedRightX, mappedRightY); // tile infront of player
//            if(tileInFront != null) 
//                System.out.println("tile Infront " + tileInFront.getName() + " " + mappedUpX + ", " + mappedUpY);
//            if(tileDown != null)
//                System.out.println("tile Down" + tileDown.getName() + " " + mappedDownX + ", " + mappedDownY);
//            if(tileLeft != null)
//                System.out.println("tile Left" + tileLeft.getName() + " " + mappedLeftX + ", " + mappedLeftY);
//            if(tileRight != null)
//                System.out.println("tile Right" + tileRight.getName() + " " + mappedRightX + ", " + mappedRightY);

            // checks if the tile above the player is a barrel (box) and the user presses use (spacebar or enter)
            // then moves the box in the direction the player was facing
            if(tileInFront != null && tileInFront.getName().equals("barrel") && ic.pressedUse() && ic.prevPressedUp()) {
                twoInFront = ipg.getGrid().getMappedTile(mappedUpX, mappedUpY - 1); 
                if(twoInFront == null || twoInFront.getCollision() != TileType.Collision.ALL) {
                    // check if moving the box will be placed a goal position 
                    tileInFront.setMapped(true, mappedUpX,mappedUpY-1);
                }
            } 

            if(tileDown != null && tileDown.getName().equals("barrel") && ic.pressedUse() && ic.prevPressedDown()) {
                twoDown = ipg.getGrid().getMappedTile(mappedDownX, mappedDownY + 1); 
                if(twoDown == null || twoDown.getCollision() != TileType.Collision.ALL) 
                    tileDown.setMapped(true, mappedDownX,mappedDownY+1);
            }
            if(tileLeft != null && tileLeft.getName().equals("barrel") && ic.pressedUse() && ic.prevPressedLeft()) {
                twoLeft = ipg.getGrid().getMappedTile(mappedLeftX - 1, mappedLeftY); 
                if(twoLeft == null || twoLeft.getCollision() != TileType.Collision.ALL)
                    tileLeft.setMapped(true, mappedLeftX - 1,mappedLeftY);
            }
            if(tileRight != null && tileRight.getName().equals("barrel") && ic.pressedUse() && ic.prevPressedRight()) {
                twoRight = ipg.getGrid().getMappedTile(mappedRightX + 1, mappedRightY); 
                if(twoRight == null || twoRight.getCollision() != TileType.Collision.ALL)
                    tileRight.setMapped(true, mappedRightX + 1,mappedRightY);
            }  
            
           playerWinCheck(ipg); 
            this.a.update(ipg); 
			
        // updates camera as player moves in map  
        Frame cam = ipg.getImageRenderer().getCamera(); 
        cam.setX(this.playerFrame.getX() - cam.getWidth()/2);
        cam.setY(this.playerFrame.getY() - cam.getHeight()/2);
         
    }
    /**
    * Updates the animation direction as the player moves
    */
    public void animationDirection() {
        Frame[] animationPositions =  new Frame[6]; // an array of stills of animated images   
        switch (d) {
            case UP:
                animationPositions =  new Frame[6]; // an array of stills of animated images   
                for(int i = 0; i < animationPositions.length; i++) {
                   animationPositions[i] = new Frame(i*25, 0*32, 20, 30); 
                }
                this.a = new Animation(this.a.getSheet(), animationPositions, 10);
                break;
            case DOWN: 
                animationPositions =  new Frame[6]; // an array of stills of animated images   
                for(int i = 0; i < animationPositions.length; i++) {
                   animationPositions[i] = new Frame(i*25, 2*32, 20, 30); 
                }
                this.a = new Animation(this.a.getSheet(), animationPositions, 10);
                break;
            case LEFT: 
                animationPositions =  new Frame[6]; // an array of stills of animated images   
                for(int i = 0; i < animationPositions.length; i++) {
                   animationPositions[i] = new Frame(i*25, 3*32, 20, 30); 
                }
                this.a = new Animation(this.a.getSheet(), animationPositions, 10);
                break;
            case RIGHT:
                animationPositions =  new Frame[6]; // an array of stills of animated images   
                for(int i = 0; i < animationPositions.length; i++) {
                   animationPositions[i] = new Frame(i*25, 1*32, 20, 30); 
                }
                this.a = new Animation(this.a.getSheet(), animationPositions, 10);
                break;
        } 
    }
    
    /**
    * Sets the boolean flag levelCompleted in the Interactive Puzzle Game if the player wins
    */
    public void playerWinCheck(InteractivePuzzleGame ipg) {
        // checks if the player has completed all the goals
        for(TileType g: ipg.getGrid().getMappedGoals()) {
            this.completedGoals += ipg.getGrid().getMappedTilesAt(g.getX(), g.getY()).size() -1; 
//            System.out.println("COMPLETED COUNT: " + this.completedGoals); 
        }
        // resets the goal count as the game updates 
        if(this.completedGoals != 3) {
            this.completedGoals = 0;
        } else {
            System.out.println("YOU HAVE WON!");
            ipg.setLevelCompleted(true); 
            ipg.setShutdown(true);
            ipg.levelHandler();
        }
    }

}
