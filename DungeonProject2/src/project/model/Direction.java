package project.model;

import java.util.ArrayList;

public enum Direction {
	UP, DOWN, LEFT, RIGHT, NONE;

	public static ArrayList<Direction> directions() {
		ArrayList<Direction> dirs = new ArrayList<Direction>();
		dirs.add(UP);
		dirs.add(DOWN);
		dirs.add(LEFT);
		dirs.add(RIGHT);
		dirs.add(NONE);
		return dirs;
	}
}
