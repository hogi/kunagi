package scrum.client.project;

public class SetRequirementDirtyAction extends GSetRequirementDirtyAction {

	protected SetRequirementDirtyAction(Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Request Re-Estimation";
	}

	@Override
	public String getTooltip() {
		return "Mark this requirement as dirty. This means, the estimated work needs to be reestimated.";
	}

	@Override
	public boolean isExecutable() {
		if (requirement.isClosed()) return false;
		if (requirement.isDirty()) return false;
		if (requirement.isInCurrentSprint()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.setDirty(true);
	}

}
