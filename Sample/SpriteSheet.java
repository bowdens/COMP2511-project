import java.awt.image.BufferedImage;
/**
* Holds an int[] of pixels for the entire sheet image.
* Holds array of sprites
* Sprites are loaded based on image size parameters
*/
public class SpriteSheet {
    private int[] pixels;
    private BufferedImage sheet;
    private final int width; // width of entire sprite sheet
    private final int height; // height of entire sprite sheet
    private Sprite[] sprites = null; // array of all sprites on sheet
    private int spriteX; // size of each sprite in X
    private int spriteY; // size of each sprite in Y

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
        this.width = sheet.getWidth();
        this.height = sheet.getHeight();
        // create and populate pixels array 
        this.pixels = new int[this.width * this.height];
        this.sheet.getRGB(0, 0, this.width, this.height, this.pixels, 0, this.width);
    }

    public void getAllSprites(int spriteX, int spriteY) {
        this.sprites = new Sprite[(this.width / spriteX) * (this.height / spriteY)];
        int i = 0;
        // populate sprites array with sprites
        for(int y = 0; y < this.height; y += spriteY) {
            for(int x = 0; x < this.width; x += spriteX) {
                sprites[i] = new Sprite(this, x, y, spriteX, spriteY);
                i++;
            }
        }
        this.spriteX = spriteX;
        this.spriteY = spriteY;
    }

    public Sprite extract(int x, int y) {
        // if sprites array is populated
        if(sprites != null) {
            int index = x + y * (width / spriteX);
            // check if requested sprite is valid
            if ( index < sprites.length)
                return sprites[index];
        }
        System.out.println(x+" "+ y+" Sprites Are NULL!!");
        return null;
    }
    
    public BufferedImage getSheetImg() {
        return this.sheet;
    }
    
    public int[] getPixels() {
        return this.pixels;
    }
}
