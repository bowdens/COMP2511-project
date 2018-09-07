import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputController implements KeyListener, FocusListener{
    
    private boolean[] keys = new boolean[65536]; // boolean array of all possible keys
    private boolean up, down, left, right, use, back; // controls
    private int[] lastThree = new int[3]; // previous key lastThree[0], current key lastThree[1]
    public int lastKeyReleased;
    private InteractivePuzzleGame ipg;

    public InputController (InteractivePuzzleGame ipg) {
        this.ipg = ipg;
    }    

    /**
    * Updates boolean values of keypresses
    */
    public void update() {
        // sets appropriate array elements to controls
        this.up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        this.down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        this.left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        this.right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        this.use = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_ENTER];
        this.back = keys[KeyEvent.VK_ESCAPE];
    }

    /**
    * If key is pressed set the element at that key index as true
    */
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        this.lastThree[0] = this.lastThree[1];
        this.lastThree[1] = this.lastThree[2];
        this.lastThree[2] = e.getKeyCode();
     }

    /**
    * If key is released set the element at that key index as false
    */
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        this.lastKeyReleased = e.getKeyCode();
    }
    
    // we do not use this method in implement 
    public void keyTyped(KeyEvent e) {}
   
    /**
    * sets all controls to released state
    */ 
    public void releaseAll() {
        up = down = left = right = use = back = false;
    }
    
    // we do not use this method in implement 
    public void focusGained(FocusEvent e) {}

    /**
    * when focused is lost we want all keys pressed to be set to false
    * e.g. when you tab out of game it stops
    */
    public void focusLost(FocusEvent e) {
        for(int i = 0; i < keys.length; i++)
            keys[i] = false;
    }
    
    /**
    * returns true on the prev pressed key else false
    */
    public boolean prevPressedUp() {
        if(this.lastThree[1] == KeyEvent.VK_UP || this.lastThree[1] == KeyEvent.VK_W)
            return true;
        return false;
    }

    public boolean prevPressedDown() {
        if(this.lastThree[1] == KeyEvent.VK_DOWN || this.lastThree[1] == KeyEvent.VK_S)
            return true;
        return false;
    }    

    public boolean prevPressedLeft() {
        if(this.lastThree[1] == KeyEvent.VK_LEFT || this.lastThree[1] == KeyEvent.VK_A)
            return true;
        return false;
    }

    public boolean prevPressedRight() {
        if(this.lastThree[1] == KeyEvent.VK_RIGHT || this.lastThree[1] == KeyEvent.VK_D)
            return true;
        return false;
    }
    

    /**
    * Getters
    */
    public boolean pressedUp() { 
        return this.up;    
    }

    public boolean pressedDown() { 
        return this.down;    
    }

    public boolean pressedLeft() { 
        return this.left;    
    }

    public boolean pressedRight() { 
        return this.right;    
    }

    public boolean pressedUse() { 
        return this.use;    
    } 

    public boolean pressedBack() { 
        return this.back;    
    }

}
