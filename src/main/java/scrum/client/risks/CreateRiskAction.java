package scrum.client.risks;

public class CreateRiskAction extends GCreateRiskAction {

	@Override
	public String getLabel() {
		return "Create new Risk";
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void onExecute() {
		Risk risk = getCurrentProject().createNewRisk();
		cm.getProjectContext().showRiskList(risk);
	}

}
