package project.model.items;

public abstract class Weapon extends Item {
	
	private static final long serialVersionUID = -408195096141645845L;
	private DeploymentBehaviour deploymentBehaviour;
	
	public Weapon(int x, int y) {
		super(x, y);
	}

	public DeploymentBehaviour getDeploymentBehaviour() {
		return deploymentBehaviour;
	}

	public void setDeploymentBehaviour(DeploymentBehaviour deploymentBehviour) {
		this.deploymentBehaviour = deploymentBehviour;
	}
	
	public boolean performDeploy(Board board, BoardEntity deployedEntity) {
		return getDeploymentBehaviour().deploy(board, deployedEntity);
	}

}
