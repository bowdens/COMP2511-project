package project.model;

public class GamePlayer {
	
	private Board board;
	private Player player;
	

	public GamePlayer(Board board) {
		this.board = board;
		player = board.getPlayer();
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public boolean shootArrow() {
		return player.shootArrow(board);
	}
	
	public void dropBomb() {
		if (!(player.dropBomb(board))) {
			System.out.println("Can't Drop Bomb!");
		}
	}

	public boolean movePlayer(String direction) {
		
		int prevX = player.getX();
		int prevY = player.getY();
		
		switch (direction) {
			case "Up":
				player.moveUp(board);
				break;
			case "Down":
				player.moveDown(board);
				break;
			case "Left":
				player.moveLeft(board);
				break;
			case "Right":
				player.moveRight(board);
				break;
		}
		
		// If the player has moved return true so that the turn can be played out
		if (player.getX() != prevX || player.getY() != prevY) {
			return true;
		}
		
		return false;
	}
	
	public void newTurn() {
		//TODO
	}

}
