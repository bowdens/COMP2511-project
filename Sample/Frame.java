public class Frame {
    private int x;
    private int y;
    private int width;
    private int height;
    private int[] pixels;
    private final int unusedColour = 0xFF00FFDD; 

    Frame(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    Frame() {
        this(0,0,0,0);
    }
    
    /**
    *    Fills pixels array for Frame object.    
    *    Since we do not always display Frame objects, (e.g checking collisions we create movement object,
    *    but do not need to render). Hence we call generate graphics only when required.
    */
    public void generateFrameGraphics (int colour) {
        this.pixels = new int[this.width * this.height];
        for (int i = 0; i < this.pixels.length; i++){
            this.pixels[i] = colour;
        }
    }
    
    public void generateFrameGraphics (int colour, int borderWidth) {
        pixels = new int[this.width * this.height];
        // sets all the pixels of the frame to the passed colour
        for (int i = 0; i < this.pixels.length; i++) {
            this.pixels[i] = colour;
        }
        // clears the inside of the frame so you are just left with the border
        for(int x = borderWidth;  x < this.width-borderWidth; x++) {
            for(int y = borderWidth;  y < this.height-borderWidth; y++) {
                if(x >= 0 && y >= 0 && x <= width && y <= height){
                    this.pixels[x + y* this.width] = unusedColour; 
                }
            }
        }
        /*this.pixels = new int[this.width * this.height];
        
        for(int i = 0; i < this.pixels.length; i++)
            this.pixels[i] = unusedColour;

        for(int y = 0; y < borderWidth; y++)
            for(int x = 0; x < this.width; x++)
                this.pixels[x + y * this.width] = colour;

        for(int y = 0; y < this.height; y++)
            for(int x = 0; x < borderWidth; x++)
                this.pixels[x + y * this.width] = colour;

        for(int y = 0; y < this.height; y++)
            for(int x = this.width - borderWidth; x < this.width; x++)
                this.pixels[x + y * this.width] = colour;

        for(int y = this.height - borderWidth; y < this.height; y++)
            for(int x = 0; x < this.width; x++)
                this.pixels[x + y * this.width] = colour;*/
    }

    /**
    * Checks if a frame intersects with this frame. 
    * @return true if frames intersect else false
    */
    public boolean intersects(Frame f) {
        // if either of the frames x positions do not intersect
        if (this.x > f.getX() + f.getWidth() || f.getX() > this.x + this.width) {
            return false; 
        } 
        // if either of the frames y positions do not intersect
        if (this.y > f.getY() + f.getHeight() || f.getY() > this.y + this.height) { 
            return false;
        }
        
        return true;
    }    
    /**
    * getters
    */
    public int[] getPixels() {
        // returns pixels array after populated from generateFrameGraphics
        if(pixels != null)
            return pixels;
        // else null due to not generating graphics or error
        return null;
    }

    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
    
    /**
    *    Setters
    */
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
