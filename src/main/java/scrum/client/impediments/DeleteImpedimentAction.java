package scrum.client.impediments;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.AScrumAction;

public class DeleteImpedimentAction extends AScrumAction {

	private Impediment impediment;

	public DeleteImpedimentAction(Impediment task, AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
		this.impediment = task;
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		return "Delete this impediment.";
	}

	@Override
	public boolean isExecutable() {
		return getProject().getScrumMasters().contains(getUser());
	}

	@Override
	protected void onExecute() {
		getProject().deleteImpediment(impediment);
	}

}
