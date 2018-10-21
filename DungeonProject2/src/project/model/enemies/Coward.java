package project.model.enemies;

import project.model.Board;
import project.model.Enemy;
import project.model.Player;
import project.model.movementBehaviours.GoTowardsPlayer;
import project.model.movementBehaviours.RunAwayBehaviour;
import project.model.movementBehaviours.dijkstra.Dijkstra;

public class Coward extends Enemy {

	private static final long serialVersionUID = 1L;
	
	private int runAwayDistance;

	public Coward(int x, int y) {
		super(x, y, "coward");
		setMovementBehaviour(new GoTowardsPlayer());
		setRunAwayDistance(5);
	}
	
	/**
	 * @return the runAwayDistance
	 */
	public int getRunAwayDistance() {
		return runAwayDistance;
	}

	/**
	 * @param runAwayDistance the runAwayDistance to set
	 */
	public void setRunAwayDistance(int runAwayDistance) {
		this.runAwayDistance = runAwayDistance;
	}
	
	@Override
	public void update(Board board) {		
		Player player = board.getPlayer();
		
		int distance = Dijkstra.distance(board, player, player.getX(), player.getY(), getX(), getY());
		
		// if the player is to close to the coward then change the movement behaviour to run away
		if (distance <= getRunAwayDistance()) {
			setMovementBehaviour(new RunAwayBehaviour());
		}
		
		super.update(board);
	}

}
