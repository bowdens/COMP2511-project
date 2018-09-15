package project.model;

import java.util.ArrayList;
import model.BoardEntity;

public class Inventory {
	//private ArrayList<BoardEntity> items;
	private Sword sword;
	private int arrows;
	private int bombs;
	//potions
	
	public Inventory() {
		this.sword = null;
		this.arrows = 0;
		this.bombs = 0;
	}
	
	public void addSword(Sword s) {
		if(this.sword == null) {
			this.sword = s;
		}else {
			//player picks up a second sword, so sword swings increases to 5
			s.increaseSwings();
		}
	}
	
	public Sword getSword() {
		return sword;
	}
	
	public void removeSword() {
		this.sword = null;
	}
	
	public void addArrow() {
		this.arrows++;
	}
	
	public void removeArrow() {
		if(this.arrows == 0) {
			return;
		}else {
			this.arrows--;
		}
	}
	
	public int getNumArrows() {
		return this.arrows;
	}
	
	public void addBomb() {
		this.bombs++;
	}
	
	public void removeBomb() {
		if(this.bombs == 0) {
			return;
		}else {
			this.bombs--;
		}
	}
	
	public int getNumBombs() {
		return this.bombs;
	}
	
}
