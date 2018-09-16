package project.model.enemies;

import project.model.Board;
import project.model.Enemy;
import project.model.MovementBehaviour;
import project.model.Player;
import project.model.movementBehaviours.GoTowardsPlayer;
import project.model.movementBehaviours.RunAwayBehaviour;
import project.model.movementBehaviours.dijkstra.Dijkstra;

public class Coward extends Enemy {

	private static final long serialVersionUID = 1L;
	private int runAwayDistance;
	private MovementBehaviour scaredMovement;
	private MovementBehaviour regularMovement;

	public Coward(int x, int y) {
		super(x, y);
		setMovementBehaviour(new GoTowardsPlayer());
		setScaredMovement(new RunAwayBehaviour());
		setRegularMovement(new GoTowardsPlayer());
		setRunAwayDistance(5);
	}
	
	@Override
	public void update(Board board) {
		/*
		 * check that the player is too close
		 * if they are, set the movement to run away
		 * if not, set the movement to go closer
		 * then call the super classes update
		 */
		
		Player player = board.getPlayer();
		if (player == null) {
			setMovementBehaviour(getRegularMovement());
		}
		
		// calculate the distance that it would take the player to get from them to me
		int distance = Dijkstra.distance(board, player, player.getX(), player.getY(), getX(), getY());
		
		
		if (distance <= getRunAwayDistance()) {
			setMovementBehaviour(getScaredMovement());
		} else {
			setMovementBehaviour(getRegularMovement());
		}
		
		super.update(board);
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

	/**
	 * @return the scaredMovement
	 */
	public MovementBehaviour getScaredMovement() {
		return scaredMovement;
	}

	/**
	 * @param scaredMovement the scaredMovement to set
	 */
	public void setScaredMovement(MovementBehaviour scaredMovement) {
		this.scaredMovement = scaredMovement;
	}

	/**
	 * @return the regularMovement
	 */
	public MovementBehaviour getRegularMovement() {
		return regularMovement;
	}

	/**
	 * @param regularMovement the regularMovement to set
	 */
	public void setRegularMovement(MovementBehaviour regularMovement) {
		this.regularMovement = regularMovement;
	}

}
