package project.model;

public abstract class Weapon extends BoardEntity {
	
	private DeploymentBehaviour deploymentBehviour;
	
	public Weapon(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public DeploymentBehaviour getDeploymentBehviour() {
		return deploymentBehviour;
	}

	public void setDeploymentBehviour(DeploymentBehaviour deploymentBehviour) {
		this.deploymentBehviour = deploymentBehviour;
	}

}
