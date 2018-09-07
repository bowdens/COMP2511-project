import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import javax.imageio.ImageIO;
import java.io.IOException;

import java.util.Random;
public class ImageRenderer implements Cloneable {
    
    private BufferedImage image; // the entire screen's image
    private int[] pixels; // array of all the pixels on the screen
    private Frame cam; // camera (user's view point), moves the entire screen 
    private final int unusedColour = 0xFF00FFDD; // colour of background in sprites 

    public ImageRenderer (int width, int height){
        // creates an image with an accessable buffer 
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // get dataBuffer from raster created from buffered Image
        DataBuffer db = this.image.getRaster().getDataBuffer();
        // convert image object into an array of integers
        pixels = ((DataBufferInt)db).getData();
        cam = new Frame(0, 0, width, height);
    }

    /**
    * @return returns a buffered image
    * Takes an image path and loads it into a BufferedImage type
    */ 
    public BufferedImage loadImage(String file) {
        try {
            BufferedImage loadedImage = ImageIO.read(InteractivePuzzleGame.class.getResource(file)); // loads image (can be any format)
            // formats image to RGB
            BufferedImage formattedImage = new BufferedImage(loadedImage.getWidth(), loadedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            formattedImage.getGraphics().drawImage(loadedImage, 0, 0, null);
            return formattedImage;
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    
    }

    /**
    * Clears pixles array
    */
    public void clear() {
        for(int i = 0; i < pixels.length; i++)
            pixels[i] = 0;
    }
    
    public void render(Graphics gfx) {
        gfx.drawImage(this.image, 0, 0, this.image.getWidth(), this.image.getHeight(), null);
        clear(); // clear so that when movement occurs on screen old image positions get removed
    }

   /**
    * Renders any image to Screen
    */ 
    public void renderImg(BufferedImage img, int xPos, int yPos, int xScale, int yScale) {
        // get dataBuffer from raster created from tile
        DataBuffer db = img.getRaster().getDataBuffer();
        // convert image object into an array of integers
        int[] imgPixels = ((DataBufferInt)db).getData();
        renderType(imgPixels, img.getHeight(), img.getWidth(), xPos, yPos, xScale, yScale);
    }

   /**
    * Renders a sprite to screen
    */ 
    public void renderSprite(Sprite s, int xPos, int yPos, int xScale, int yScale) {
        renderType(s.getPixels(), s.getHeight(), s.getWidth(), xPos, yPos, xScale, yScale);
    }
   
    /**
    * Renders a frame to screen
    */ 
    public void renderFrame(Frame f, int xScale, int yScale) {
        // only render if we can/need to
        if(f.getPixels() != null)
            renderType(f.getPixels(), f.getHeight(), f.getWidth(), f.getX(), f.getY(), xScale, yScale);
    }
    
    /**
    * Will render any type (sprite, tile, whatever) given a pixel array of image
    */
    public void renderType(int[] render, int height, int width, int xPos, int yPos, int xScale, int yScale) {
        // for each row in pixels to render
        for(int y = 0; y < height; y++) {
            // for each col in pixels to render
            for(int x = 0; x < width; x++) {
                // for each position of scaled image in y
                for(int yScalePos = 0; yScalePos < yScale; yScalePos++) {
                    // for each position of scaled image in x
                    for(int xScalePos = 0; xScalePos < xScale; xScalePos++) {
                        int renderIndex = x + y * width;
                        //  xth pixl * scaled size + the offset of position and scaled position, similarly y
                        int xPixelsToRender = (x*xScale) + xPos + xScalePos;
                        int yPixelsToRender = (y*yScale) + yPos + yScalePos;
                        // pixels must be in range of the camera
                        if(xPixelsToRender >= cam.getX() && yPixelsToRender >= cam.getY() && xPixelsToRender<= (cam.getX() + cam.getWidth()) && yPixelsToRender<= (cam.getY() +cam.getHeight())) {
                            // multiply y by the entire screens width such that it renders for each yth position in the screen (otherwise you get a straight line)
                            int pixelsIndex =  xPixelsToRender - cam.getX() + (yPixelsToRender - cam.getY())* this.image.getWidth();
                            // checks for rendering out of bounds and we are not trying to render our unusedColour 
                            if(pixelsIndex < pixels.length && render[renderIndex] != unusedColour)
                                pixels[pixelsIndex] = render[renderIndex];
                        }
                    }
                }
            }
        }
    }

    /**
    * overriding clone() method of Object class
    */
    public Object clone() throws CloneNotSupportedException {
        return (ImageRenderer)super.clone();
    }

    /**
    * Getters
    */
    public Frame getCamera() {
        return this.cam;
    }
}
