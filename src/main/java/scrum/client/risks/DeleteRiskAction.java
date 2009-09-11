package scrum.client.risks;

import ilarkesto.gwt.client.AWidget;
import scrum.client.common.AScrumAction;

public class DeleteRiskAction extends AScrumAction {

	private Risk risk;

	public DeleteRiskAction(Risk risk, AWidget... widgetsToUpdate) {
		super(widgetsToUpdate);
		this.risk = risk;
	}

	@Override
	public String getLabel() {
		return "Delete";
	}

	@Override
	public String getTooltip() {
		return "Delete this risk.";
	}

	@Override
	public boolean isExecutable() {
		return getProject().getProductOwners().contains(getUser());
	}

	@Override
	protected void onExecute() {
		getProject().deleteRisk(risk);
	}

}
