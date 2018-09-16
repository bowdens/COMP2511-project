package project.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import project.model.Game;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ProjectController extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	private Game game;
	
	public ProjectController() {
		this.game = new Game();
		addKeyListener(this);
		setFocusable(true);
	}

	public Game getGame() {
		return game;
	}

	public void start() { 
		//getGame().loadAllBoards();
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,400);
		frame.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent key) {
		int code = key.getKeyCode();
		
		switch (code) {
			case KeyEvent.VK_SPACE:
				while (game.shootArrow());
				break;
			case KeyEvent.VK_A:
				game.dropBomb();
				break;
			case KeyEvent.VK_UP:
				if (game.movePlayer("Up"))
				game.newTurn();
				break;
			case KeyEvent.VK_DOWN:
				if (game.movePlayer("Down"))
				game.newTurn();
				break;
			case KeyEvent.VK_LEFT:
				if (game.movePlayer("Left"))
				game.newTurn();
				break;
			case KeyEvent.VK_RIGHT:
				if (game.movePlayer("Right"))
				game.newTurn();
				break;
		}
	}

	// Not sure we need this unless you want to move
	// some or all of the ones above down here
	@Override
	public void keyReleased(KeyEvent key) {
		int code = key.getKeyCode();
		
		if (code == KeyEvent.VK_DOWN){
			System.out.println("Tic");
		}
	}

	// Not sure we need this
	@Override
	public void keyTyped(KeyEvent key) {
		int code = key.getKeyCode();
		
		if (code == KeyEvent.VK_DOWN){
			System.out.println("Tic");
		}
	}


}
