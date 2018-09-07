import java.awt.image.BufferedImage;

public class Animation extends Sprite implements GameObject {
    
    public Sprite[] sprites; // array of sprites for animation
    private int index = 0;
    private int speed; // speed of animation
    private int updates = 0; // updates per second
    private SpriteSheet sheet;
    
    /**
    * Constructor given a list of images
    */   
    public Animation (BufferedImage[] imgs, int speed) {
        // create sprites array an populate with images for animation
        this.sprites = new Sprite[imgs.length];
        for(int i = 0; i < imgs.length; i++) {
            this.sprites[i] = new Sprite(imgs[i]);
        }
        this.speed = speed;
    }

    /**
    * Sprite constuctor given a sprite sheet
    */
    public Animation(SpriteSheet sheet, Frame[] positions, int speed) {
        this.sheet = sheet;
        // create sprites array an populate with images for animation
        this.sprites = new Sprite[positions.length];
        for(int i = 0; i < positions.length; i++) {
            this.sprites[i] = new Sprite(sheet, positions[i].getX(), positions[i].getY(), positions[i].getWidth(), positions[i].getHeight());
        }
        this.speed = speed;
    }

    // render is called in base class
    public void render(ImageRenderer screen, int xScale, int yScale) {}

    /**
    * updates index in array of sprites for animation for a given speed
    */
    public void update(InteractivePuzzleGame ipg) {
//        this.updates += (this.updates + 1) % this.speed;  
        this.updates++;
        if(updates >= speed) {
            this.updates = 0;
            this.index++;
            if(this.index >= this.sprites.length)
                this.index = 0;
        }
//        System.out.println("Updates "+ this.updates +" index: "+ this.index + " lenght: " + this.sprites.length);
    }

    /**
    * Getters
    */ 
    @Override
    public int[] getPixels(){
        return this.sprites[index].getPixels();
    }

    @Override
    public int getHeight(){
        return this.sprites[index].getHeight();
    }   

    @Override
    public int getWidth(){
        return this.sprites[index].getWidth();
    }
    
    public SpriteSheet getSheet() {
        return this.sheet;
    }
}
