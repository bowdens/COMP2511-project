package project.model.movementBehaviours.dijkstra;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import project.model.Board;
import project.model.BoardEntity;
import project.model.Direction;

public class Dijkstra {
	/**
	 * gets the next direction to move into.
	 * @param entity The entity that is moving
	 * @param toX left=0
	 * @param toY top=0
	 * @return the direction that you need to take next to get to your destination, NONE if we are there, NONE if there is no path there
	 */
	public static Direction getNextMove(Board board, BoardEntity entity, int toX, int toY) {
		int fromX = entity.getX();
		int fromY = entity.getY();
		//System.out.println("going from (" + fromX + ", " + fromY + ") to (" + toX + ", " + toY + ")");
		if (fromX == toX && fromY == toY) {
			return Direction.NONE;
		}
		if(!board.canMoveOnto(entity, toX, toY)) {
			// if we can't get to the final square, return NONE
			System.out.println("cant get to " + toX + "," + toY + " entities on point: " + board.getEntitiesAt(toX, toY));
			return Direction.NONE;
		}
		
		ArrayList<Integer> moves = Dijkstra.dijkstra(board, entity, fromX, fromY, toX, toY);
		int next = moves.get(0);
		int nextX = Dijkstra.fromIndexToX(board, next);
		int nextY = Dijkstra.fromIndexToY(board, next);
		Direction direction = Direction.NONE;
		if (nextX == fromX -1 && nextY == fromY) {
			// left
			direction = Direction.LEFT;
		} else if (nextX == fromX + 1 && nextY == fromY) {
			// right
			direction = Direction.RIGHT;
		} else if (nextX == fromX && nextY == fromY - 1) {
			// up
			direction = Direction.UP;
		} else if (nextX == fromX && nextY == fromY + 1) {
			direction = Direction.DOWN;
		} else {
			// not supposed to happen
			System.out.println("not supposed to happen nextX = " + nextX + ", nextY = " + nextY);
			direction = Direction.NONE;
		}
		
		 //System.out.println("The next pos is (" + nextX + ", " + nextY +"), dir = " + direction);
		
		return direction;
	}
	
	public static int distance(Board board, BoardEntity entity, int fromX, int fromY, int toX, int toY) {
		if (fromX == toX && fromY == toY) {
			// we are there
			return 0;
		}
		if(!board.canMoveOnto(entity, toX, toY)) {
			// it's not possible to get there
			return -1;
		}
		
		return dijkstra(board, entity, fromX, fromY, toX, toY).size();
	}
	
	private static int fromCoordToIndex(Board board, int x, int y) {
		return x + y * board.getWidth();
	}
	
	private static int fromIndexToX(Board board, int index) {
		return index % board.getWidth();
	}
	
	private static int fromIndexToY(Board board, int index) {
		return index / board.getWidth();
	}
	
	private static ArrayList<Integer> dijkstra(Board board, BoardEntity entity, int fromX, int fromY, int toX, int toY) {
		Queue<Integer> verticies = new ArrayDeque<Integer>();
		int size = board.getHeight() * board.getWidth();
		int []dist = new int[size];
		int []prev = new int[size];
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				// add all vertexes where this entity can move onto them
				if (board.canMoveOnto(entity, i, j)) {
					int index = Dijkstra.fromCoordToIndex(board, i, j);
					dist[index] = Integer.MAX_VALUE;
					prev[index] = -1;
					verticies.add(index);
				}
			}
		}
		
		int source = Dijkstra.fromCoordToIndex(board, fromX, fromY);
		dist[source] = 0;
		
		while (verticies.size() > 0) {
			int nextVertex = verticies.peek();
			for (int i : verticies) {
				if (dist[i] < dist[nextVertex]) {
					nextVertex = i;
				}
			}
			verticies.remove(nextVertex);
			
			// add each neighbour to the current vertex to an array list
			int nextX = Dijkstra.fromIndexToX(board, nextVertex);
			int nextY = Dijkstra.fromIndexToY(board, nextVertex);
			ArrayList<Integer> neighbours = new ArrayList<Integer>();
			// quick and dirty way to add each neighbour up, down, left, right
			// also checks to make sure the x,y are within the board
			if(board.canMoveOnto(entity, nextX-1, nextY)) {
				int index = Dijkstra.fromCoordToIndex(board, nextX-1, nextY);
				if (board.validX(nextX-1) && board.validY(nextY)) {
					neighbours.add(index);
				}
			}
			if(board.canMoveOnto(entity, nextX+1, nextY)) {
				int index = Dijkstra.fromCoordToIndex(board, nextX+1, nextY);
				if (board.validX(nextX+1) && board.validY(nextY)) {
					neighbours.add(index);
				}
			}
			if(board.canMoveOnto(entity, nextX, nextY-1)) {
				int index = Dijkstra.fromCoordToIndex(board, nextX, nextY-1);
				if (board.validX(nextX) && board.validY(nextY-1)) {
					neighbours.add(index);
				}
			}
			if(board.canMoveOnto(entity, nextX, nextY+1)) {
				int index = Dijkstra.fromCoordToIndex(board, nextX, nextY+1);
				if (board.validX(nextX) && board.validY(nextY+1)) {
					neighbours.add(index);
				}
			}
			
			//System.out.println("neighbours of " + nextX + "," + nextY + " are " + neighbours);
			
			for (int neighbour : neighbours) {
				int alt = dist[nextVertex] + 1;
				// System.out.println("at " + nextVertex + " looking towards " + neighbour + ". dist[" + nextVertex + "] + 1 = " + alt + ", dist[" + neighbour + "] = " + dist[neighbour]);
				if (alt < dist[neighbour]) {
					dist[neighbour] = alt;
					prev[neighbour] = nextVertex;
				}
			}
		}
		
		int count = 0;
		ArrayList<Integer> moves = new ArrayList<Integer>();
		int current = Dijkstra.fromCoordToIndex(board, toX, toY);
		// System.out.println("working backwards from " + current);
		while(current != source) {
			moves.add(0,current);
			// System.out.println("moves = " + moves);
			// System.out.println("at " + current);
			// System.out.println("the previous of " + current + " is " + prev[current]);
			current = prev[current];
			count++;
			if(count > size*2) {
				System.out.println("Dijkstra breaking due to too much repetition - something went wrong");
				break;
			}
		}
		
		//System.out.println("Moves are " + moves);
		return moves;
	}
}
