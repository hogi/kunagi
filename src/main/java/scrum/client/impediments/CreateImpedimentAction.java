package scrum.client.impediments;

import scrum.client.ScrumGwtApplication;
import scrum.client.context.ProjectContext;

public class CreateImpedimentAction extends GCreateImpedimentAction {

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
		ProjectContext.get().showImpedimentList(impediment);
	}

}
