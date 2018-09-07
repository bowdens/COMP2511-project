import java.awt.image.BufferedImage;
/**
* Holds int[] of pixels for sprite image.
* Holds fields for the width and height of individual sprites.
*/
public class Sprite {
    private int width; // width of sprite
    private int height; // height of sprite
    private int[] pixels; // array of pixels of sprite
    
    /**
    * Sprite constuctor given a sprite sheet
    */
    public Sprite(SpriteSheet sheet, int x, int y, int width, int height) {
        this.width = width;     // width of sprite in pixels
        this.height = height;   // height of sprite in pixels
        // create an array of pixels for sprite 
        pixels = new int[width * height];
        // set pixels from image into pixels array
        sheet.getSheetImg().getRGB(x,y, width, height, pixels, 0, width);
    }
    
    /**
    * Sprite constructor given a single image
    */
    public Sprite(BufferedImage img) {
        this.width = img.getWidth();
        this.height = img.getHeight();
        // create an array of pixels for single image
        pixels = new int[this.width * this.height];
        // set pixels array
        img.getRGB(0, 0, width, height, pixels, 0, width);
    }
    /***/
    public Sprite(){};

    /**
    * Getters
    */ 
    public int[] getPixels(){
        return this.pixels;
    }

    public int getHeight(){
        return this.height;
    }   

    public int getWidth(){
        return this.width;
    }
    
} 
