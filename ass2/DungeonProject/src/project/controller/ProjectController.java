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
		getGame().loadAllBoards();
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,400);
		frame.setVisible(true);
	}
	
	public void makeNewBoard(String name, int height, int width) {
		game.createNewBoard(name, height, width);
	}
	
	public void addRequestedEntity(int entity, int x, int y) {
		game.addEntityToBoard(entity, x, y);
	}
	
	public void removeRequestedEntity(int x, int y) {
		game.removeEntityAt(x, y);
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
	}

	// Not sure we need this
	@Override
	public void keyTyped(KeyEvent key) {
	}


}
