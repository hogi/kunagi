package scrum.client.project;

import scrum.client.ScrumGwtApplication;
import scrum.client.workspace.Ui;

public class CreateRequirementAction extends GCreateRequirementAction {

	@Override
	public String getLabel() {
		return "Create new Requirement";
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void onExecute() {
		Requirement requirement = ScrumGwtApplication.get().getProject().createNewRequirement();
		Ui.get().showRequirement(requirement);
	}

}
