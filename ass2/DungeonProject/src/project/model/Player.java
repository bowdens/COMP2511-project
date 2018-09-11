package project.model;

import java.util.ArrayList;

import project.model.CollisionBehaviours.NoCollision;

public class Player extends BoardEntity {

	int bombs;
	int arrows;
	int swords;
	boolean hover;
	boolean invincible;
	ArrayList<Integer> keys;
	
	
	public Player(int x, int y) {
		super(x, y);
		bombs = 0;
		arrows = 0;
		swords = 0;
		hover = false;
		invincible = false;
		setCollisionBehaviour(new NoCollision());
	}

}
