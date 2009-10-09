package scrum.client.impediments;

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
		Impediment impediment = getProject().createNewImpediment();
		cm.getProjectContext().showImpedimentList(impediment);
	}

}
