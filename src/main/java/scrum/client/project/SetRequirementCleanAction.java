package scrum.client.project;

public class SetRequirementCleanAction extends GSetRequirementCleanAction {

	protected SetRequirementCleanAction(Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Confirm Estimation";
	}

	@Override
	public String getTooltip() {
		return "Mark this requirement as clean. This means, the estimated work is accurate.";
	}

	@Override
	public boolean isExecutable() {
		if (requirement.isClosed()) return false;
		if (!requirement.isDirty()) return false;
		if (requirement.getEstimatedWork() == null) return false;
		if (requirement.isInCurrentSprint()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.setDirty(false);
	}

}
