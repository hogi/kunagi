package scrum.client.impediments;

import scrum.client.ScrumGwtApplication;
import scrum.client.workspace.Ui;

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
		Ui.get().showImpedimentList(impediment);
	}

}
