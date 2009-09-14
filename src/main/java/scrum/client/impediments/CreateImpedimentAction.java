package scrum.client.impediments;

import scrum.client.ScrumGwtApplication;
import scrum.client.common.AScrumAction;
import scrum.client.workspace.WorkareaWidget;

public class CreateImpedimentAction extends AScrumAction {

	@Override
	public String getLabel() {
		return "Create new Impediment";
	}

	@Override
	public boolean isExecutable() {
		return true;
	}

	@Override
	protected void onExecute() {
		Impediment impediment = ScrumGwtApplication.get().getProject().createNewImpediment();
		WorkareaWidget.get().showImpediment(impediment);
	}

}
