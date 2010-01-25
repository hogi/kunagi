package scrum.client.estimation;

import scrum.client.common.AScrumWidget;
import scrum.client.project.Requirement;

import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class PlanningPokerWidget extends AScrumWidget {

	private Requirement requirement;
	private SimplePanel wrapper;
	private PlanningPokerTableWidget table;

	public PlanningPokerWidget(Requirement requirement) {
		super();
		this.requirement = requirement;
	}

	@Override
	protected Widget onInitialization() {
		wrapper = new SimplePanel();
		return wrapper;
	}

	@Override
	protected void onUpdate() {
		if (requirement.isWorkEstimationVotingActive()) {
			if (table == null) table = new PlanningPokerTableWidget(requirement);
			wrapper.setWidget(table);
		} else {
			wrapper.setWidget(null);
		}
		super.onUpdate();
	}

}
