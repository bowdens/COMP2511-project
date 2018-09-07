import java.util.ArrayList;

public class Board {
	private int width;
	private int height;
	private ArrayList<BoardObject> objects;
	private Player player;
	
	public Board() {
		// TODO
	}
	
	/**
	 * Calls the update strategy for each non player object on the board
	 */
	public void updateObjects() {
		for (BoardObject obj : objects) {
			obj.update(this);
		}
	}
	
	public void showObjects() {
		// TODO
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}
