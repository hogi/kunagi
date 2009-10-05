package scrum.client.impediments;

public class CloseImpedimentAction extends GCloseImpedimentAction {

	public CloseImpedimentAction(scrum.client.impediments.Impediment impediment) {
		super(impediment);
	}

	@Override
	public String getLabel() {
		return "Close";
	}

	@Override
	public boolean isExecutable() {
		if (impediment.isClosed()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		impediment.setClosed(true);
	}

}