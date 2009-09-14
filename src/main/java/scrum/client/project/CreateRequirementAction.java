package scrum.client.project;

import ilarkesto.gwt.client.AWidget;
import scrum.client.ScrumGwtApplication;
import scrum.client.common.AScrumAction;
import scrum.client.workspace.WorkareaWidget;

public class CreateRequirementAction extends AScrumAction {

	public CreateRequirementAction(AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
	}

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
		WorkareaWidget.get().showRequirement(requirement);
	}

}
