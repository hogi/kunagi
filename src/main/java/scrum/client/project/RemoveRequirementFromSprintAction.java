package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AAction;

public class RemoveRequirementFromSprintAction extends AAction {

	private Requirement requirement;

	public RemoveRequirementFromSprintAction(Requirement task, AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
		this.requirement = task;
	}

	@Override
	public String getLabel() {
		return "Remove from Sprint";
	}

	@Override
	public String getTooltip() {
		return "Remove this requirement from Sprint.";
	}

	@Override
	public boolean isExecutable() {
		return requirement.isSprintSet();
	}

	@Override
	protected void onExecute() {
		requirement.setSprint(null);
		ScrumGwtApplication.get().postSystemMessage(
			getUser().getName() + " removed requirement " + requirement.getReference() + " from current sprint.", true);
	}

}
