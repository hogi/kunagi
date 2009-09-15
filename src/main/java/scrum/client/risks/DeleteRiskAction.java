package scrum.client.risks;


public class DeleteRiskAction extends GDeleteRiskAction {

	protected DeleteRiskAction(Risk risk) {
		super(risk);
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		return "Delete this risk.";
	}

	@Override
	public boolean isExecutable() {
		return getProject().isPig(getUser());
	}

	@Override
	protected void onExecute() {
		getProject().deleteRisk(risk);
	}

}
