package scrum.client.project;


public class SetRequirementCleanAction extends GSetRequirementCleanAction {

	protected SetRequirementCleanAction(Requirement requirement) {
		super(requirement);
	}

	@Override
	public String getLabel() {
		return "Set clean";
	}

	@Override
	public String getTooltip() {
		return "Mark this requirement as clean. This means, the estimated work is accurate.";
	}

	@Override
	public boolean isExecutable() {
		if (!requirement.isDirty()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.setDirty(false);
	}

}
