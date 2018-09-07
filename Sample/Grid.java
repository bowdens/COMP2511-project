import java.util.ArrayList;

public class Grid {
    
    private Tiles tiles;
    private ArrayList<TileType> mappedTiles = new ArrayList<TileType>();
    private ArrayList<TileType> mappedGoals = new ArrayList<TileType>();
    private Node n;
    private Map level;
    private int playerPosX;
    private int playerPosY;

    public Grid(Tiles tiles, Map level) {
        this.level = level;
        level.generateLevel();
        this.tiles = tiles;
    }

    public void addMapTypes(InteractivePuzzleGame ipg) {
        int index = -1;
        Frame cam = ipg.getImageRenderer().getCamera(); 
		Node array[][] = level.getGraph();
        for (int y = 0; y < array.length; y ++){
            for (int x = 0; x < array[y].length; x++){
                this.n = array[y][x];
                switch (n.getType()) {
                    case 'S':
                        if(n.getStepId() == 1){
                                this.playerPosX = x;	
                                this.playerPosY = y;
                        }
                        break;
                    case '.':
                        index = this.tiles.indexOf("wall");
                        addMapped(index, x, y);
                        break;
                    case 'B': 
                        index = this.tiles.indexOf("barrel");
                        addMapped(index, x, y);
                        break;
                    case 'G':
                        index = this.tiles.indexOf("sparklywater");
                        addMapped(index, x, y);
                        this.mappedGoals.add(getMappedTile(x,y));
                        break;
                    case 'E':
                        index = this.tiles.indexOf("treasure");
                        addMapped(index, x, y);
                        break;
                }
                // creates a wall around the map in y direction
                index = this.tiles.indexOf("wall");
                if(y == array.length - 1){
                    if(x == 0) { 
                        addMapped(index, x-1, y);
                        addMapped(index, x-1, y + 1);
                    }
                    if(x == array.length - 1) {
                        addMapped(index, x + 1, y);
                        addMapped(index, x + 1, y + 1);
                    }
                    addMapped(index, x, y + 1);
                } else if( y == 0) {
                    if(x == 0) { 
                        addMapped(index, x-1, y);
                        addMapped(index, x-1, y - 1);
                    }
                    if(x == array.length - 1) {
                        addMapped(index, x+1, y);
                        addMapped(index, x+1, y - 1);
                    }
                    addMapped(index, x, y - 1);
                } else if(x == array[y].length - 1) {
                    addMapped(index, x + 1, y);
                } else if( x == 0) {
                        index = this.tiles.indexOf("wall");
                        addMapped(index, x - 1, y );
                }
                // creates wall around 

					
//                    System.out.println("Values at array["+i+"]["+j+"] is " + array[i][j]);
            }
        }
    }
    
    public void addMapped(int index, int x, int y){
        // clones type of tile in type list
        TileType mappedType = null;
        try {
            mappedType = (TileType) this.tiles.getTypes().get(index).clone();
        } catch (CloneNotSupportedException c) {
            c.printStackTrace();
        }
        if(mappedType != null) {
            mappedType.setMapped(true, x, y);
            mappedType.setFrame(x,y);
            this.mappedTiles.add(mappedType);
        }
    }
    
    public void render(ImageRenderer screen, int xScale, int yScale) {
        int tileWidth = 16 * xScale;
        int tileHeight = 16 * yScale;
        Frame cam = screen.getCamera();                

        // fills the Grid/map with our fill tile (grass)
        for(int y = cam.getY() - cam.getHeight() - (cam.getY() % tileHeight); y < cam.getY() + cam.getHeight(); y+=tileHeight) {
            for(int x = cam.getX() - cam.getWidth() - (cam.getX() % tileWidth); x < cam.getX() + cam.getWidth(); x+=tileWidth) {
                // make sure fillIndex is set
                if(tiles.getFillIndex() != -1)
                    tiles.renderTiles(tiles.getFillIndex(), screen, x, y, xScale, yScale); 
            }
        }    

        // displays all tiles onto grid
        for (TileType tt: this.mappedTiles) {
                                                    // x-position && y-position multiplied by tileWidth/Height
            this.tiles.renderTiles(tt.getId(), screen,  tt.getX() * tileWidth, tt.getY() * tileHeight, xScale, yScale); 
        }

    }
    
    /**
    * Returns the tile mapped to the position passed else returns null
    */
    public TileType getMappedTile(int x, int y) {
        for(TileType tt: this.mappedTiles) {
            if(tt.getX() == x && tt.getY() == y)
                return tt;
        } 
        return null;
    }

    /**
    * gets all the tiles that are mapped to that position else returns null
    */
    public ArrayList<TileType> getMappedTilesAt(int x, int y) {
        ArrayList<TileType> es = new ArrayList<TileType>();
        for(TileType mt: this.mappedTiles) {
            if(mt.getX() == x && mt.getY() == y)
                es.add(mt);
        }
        return es;
    }



    public boolean doesCollide(Frame f, int xScale, int yScale) {

        int tileWidth = 16 * xScale;
        int tileHeight = 16 * yScale;

        //Coordinates to check all tiles in a radius of 4 around the player
        int topLeftX = (int) Math.floor((f.getX() - 64)*1.0/tileWidth);
        int topLeftY = (int) Math.floor((f.getY() - 64)*1.0/tileHeight);
        int bottomRightX = (int) Math.floor((f.getX() + f.getWidth() + 64)*1.0/tileWidth);
        int bottomRightY = (int) Math.floor((f.getY() + f.getHeight() + 64)*1.0/tileHeight);

        TileType.Collision c = TileType.Collision.NONE;

        //Starting at the top left tile and going to the bottom right
        for(int x = topLeftX; x < bottomRightX; x++) {
            for(int y = topLeftY; y < bottomRightY; y++) {
                TileType tile = getMappedTile(x, y); // current tile
                // if we find a tile at that position check for collision type
                if(tile != null) { 
                    c = tile.getCollision();     
                    Frame tileFrame = null;
                    switch (c) {
                        case ALL:
                            if(f.intersects(tile.getFrame()))
                                return true;
                            break;
                        case UP: 
                            // frame around tile
                            if(f.intersects(tile.getFrame())) 
                               return true; 
                            break;
                    }

                    int printfX = tile.getFrame().getX() + tile.getFrame().getWidth();
                    int printX = f.getX() + f.getWidth();
//                    System.out.println("x intersection check: "+ f.getX() + " > " + printfX + " || " + tile.getFrame().getX() + " > " +printX);
                }
            }
        }

        return false;
    }

    /**
    * Getters
    */
    public ArrayList<TileType> getMappedTiles() {
        return this.mappedTiles;
    }

    public ArrayList<TileType> getMappedGoals() {
        return this.mappedGoals;
    }

    /**
    * returns the players postion
    */
    public int getPlayerPosX() {
	return this.playerPosX;
    }

    public int getPlayerPosY() {
        return this.playerPosY;
    }
    

}
