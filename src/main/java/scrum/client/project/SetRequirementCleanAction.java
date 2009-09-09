package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.AAction;

public class SetRequirementCleanAction extends AAction {

	private Requirement requirement;

	public SetRequirementCleanAction(Requirement task, AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
		this.requirement = task;
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
