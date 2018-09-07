
public class TileType implements Cloneable {

    private String name; // name of tile
    private Sprite s; // associated sprite
    private int id; // tileId

    private boolean mapped = false; // whether or not tile is rendered to screen
    private int x = -1; // mapped x position of tile
    private int y = -1; // mapped y position of tile
    private Collision c; // type of collision

    private Frame tileFrame; 
 
    public TileType(String name, Sprite s, Collision c) {
        this.name = name;
        this.s = s;
        this.c = c;
    } 
    
    /**
    * Represents the type of collision.
    */
    public enum Collision {
        NONE, ALL, LEFT, RIGHT, UP, DOWN
    }
    
    /**
    * Sets the tile type's Id
    */
    public void setId(int id) {
        this.id = id;
    }

    public void setFrame(int x, int y) {
        int tileHeight = 16 * 4;
        int tileWidth = 16 * 4;
        this.tileFrame = new Frame(x*tileWidth, y*tileHeight, tileWidth, tileHeight);
//        System.out.println(x*tileWidth + "," + y*tileHeight);
    }

    /**
    * sets if the current tile is mapped and sets the mapped position
    */ 
    public void setMapped(boolean mapped, int x, int y) {
        this.mapped = mapped;
        this.x = x;
        this.y = y;
    }
    
    /**
    * overriding clone() method of Object class
    */
    public Object clone() throws CloneNotSupportedException {
        return (TileType)super.clone();
    }   

    /**
    * Getters
    */
    public String getName() {
        return this.name;
    }
    
    public Sprite getSprite() {
        return this.s;
    }

    public int getId() {
        return this.id;
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isMapped() {
        return this.mapped;
    }
    
    public Collision getCollision() {
        return this.c;
    }

    public Frame getFrame() {
        return this.tileFrame;
    }

}
