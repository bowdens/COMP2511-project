//package project.model;
package model;

public abstract class Weapon extends BoardEntity {
	
	private DeploymentBehaviour deploymentBehviour;
	
	public Weapon(int x, int y) {
		super(x, y);
	}

	public DeploymentBehaviour getDeploymentBehviour() {
		return deploymentBehviour;
	}

	public void setDeploymentBehviour(DeploymentBehaviour deploymentBehviour) {
		this.deploymentBehviour = deploymentBehviour;
	}
	
	public boolean performDeploy(Board board, BoardEntity deployedEntity) {
		return getDeploymentBehaviour().deploy(board, deployedEntity);
	}

}
