package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.AScrumAction;

public class SetRequirementDirtyAction extends AScrumAction {

	private Requirement requirement;

	public SetRequirementDirtyAction(Requirement task, AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
		this.requirement = task;
	}

	@Override
	public String getLabel() {
		return "Set dirty";
	}

	@Override
	public String getTooltip() {
		return "Mark this requirement as dirty. This means, the estimated work needs to be reestimated.";
	}

	@Override
	public boolean isExecutable() {
		if (requirement.isDirty()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		requirement.setDirty(true);
	}

}
