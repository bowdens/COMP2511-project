import java.util.ArrayList;

public class Tiles {

    private ArrayList<SpriteSheet> sheets = new ArrayList<SpriteSheet>();
    private ArrayList<TileType> types = new ArrayList<TileType>();
    private int fillIndex = -1; // index of tile to fill the screen floor (grass)

    /**
    * add all sprites here
    */
    public void addTypes() {
        TileType.Collision c = TileType.Collision.NONE;
        // create TileTypes of sprites
        TileType grass = new TileType("grass", this.sheets.get(0).extract(0,1), c.NONE); 
        TileType wall = new TileType("wall", this.sheets.get(0).extract(5,2), c.ALL); 
        TileType barrel = new TileType("barrel", this.sheets.get(0).extract(4,2), c.ALL); 
        TileType treasure = new TileType("treasure", this.sheets.get(0).extract(6,2), c.ALL); 
        TileType sparklywater = new TileType("sparklywater", this.sheets.get(0).extract(4,3), c.NONE); 
       
        // add to list of types
        this.types.add(grass);
        this.fillIndex = 0;

        this.types.add(wall);
        this.types.add(barrel);
        this.types.add(treasure);
        this.types.add(sparklywater);

        setIds(); // setIds after populating the list
    }
    
    
    public void renderTiles(int index, ImageRenderer screen, int xPos, int yPos, int xScale, int yScale) {
        // check if valid
        if(this.types.size() > index && index >= 0)
            screen.renderSprite(this.types.get(index).getSprite(), xPos, yPos, xScale, yScale);
    }
    
    /**
    * sets ids of each tiletype
    */
    public void setIds() {
        int i = 0;
        for(TileType tt: types) {
            tt.setId(i);
            i++;
        }
    }
    
    /**
    * Gets the index of a sprite based of its name. Returns -1 if it doesnt exits
    */
    public int indexOf(String s) {
        for(int i = 0; i < types.size(); i++) {
            if(types.get(i).getName().equals(s)) {
                return i;
            }
        }
        return -1;
    }

    /**
    * Getters
    */ 
    public ArrayList<SpriteSheet> getSheets() {
        return this.sheets;
    }    
    
    public ArrayList<TileType> getTypes() {
        return this.types;
    }
    
    public int getFillIndex() {
        return this.fillIndex;
    }
 

}
