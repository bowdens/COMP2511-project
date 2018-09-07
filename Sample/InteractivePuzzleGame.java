import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import java.lang.Runnable;
import java.lang.Thread;
import java.lang.System;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;

public class InteractivePuzzleGame extends JFrame implements Runnable{
    private Canvas c = new Canvas();
    private ImageRenderer screen; // screen to which we call render methods   
     
    private BufferedImage generalImg;
    private SpriteSheet generalTilesSheet; 

    private BufferedImage playerSprites;
    private BufferedImage playerSprites2;
    private SpriteSheet playerSheet; 
    private SpriteSheet playerSheet2; 
    
    private Animation playerAnimation;

    private Tiles tiles;
    private Grid g;

    private final int playerScaleX = 2;
    private final int playerScaleY = 2;
    private final int tileScaleX = 4;
    private final int tileScaleY = 4;

//    private Frame testFrame = new Frame(30, 30, 100, 100);

    private GameObject[] gameObject; // objects such as player, enemy etc.
    private Player p;
    private InputController ic;
	
    private Map level;

    private boolean shutdown = false; // check to stop thread
    private int countdown = 60;
    private boolean levelCompleted = false;

    public InteractivePuzzleGame() {
        toolBarDisplay();
        // quits application on close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        // set position and size of window
        setBounds(0,0,tileScaleX*16*11,tileScaleY*16*10+32);
        // sets window to the centre of the screen  
        setLocationRelativeTo(null); 
        // Make frame visible
        add(this.c);
        // shows window
        setVisible(true); // must be before buffer strat because buffer strat needs a frame

        /*--------------------------------------------------------------------------
           Multi-buffering useful for rendering performance
         - One of the arrays will be what are showing on the screen
         - One of the arrays will be what we are writing to
         - Sometimes the write operation takes longer than it would to switch buffers
         - Hence we will use 3 buffers 
        ---------------------------------------------------------------------------*/
        c.createBufferStrategy(3);
        // create the screen to which we render pixels
        this.screen = new ImageRenderer(getWidth(), getHeight());

        // create and loads general sprites from sprite sheets
        this.generalImg = this.screen.loadImage("/files/Tiles1.png");
        this.generalTilesSheet = new SpriteSheet(generalImg);
        this.generalTilesSheet.getAllSprites(16,16);// sprites are of sixe 16 pixels * 16 pixels
        this.tiles = new Tiles();
        this.tiles.getSheets().add(this.generalTilesSheet);

       // create and loads player sprites from sprite sheets
        this.playerSprites = this.screen.loadImage("/files/player_25_30.png");
        this.playerSheet = new SpriteSheet(playerSprites);
//        this.playerSheet.getAllSprites(32,32); 
//        this.playerSheet.extract(20,25);

        this.tiles.addTypes(); // loads all player sprites required 

        // load Grid/Map
        this.level = new Map(10,10,"e");
        this.g = new Grid(this.tiles, this.level); 
        this.g.addMapTypes(this);
        
        // create

        // create and populate animationPosition frames
        Frame[] animationPositions =  new Frame[6]; // an array of stills of animated images   
        for(int i = 0; i < animationPositions.length; i++) {
           animationPositions[i] = new Frame(i*25, 1*32, 20, 30); 
        }
        this.playerAnimation = new Animation(playerSheet, animationPositions, 10);

        // load objects
        this.gameObject = new GameObject[2]; // create an array of game objects 
        this.p = new Player(playerAnimation, this.playerScaleX , this.playerScaleY, this.g.getPlayerPosX(), this.g.getPlayerPosY());
        this.gameObject[0] = this.p;
        this.gameObject[1] = this.playerAnimation;
        
        // add listeners to canvas
        this.ic = new InputController(this);
        this.c.addKeyListener(this.ic);    
        this.c.addFocusListener(this.ic); 

        this.c.setFocusable(true); 
        this.c.requestFocus();
    }

    public static void main(String[] args) {
	// constructs a new InteractivePuzzleGame
        InteractivePuzzleGame ipg = new InteractivePuzzleGame();
    }
    

    public void update() {
        // iterates through gameObject array and calls the update method on appropriate object
        for(int i = 0; i < this.gameObject.length; i++) {
            this.gameObject[i].update(this);
        }
    }

    public void render() {
        // gets current buffer we are writing to
        BufferStrategy bs = c.getBufferStrategy();
        // gets graphics for current buffer strat
        Graphics gfx = bs.getDrawGraphics();
        super.paint(gfx); // paints the cointainer
        this.g.render(this.screen, this.tileScaleX, this.tileScaleY);

        // iterates through gameObject array and calls the render method on appropriate object
        for(int i = 0; i < this.gameObject.length; i++) {
            if(this.gameObject[i] instanceof Player)
                this.gameObject[i].render(this.screen, this.playerScaleX, this.playerScaleY); // scale size of our player sprite is smaller than our tiles
            else
                this.gameObject[i].render(this.screen, this.playerScaleX, this.playerScaleY);
        }

        // renders everything on screen
        screen.render(gfx); 
 
        gfx.dispose(); // releases graphics system resources
        bs.show(); // done with writing to buffer and enqueues to be put on screen
    }
    
    public void run(){
        
        BufferStrategy bs = c.getBufferStrategy();
        /*--------------------------------------------------------------------------
        Since computers do not run at the same speed, we dont want the speed of
        the game to change based on the computer you run it on. Hence we call the 
        update method at specific intervals.
        ---------------------------------------------------------------------------*/
        long endTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double nanoSecondConversion = 1000000000.0;
        double deltaSeconds = 0;
        int fps = 0;
        int updates = 0;
        while(!this.shutdown){
            long currTime = System.nanoTime();
            // change in seconds in nano seconds
            deltaSeconds += (currTime - endTime)*60.0 / nanoSecondConversion;
            // Update method will run every second on any computer 
            while( deltaSeconds >= 1){ 
                update(); 
                updates++;
                deltaSeconds = 0;
            }
            // Render will be run as fast as the computer can run it
            render();
            endTime = currTime; // update endTime for next 60 frames
            fps++;
            // prints the FPS to console
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("Updates: " + updates + " FPS: " + fps);
                this.countdown--;
                if(countdown == 0) {
                    setLevelCompleted(false);
                    levelHandler();
                    this.shutdown = true;
                } 
                    
                updates = 0;
                fps = 0;
            }

            setTitle("Ware House box   | " + this.countdown);
        }
    }
   
    /**
    * Displays a message if the player has won the game
    */ 
    public void levelHandler() {
        JPanel contentPane = (JPanel) getContentPane();
        if(this.levelCompleted){
           JOptionPane.showMessageDialog(contentPane, "Congrats you have won the game in " + this.countdown+" seconds!"); 
        } else {
           JOptionPane.showMessageDialog(contentPane, "Time up you lose :("); 
        }
    }

    /**
    * Setters 
    */
    public void setLevelCompleted(boolean win) {
        this.levelCompleted = win;
    }

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }

    /**
    * Getters
    */
    public InputController getInputController() {
        return this.ic;
    }
    
    public ImageRenderer getImageRenderer() {
        return this.screen;
    }

    public Grid getGrid() {
        return this.g;
    }

    public int getPlayerScaleX() {
        return this.playerScaleX;
    }

    public int getPlayerScaleY() {
        return this.playerScaleY;
    }

    public int getTileScaleX() {
        return this.tileScaleX;
    }

    public int getTileScaleY() {
        return this.tileScaleY;
    }

    
    /**
    * Interrupts the current thread (necesssary as we do not want to create a new thread for every level generation),
    * before creating a new thread
    */ 
    public void startGame() {
        try {
            while(!Thread.currentThread().isInterrupted()) {
                if(this.shutdown == true){
                    Thread.currentThread().interrupt();
                    Thread.sleep(1000);
                }
                this.shutdown = true;
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        Thread t = new Thread(new InteractivePuzzleGame());
        t.start();
    }
    
    public void toolBarDisplay() {
        final JToolBar toolBar = new JToolBar();

        //Create the popup menu for difficulty level
        final JPopupMenu popup = new JPopupMenu();
        popup.add(new JMenuItem(new AbstractAction("Easy") {
          public void actionPerformed(ActionEvent e) {
              //JOptionPane.showMessageDialog(frame, "You selected Easy difficulty");
          }
        }));

        popup.add(new JMenuItem(new AbstractAction("Medium") {
          public void actionPerformed(ActionEvent e) {
             // JOptionPane.showMessageDialog(frame, "You selected Medium difficulty");
          }
        }));

        popup.add(new JMenuItem(new AbstractAction("Hard") {
          public void actionPerformed(ActionEvent e) {
              //JOptionPane.showMessageDialog(frame, "You selected Hard difficulty");
          }
        }));

        // create popup menu for options menu
        final JPopupMenu popup2 = new JPopupMenu();
        popup2.add(new JMenuItem(new AbstractAction("Option1") {
          public void actionPerformed(ActionEvent e) {
              //JOptionPane.showMessageDialog(frame, "Option 1 selected");
          }
        }));

        popup2.add(new JMenuItem(new AbstractAction("Option2") {
          public void actionPerformed(ActionEvent e) {
              //JOptionPane.showMessageDialog(frame, "Option 2 selected");
          }
        }));

        popup2.add(new JMenuItem(new AbstractAction("Option3") {
          public void actionPerformed(ActionEvent e) {
             // JOptionPane.showMessageDialog(frame, "Option 3 selected");
          }
        }));

        final JButton difficultyButton = new JButton("Select Difficulty");
        difficultyButton.addMouseListener(new MouseAdapter() {
          public void mousePressed(MouseEvent e) {
              popup.show(e.getComponent(), e.getX(), e.getY());
          }
        });

        final JButton optionsButton = new JButton("Generate Level");
        optionsButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startGame();
                dispose();
            }
        });

        toolBar.add(difficultyButton);
        toolBar.add(optionsButton);

        getContentPane().add(toolBar, BorderLayout.NORTH);
        pack();

    }

}



